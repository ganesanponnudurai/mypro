package com.client.exception;

import com.client.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        request = mock(HttpServletRequest.class);
    }

    @Test
    void handleException_shouldReturnNotFoundResponse() {

        // Arrange
        DataNotFoundException exception =
                new DataNotFoundException("Data not found");

        when(request.getRequestURI())
                .thenReturn("/api/client/extract");

        // Act
        ResponseEntity<?> response =
                handler.handleException(exception, request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ErrorResponse);

        ErrorResponse error =
                (ErrorResponse) response.getBody();

        assertEquals(404, error.getStatus());
        assertEquals("Data not found", error.getError());
        assertEquals("Data not found", error.getMessage());
        assertEquals("/api/client/extract", error.getPath());
        assertNotNull(error.getTimestamp());
    }

    @Test
    void handleException_shouldHandleNullMessage() {

        // Arrange
        DataNotFoundException exception =
                new DataNotFoundException(null);

        when(request.getRequestURI())
                .thenReturn("/api/client/extract");

        // Act
        ResponseEntity<?> response =
                handler.handleException(exception, request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        ErrorResponse error =
                (ErrorResponse) response.getBody();

        assertNotNull(error);
        assertEquals(404, error.getStatus());
        assertNull(error.getError());
        assertNull(error.getMessage());
        assertEquals("/api/client/extract", error.getPath());
        assertNotNull(error.getTimestamp());
    }
}