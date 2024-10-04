package db.migration;

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

public class R__4_Insert_questions extends BaseJavaMigration {

    @Override
    public void migrate(Context context) {
        DataSource dataSource = new SingleConnectionDataSource(context.getConnection(), true);
        NamedParameterJdbcOperations jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insertQuestions(jdbcTemplate);
    }

    private void insertQuestions(NamedParameterJdbcOperations jdbc) {
        List<UUID> categoryIds = MigrationUtils.getIdsFromTable(jdbc, "categories");
        List<UUID> authorIds = MigrationUtils.getIdsFromTable(jdbc, "authors");
        var questionsParams = IntStream.rangeClosed(1, InsertConstants.QUESTIONS_COUNT)
                .mapToObj(i -> getQuestionParams(i, categoryIds, authorIds))
                .toArray(Map[]::new);
        String insertSql = "insert into questions (title, description, category_id, author_id, difficulty, created_at)" +
                           " values (:title, :description, :categoryId, :authorId, :difficulty, :createdAt)";
        jdbc.batchUpdate(insertSql, questionsParams);
    }

    private Map<String, Object> getQuestionParams(int questionIndex, List<UUID> categoryIds, List<UUID> authorIds) {
        String title = String.format("Вопрос #%d", questionIndex);
        return new MapSqlParameterSource("title", title.repeat(15))
                .addValue("description", title.repeat(20))
                .addValue("categoryId", MigrationUtils.getRandomIdFromList(categoryIds))
                .addValue("authorId", MigrationUtils.getRandomIdFromList(authorIds))
                .addValue("difficulty", getRandomDifficulty())
                .addValue("createdAt", LocalDateTime.now())
                .getValues();
    }

    private int getRandomDifficulty() {
        return MigrationUtils.random.nextInt(InsertConstants.MAX_QUESTION_DIFFICULTY) + InsertConstants.MIN_QUESTION_DIFFICULTY;
    }
}
