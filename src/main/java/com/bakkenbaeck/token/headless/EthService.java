package com.bakkenbaeck.token.headless;

import com.bakkenbaeck.token.crypto.HDWallet;
import com.bakkenbaeck.token.model.network.*;
import com.bakkenbaeck.token.network.BalanceService;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;

public class EthService {
    private final BalanceService balanceService;
    private final HDWallet wallet;

    public EthService(HDWallet wallet, String baseUrl) {
        this.wallet = wallet;
        this.balanceService = new BalanceService(wallet, baseUrl);
    }

    public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public Response<UnsignedTransaction> createTransaction(final HashMap<String, String> params) throws IOException {
        String from = params.get("from");
        if (from == null) {
            from = wallet.getPaymentAddress();
        }
        TransactionRequest request = new TransactionRequest()
            .setToAddress(params.get("to"))
            .setFromAddress(from)
            .setValue(params.get("value"))
            .setGasPrice(params.get("gasPrice"))
            .setGas(params.get("gas"))
            .setNonce(params.get("nonce"))
            .setData(params.get("data"));
        return balanceService.getApi().createTransaction(request).execute();
    }

    public SentTransaction sendTransaction(String utx) throws IOException {
        final long ts = balanceService.getApi().getTimestamp().execute().body().get();
        String signature = this.wallet.signTransaction(utx);
        final SignedTransaction signedTransaction = new SignedTransaction()
                .setEncodedTransaction(utx)
                .setSignature(signature);
        return balanceService.getApi().sendSignedTransaction(ts, signedTransaction).execute().body();
    }

}
