package com.example.avywhale.controller;

import com.example.avywhale.model.Transaction;
import com.example.avywhale.service.SolanaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final SolanaService solanaService;

    public WalletController(SolanaService solanaService) {
        this.solanaService = solanaService;
    }

    @GetMapping("/{walletAddress}/balance")
    public ResponseEntity<String> getBalance(@PathVariable String walletAddress) {
        try {
            System.out.println("Fetching balance for wallet: " + walletAddress);
            String balance = solanaService.getWalletBalance(walletAddress);
            System.out.println("Balance fetched: " + balance);
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get wallet balance: " + e.getMessage());
        }
    }

    @PostMapping("/{walletAddress}/invest")
    public ResponseEntity<String> invest(@PathVariable String walletAddress, @RequestParam double amount) {
        try {
            boolean success = solanaService.invest(walletAddress, amount);
            if (success) {
                return ResponseEntity.ok("Investment successful!");
            } else {
                return ResponseEntity.badRequest().body("Investment failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during investment: " + e.getMessage());
        }
    }

    @PostMapping("/top-up")
    public Map<String, String> topUpWallet(@RequestBody Map<String, Object> request) {
        try {
            System.out.println("Request received: " + request);
            if (!request.containsKey("amount") || !(request.get("amount") instanceof Number)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid amount in request");
            }
            String walletAddress = "63CuxETsKD5no5ZhygnBJQCFavi18dT4iw6dQUoepaaA";
            Number amountNumber = (Number) request.get("amount");
            long amount = amountNumber.longValue();

            boolean success = solanaService.topUpWallet(walletAddress, amount);

            if (success) {
                return Map.of("message", "Wallet " + walletAddress + " topped up with " + amount + " tokens.");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to top up wallet.");
            }
        } catch (Exception e) {
            System.err.println("Error processing request: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request data", e);
        }
    }

    @PostMapping("/spend")
    public Map<String, String> spendFromWallet(@RequestBody Map<String, Object> request) {
        try {
            String walletAddress = "63CuxETsKD5no5ZhygnBJQCFavi18dT4iw6dQUoepaaA";
            if (!request.containsKey("amount") || !(request.get("amount") instanceof Number)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid amount in request");
            }
            long amount = ((Number) request.get("amount")).longValue();

            String message = solanaService.spendFromWallet(walletAddress, amount);
            return Map.of("message", message);
        } catch (Exception e) {
            System.err.println("Error processing spend request: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error processing spend request", e);
        }
    }
    @GetMapping("/{walletAddress}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String walletAddress) {
        try {
            List<Transaction> transactions = solanaService.getTransactionHistory(walletAddress);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/transfer")
    public Map<String, String> transferTokens(@RequestBody Map<String, Object> request) {
        try {
            String fromWallet = "63CuxETsKD5no5ZhygnBJQCFavi18dT4iw6dQUoepaaA";
            if (!request.containsKey("toWallet") || !(request.get("toWallet") instanceof String)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid toWallet in request");
            }
            if (!request.containsKey("amount") || !(request.get("amount") instanceof Number)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid amount in request");
            }

            String toWallet = (String) request.get("toWallet");
            long amount = ((Number) request.get("amount")).longValue();

            String message = solanaService.transferTokens(fromWallet, toWallet, amount);
            return Map.of("message", message);
        } catch (Exception e) {
            System.err.println("Error processing transfer request: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error processing transfer request", e);
        }
    }
}