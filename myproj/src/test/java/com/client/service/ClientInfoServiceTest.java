package com.client.service;

import com.client.exception.DataNotFoundException;
import com.client.model.ClientInfoResponse;
import com.client.util.FrameworkConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientInfoServiceTest {

    private ClientInfoService service;

    private Path testFile;

    @BeforeEach
    void setUp() throws Exception {

        service = new ClientInfoService();

        testFile = Path.of(
                FrameworkConstants.DATALOCATION +
                        FrameworkConstants.FILENAME);

        Files.createDirectories(testFile.getParent());
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(testFile);
    }

    @Test
    void extractData_success() throws Exception {

        List<String> data = List.of(
                "305CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O"
        );

        Files.write(testFile, data);

        ClientInfoResponse response = service.extractData();

        assertNotNull(response);
        assertEquals(1, response.getClientInformation().size());
        assertEquals(1, response.getProductInformation().size());
        assertEquals(1, response.getTotalTransactionAmount().size());
    }

    @Test
    void extractData_emptyFile() throws Exception {

        Files.write(testFile, List.of());

        ClientInfoResponse response = service.extractData();

        assertNotNull(response);
        assertTrue(response.getClientInformation().isEmpty());
        assertTrue(response.getProductInformation().isEmpty());
        assertTrue(response.getTotalTransactionAmount().isEmpty());
    }

    @Test
    void extractData_invalidRecord() throws Exception {

        Files.write(testFile, List.of("ABC"));

        assertThrows(DataNotFoundException.class,
                () -> service.extractData());
    }

}