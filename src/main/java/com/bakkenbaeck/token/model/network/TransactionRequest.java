package com.bakkenbaeck.token.model.network;


public class TransactionRequest {

    public String value;
    public String from;
    public String to;
    public String gasPrice;
    public String gas;
    public String nonce;
    public String data;

    public TransactionRequest setValue(final String value) {
        this.value = value;
        return this;
    }

    public TransactionRequest setToAddress(final String addressInHex) {
        this.to = addressInHex;
        return this;
    }

    public TransactionRequest setFromAddress(final String addressInHex) {
        this.from = addressInHex;
        return this;
    }

    public TransactionRequest setGasPrice(final String gasPriceInHex) {
        this.gasPrice = gasPriceInHex;
        return this;
    }

    public TransactionRequest setGas(final String gasInHex) {
        this.gas = gasInHex;
        return this;
    }

    public TransactionRequest setNonce(final String nonceInHex) {
        this.nonce = nonceInHex;
        return this;
    }

    public TransactionRequest setData(final String dataInHex) {
        this.data = dataInHex;
        return this;
    }
}
