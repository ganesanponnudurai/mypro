package com.client.controller;

import com.client.model.ClientInfoResponse;
import com.client.service.ClientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/client")
public class ClientInfoController {

    @Autowired
    private ClientInfoService clientInfoService;

    @GetMapping("/info")
    public ClientInfoResponse getData() throws IOException {
        return clientInfoService.extractData();
    }

    //to download .csv
    @GetMapping(value = "/download", produces = "text/csv")
    public ResponseEntity<byte[]> extract() throws IOException {

        String csv = clientInfoService.generateCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Output.csv")
                .header(HttpHeaders.CONTENT_TYPE, "text/csv")
                .body(csv.getBytes(StandardCharsets.UTF_8));
    }
}