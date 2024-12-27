package com.example.avywhale.util;

public class LamportsToTokensConverter {

    private static final long LAMPORTS_PER_SOL = 1_000_000_000;

    public static double convertLamportsToTokens(long lamports, double tokenRateInSol) {
        double sol = (double) lamports / LAMPORTS_PER_SOL;

        return sol / tokenRateInSol;
    }

    public static void main(String[] args) {
        long lamports = 1461600;
        double tokenRateInSol = 0.1;

        double tokens = convertLamportsToTokens(lamports, tokenRateInSol);
        System.out.println("Tokens: " + tokens);
    }
}