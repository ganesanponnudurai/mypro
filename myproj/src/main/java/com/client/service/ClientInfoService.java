package com.client.service;

import com.client.exception.DataNotFoundException;
import com.client.model.ClientInfoResponse;
import com.client.util.ColumnDefinition;
import com.client.util.ColumnSpecUtil;
import com.client.util.FrameworkConstants;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientInfoService {

    private List<String> loadData() throws IOException {
        Path path = Path.of(FrameworkConstants.DATALOCATION + FrameworkConstants.FILENAME);
        return Files.readAllLines(path);
    }

    public ClientInfoResponse extractData() throws IOException {
        try {
            List<String> records = this.loadData();
            List<String> clientInfo = records.stream().map(record -> {
                return
                        FrameworkConstants.CLIENTINFOCOLS.stream().map(field -> {
                            ColumnDefinition spec = ColumnSpecUtil.COLUMN_MAP.get(field);
                            System.out.println(record.substring(spec.getStart() - 1, spec.getEnd()));
                            System.out.println(record.substring(spec.getStart() - 1, spec.getEnd()).length());
                            return record.substring(spec.getStart() - 1, spec.getEnd());
                            //return record.substring(spec.getStart() - 1, spec.getEnd() - 1).trim();
                        }).collect(Collectors.joining());
            }).collect(Collectors.toList());

            List<String> productInfo = records.stream().map(record -> {
                return FrameworkConstants.PRDCTINFOCOLS.stream().map(field -> {
                    ColumnDefinition spec = ColumnSpecUtil.COLUMN_MAP.get(field);
                    return record.substring(spec.getStart() - 1, spec.getEnd() );
                }).collect(Collectors.joining());
            }).collect(Collectors.toList());

            List<Double> totalInfo = records.stream().map(record -> {
                return Double.valueOf(record.substring(ColumnSpecUtil.COLUMN_MAP.get("QUANTITY LONG").getStart() - 1, ColumnSpecUtil.COLUMN_MAP.get("QUANTITY LONG").getEnd())) -
                        Double.valueOf(record.substring(ColumnSpecUtil.COLUMN_MAP.get("QUANTITY SHORT").getStart() - 1, ColumnSpecUtil.COLUMN_MAP.get("QUANTITY SHORT").getEnd()));
            }).collect(Collectors.toList());
            return new ClientInfoResponse(clientInfo, productInfo, totalInfo);
        } catch (Exception ee) {
            throw new DataNotFoundException("DataNotFoundException");
        }
    }

    public String generateCsv() throws IOException {

        ClientInfoResponse response = extractData();

        StringBuilder csv = new StringBuilder();

        csv.append("Client_Information,Product_Information,Total_Transaction_Amount\n");

        for (int i = 0; i < response.getClientInformation().size(); i++) {

            csv.append(response.getClientInformation().get(i)).append(",")
                    .append(response.getProductInformation().get(i)).append(",")
                    .append(response.getTotalTransactionAmount().get(i))
                    .append("\n");
        }
        return csv.toString();
    }
}