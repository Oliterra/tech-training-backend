package edu.oliterra.tech.training.migration;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.*;

public final class MigrationUtils {

    public static final Random random = new Random();
    private static final String ID_NAME = "id";

    private MigrationUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<UUID> getIdsFromTable(NamedParameterJdbcOperations jdbc, String tableName) {
        String idsSelectionSql = String.format("select %s from %s", ID_NAME, tableName);
        List<Map<String, Object>> rows = jdbc.queryForList(idsSelectionSql, new MapSqlParameterSource());
        return rows.stream()
                .map(row -> Objects.toString(row.get(ID_NAME)))
                .filter(Objects::nonNull)
                .map(UUID::fromString)
                .toList();
    }

    public static UUID getRandomIdFromList(List<UUID> allIds) {
        return allIds.get(random.nextInt(allIds.size()));
    }
}
