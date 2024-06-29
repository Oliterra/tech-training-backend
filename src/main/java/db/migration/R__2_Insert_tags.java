package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.IntStream;

public class R__2_Insert_tags extends BaseJavaMigration {

    @Override
    public void migrate(Context context) {
        DataSource dataSource = new SingleConnectionDataSource(context.getConnection(), true);
        NamedParameterJdbcOperations jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insertTags(jdbcTemplate);
    }

    private void insertTags(NamedParameterJdbcOperations jdbc) {
        var tagsParams = IntStream.rangeClosed(1, InsertConstants.TAGS_COUNT)
                .mapToObj(i -> String.format("#Тэг%d", i))
                .map(tagName -> new MapSqlParameterSource("tagName", tagName).getValues())
                .toArray(Map[]::new);
        String insertSql = "insert into tags (name) values (:tagName)";
        jdbc.batchUpdate(insertSql, tagsParams);
    }
}
