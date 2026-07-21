package com.client.model;

import java.util.List;

public class ClientInfoResponse {

    private List<String> clientInformation;
    private List<String> productInformation;
    private List<Double> totalTransactionAmount;

    public ClientInfoResponse(List<String> clientInformation, List<String> productInformation, List<Double> totalTransactionAmount) {
        this.clientInformation = clientInformation;
        this.productInformation = productInformation;
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public void setTotalTransactionAmount(List<Double> totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public void setProductInformation(List<String> productInformation) {
        this.productInformation = productInformation;
    }

    public List<String> getProductInformation() {
        return productInformation;
    }

    public List<Double> getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public List<String> getClientInformation() {
        return clientInformation;
    }

    public void setClientInformation(List<String> clientInformation) {
        this.clientInformation = clientInformation;
    }

}