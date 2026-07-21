package com.client.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientInfoResponseTest {

    @Test
    void testConstructorAndGetters() {

        List<String> clientInfo = Arrays.asList(
                "CL432100020001",
                "CL432100020002"
        );

        List<String> productInfo = Arrays.asList(
                "SGXDCFUSGX",
                "HKEXFUT"
        );

        List<Double> totalAmount = Arrays.asList(
                925000.0,
                924500.0
        );

        ClientInfoResponse response =
                new ClientInfoResponse(clientInfo, productInfo, totalAmount);

        assertEquals(clientInfo, response.getClientInformation());
        assertEquals(productInfo, response.getProductInformation());
        assertEquals(totalAmount, response.getTotalTransactionAmount());
    }

    @Test
    void testSettersAndGetters() {

        ClientInfoResponse response =
                new ClientInfoResponse(null, null, null);

        List<String> clientInfo = List.of("CLIENT001");
        List<String> productInfo = List.of("PRODUCT001");
        List<Double> totalAmount = List.of(1000.50);

        response.setClientInformation(clientInfo);
        response.setProductInformation(productInfo);
        response.setTotalTransactionAmount(totalAmount);

        assertEquals(clientInfo, response.getClientInformation());
        assertEquals(productInfo, response.getProductInformation());
        assertEquals(totalAmount, response.getTotalTransactionAmount());
    }

    @Test
    void testConstructorWithEmptyLists() {

        ClientInfoResponse response =
                new ClientInfoResponse(
                        List.of(),
                        List.of(),
                        List.of());

        assertTrue(response.getClientInformation().isEmpty());
        assertTrue(response.getProductInformation().isEmpty());
        assertTrue(response.getTotalTransactionAmount().isEmpty());
    }

    @Test
    void testConstructorWithNullValues() {

        ClientInfoResponse response =
                new ClientInfoResponse(null, null, null);

        assertNull(response.getClientInformation());
        assertNull(response.getProductInformation());
        assertNull(response.getTotalTransactionAmount());
    }

}