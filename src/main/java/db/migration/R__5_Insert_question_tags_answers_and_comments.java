package db.migration;

import jakarta.annotation.Nullable;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

public class R__5_Insert_question_tags_answers_and_comments extends BaseJavaMigration {

    @Override
    public void migrate(Context context) {
        DataSource dataSource = new SingleConnectionDataSource(context.getConnection(), true);
        NamedParameterJdbcOperations jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insertQuestionsRelations(jdbcTemplate);
    }

    private void insertQuestionsRelations(NamedParameterJdbcOperations jdbc) {
        List<UUID> questionIds = MigrationUtils.getIdsFromTable(jdbc, "questions");
        List<UUID> tagIds = MigrationUtils.getIdsFromTable(jdbc, "tags");
        List<UUID> authorIds = MigrationUtils.getIdsFromTable(jdbc, "authors");

        List<Map<String, Object>> questionsTagsParams = new ArrayList<>(questionIds.size());
        Map<UUID, List<UUID>> uniqueQuestionTagsPairs = new HashMap<>();
        List<Map<String, Object>> commentsParams = new ArrayList<>(questionIds.size());
        List<Map<String, Object>> answersParams = new ArrayList<>(questionIds.size());
        questionIds.forEach(questionId -> {
            IntStream.rangeClosed(1, InsertConstants.TAGS_FOR_QUESTION_COUNT)
                    .mapToObj(i -> getUniqueQuestionTagsParams(questionId, tagIds, uniqueQuestionTagsPairs))
                    .filter(Objects::nonNull)
                    .forEach(questionsTagsParams::add);
            IntStream.rangeClosed(1, InsertConstants.COMMENTS_COUNT)
                    .mapToObj(i -> getCommentParams(questionId, authorIds))
                    .forEach(commentsParams::add);
            IntStream.rangeClosed(1, InsertConstants.ANSWERS_FOR_QUESTION_COUNT)
                    .mapToObj(i -> getAnswerParams(questionId, authorIds))
                    .forEach(answersParams::add);
        });

        String questionsTagsInsertSql = "insert into questions_tags (tag_id, question_id) " +
                                        "values (:tagId, :questionId)";
        String commentsInsertSql = "insert into comments (question_id, author_id, \"text\") " +
                                   "values (:questionId, :authorId, :commentText)";
        String answersInsertSql = "insert into answers (author_id, question_id, \"text\", created_at) " +
                                  "values (:authorId, :questionId, :text, :createdAt)";
        jdbc.batchUpdate(questionsTagsInsertSql, questionsTagsParams.toArray(new Map[0]));
        jdbc.batchUpdate(commentsInsertSql, commentsParams.toArray(new Map[0]));
        jdbc.batchUpdate(answersInsertSql, answersParams.toArray(new Map[0]));
    }

    private Map<String, Object> getCommentParams(UUID questionId, List<UUID> authorIds) {
        UUID authorId = MigrationUtils.getRandomIdFromList(authorIds);
        return new MapSqlParameterSource("questionId", questionId)
                .addValue("authorId", authorId)
                .addValue("commentText", String.format("Коммент #%s_%s", questionId, UUID.randomUUID()))
                .getValues();
    }

    private Map<String, Object> getAnswerParams(UUID questionId, List<UUID> authorIds) {
        UUID authorId = MigrationUtils.getRandomIdFromList(authorIds);
        return new MapSqlParameterSource("questionId", questionId)
                .addValue("authorId", authorId)
                .addValue("questionId", questionId)
                .addValue("text", String.format("Ответ #%s_%s", questionId, UUID.randomUUID()))
                .addValue("createdAt", LocalDateTime.now())
                .getValues();
    }

    @Nullable
    private Map<String, Object> getUniqueQuestionTagsParams(UUID questionId, List<UUID> tagIds,
                                                            Map<UUID, List<UUID>> uniqueQuestionTagsPairs) {
        UUID tagId = MigrationUtils.getRandomIdFromList(tagIds);
        if (!uniqueQuestionTagsPairs.containsKey(questionId)) {
            uniqueQuestionTagsPairs.put(questionId, new ArrayList<>(List.of(tagId)));
            return getQuestionTagsParams(questionId, tagId);
        } else if (!uniqueQuestionTagsPairs.get(questionId).contains(tagId)) {
            uniqueQuestionTagsPairs.get(questionId).add(tagId);
            return getQuestionTagsParams(questionId, tagId);
        }
        return null;
    }

    private Map<String, Object> getQuestionTagsParams(UUID questionId, UUID tagId) {
        return new MapSqlParameterSource("questionId", questionId)
                .addValue("tagId", tagId)
                .getValues();
    }
}
