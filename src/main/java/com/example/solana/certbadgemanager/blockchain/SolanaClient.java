package com.example.solana.certbadgemanager.blockchain;

public class SolanaClient {
    private final String solanaRpcUrl = "htttp://api.mainnet-beta.solana.com";

    public SolanaClient(String solanaRpcUrl) {
    }

    public String sendTransaction(String rawTransaction){
        String transactionId = "mockTransactionId";
        return transactionId;
    }

    public String getDataFromBlockchain(String accountId){
        String data = "mocData";
        return data;
    }
}
