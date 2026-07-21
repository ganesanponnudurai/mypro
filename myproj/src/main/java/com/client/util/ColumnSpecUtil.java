package com.client.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ColumnSpecUtil {

    public static final Map<String, ColumnDefinition> COLUMN_MAP = new LinkedHashMap<>();

    static {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream inputStream =
                    ColumnSpecUtil.class.getClassLoader()
                            .getResourceAsStream("1234-spec.json");

            ColumnSpecification specification =
                    mapper.readValue(inputStream, ColumnSpecification.class);

            for (int i = 0; i < specification.getFieldnames().size(); i++) {

                String fieldName = specification.getFieldnames().get(i);
                String position = specification.getFixedColPos().get(i);

                String[] pos = position.split("-");

                int start = Integer.parseInt(pos[0]);
                int end = Integer.parseInt(pos[1]);

                COLUMN_MAP.put(
                        fieldName,
                        new ColumnDefinition(fieldName, start, end));
            }

        } catch (Exception ex) {
            throw new RuntimeException("Unable to load column specification.", ex);
        }

    }

    private ColumnSpecUtil() {
    }

    public static ColumnDefinition getColumn(String fieldName) {
        return COLUMN_MAP.get(fieldName);
    }

    public static String getValue(String record, String fieldName) {

        ColumnDefinition column = COLUMN_MAP.get(fieldName);

        if (column == null) {
            throw new IllegalArgumentException("Unknown field : " + fieldName);
        }

        int start = column.getStart() - 1;
        int end = column.getEnd();

        if (record.length() < end) {
            return "";
        }

        return record.substring(start, end).trim();
    }

    public static Map<String, String> extractAll(String record) {

        Map<String, String> result = new LinkedHashMap<>();

        COLUMN_MAP.forEach((key, value) ->
                result.put(key, getValue(record, key)));

        return result;
    }

    public static Map<String, ColumnDefinition> getColumns() {
        return COLUMN_MAP;
    }
}