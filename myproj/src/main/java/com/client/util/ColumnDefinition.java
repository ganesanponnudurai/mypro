package com.client.util;

public class ColumnDefinition {

    private String fieldName;
    private int start;
    private int end;

    public ColumnDefinition() {
    }

    public ColumnDefinition(String fieldName, int start, int end) {
        this.fieldName = fieldName;
        this.start = start;
        this.end = end;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}