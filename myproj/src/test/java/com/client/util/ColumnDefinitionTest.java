package com.client.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColumnDefinitionTest {

    @Test
    @DisplayName("Should create object using default constructor")
    void testDefaultConstructor() {

        ColumnDefinition column = new ColumnDefinition();

        assertNotNull(column);
        assertNull(column.getFieldName());
        assertEquals(0, column.getStart());
        assertEquals(0, column.getEnd());
    }

    @Test
    @DisplayName("Should create object using parameterized constructor")
    void testParameterizedConstructor() {

        ColumnDefinition column =
                new ColumnDefinition("Name", 1, 20);

        assertEquals("Name", column.getFieldName());
        assertEquals(1, column.getStart());
        assertEquals(20, column.getEnd());
    }

    @Test
    @DisplayName("Should update field name")
    void testSetFieldName() {

        ColumnDefinition column = new ColumnDefinition();

        column.setFieldName("Customer");

        assertEquals("Customer", column.getFieldName());
    }

    @Test
    @DisplayName("Should update start position")
    void testSetStart() {

        ColumnDefinition column = new ColumnDefinition();

        column.setStart(15);

        assertEquals(15, column.getStart());
    }

    @Test
    @DisplayName("Should update end position")
    void testSetEnd() {

        ColumnDefinition column = new ColumnDefinition();

        column.setEnd(35);

        assertEquals(35, column.getEnd());
    }

    @Test
    @DisplayName("Should allow null field name")
    void testNullFieldName() {

        ColumnDefinition column =
                new ColumnDefinition(null, 5, 10);

        assertNull(column.getFieldName());
    }

    @Test
    @DisplayName("Should allow empty field name")
    void testEmptyFieldName() {

        ColumnDefinition column =
                new ColumnDefinition("", 5, 10);

        assertEquals("", column.getFieldName());
    }

    @Test
    @DisplayName("Should allow negative start value")
    void testNegativeStart() {

        ColumnDefinition column =
                new ColumnDefinition("Test", -5, 10);

        assertEquals(-5, column.getStart());
    }

    @Test
    @DisplayName("Should allow negative end value")
    void testNegativeEnd() {

        ColumnDefinition column =
                new ColumnDefinition("Test", 1, -10);

        assertEquals(-10, column.getEnd());
    }

    @Test
    @DisplayName("Should allow start greater than end")
    void testStartGreaterThanEnd() {

        ColumnDefinition column =
                new ColumnDefinition("Test", 30, 20);

        assertEquals(30, column.getStart());
        assertEquals(20, column.getEnd());
    }

    @Test
    @DisplayName("Should support zero values")
    void testZeroValues() {

        ColumnDefinition column =
                new ColumnDefinition("Zero", 0, 0);

        assertEquals(0, column.getStart());
        assertEquals(0, column.getEnd());
    }

    @Test
    @DisplayName("Should support Integer MAX values")
    void testMaxValues() {

        ColumnDefinition column =
                new ColumnDefinition(
                        "MAX",
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, column.getStart());
        assertEquals(Integer.MAX_VALUE, column.getEnd());
    }

    @Test
    @DisplayName("Should support Integer MIN values")
    void testMinValues() {

        ColumnDefinition column =
                new ColumnDefinition(
                        "MIN",
                        Integer.MIN_VALUE,
                        Integer.MIN_VALUE);

        assertEquals(Integer.MIN_VALUE, column.getStart());
        assertEquals(Integer.MIN_VALUE, column.getEnd());
    }

}