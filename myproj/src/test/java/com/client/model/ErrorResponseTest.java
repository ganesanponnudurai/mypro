package com.client.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testNoArgsConstructorAndSetters() {

        ErrorResponse errorResponse = new ErrorResponse();

        LocalDateTime now = LocalDateTime.now();

        errorResponse.setTimestamp(now);
        errorResponse.setStatus(404);
        errorResponse.setError("Not Found");
        errorResponse.setMessage("Record not found");
        errorResponse.setPath("/api/client/extract");

        assertEquals(now, errorResponse.getTimestamp());
        assertEquals(404, errorResponse.getStatus());
        assertEquals("Not Found", errorResponse.getError());
        assertEquals("Record not found", errorResponse.getMessage());
        assertEquals("/api/client/extract", errorResponse.getPath());
    }

    @Test
    void testAllArgsConstructor() {

        LocalDateTime now = LocalDateTime.now();

        ErrorResponse errorResponse = new ErrorResponse(
                now,
                500,
                "Internal Server Error",
                "Unexpected error",
                "/api/client/extract"
        );

        assertEquals(now, errorResponse.getTimestamp());
        assertEquals(500, errorResponse.getStatus());
        assertEquals("Internal Server Error", errorResponse.getError());
        assertEquals("Unexpected error", errorResponse.getMessage());
        assertEquals("/api/client/extract", errorResponse.getPath());
    }

    @Test
    void testDefaultValues() {

        ErrorResponse errorResponse = new ErrorResponse();

        assertNull(errorResponse.getTimestamp());
        assertEquals(0, errorResponse.getStatus());
        assertNull(errorResponse.getError());
        assertNull(errorResponse.getMessage());
        assertNull(errorResponse.getPath());
    }

}