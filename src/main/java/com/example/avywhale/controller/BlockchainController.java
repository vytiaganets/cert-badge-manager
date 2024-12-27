package com.example.avywhale.controller;

import com.example.avywhale.service.BlockchainService;
import com.example.avywhale.blockchain.CrossChainManager;
import com.example.avywhale.dto.BadgeRequestDTO;
import com.example.avywhale.dto.CertificateRequestDTO;
import com.example.avywhale.util.SolanaConnectionChecker;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {
    @Autowired
    private SolanaConnectionChecker solanaConnectionChecker;
    private final CrossChainManager crossChainManager;


    @Autowired
    public BlockchainController(CrossChainManager crossChainManager) {
        this.crossChainManager = crossChainManager;

    }

    @PostMapping("/write-badge")
    public String writeBadge(@RequestBody BadgeRequestDTO badgeRequestDTO) {
        return crossChainManager.writeBadgeDataToBlockchain(badgeRequestDTO);
    }

    @PostMapping("/write-certificate")
    public String writeCertificate(@RequestBody CertificateRequestDTO certificateRequestDTO) {
        return crossChainManager.writeCertificateDataToBlockchain(certificateRequestDTO);
    }

    @GetMapping("/read-badge/{badgeId}")
    public String readBadge(@PathVariable String badgeId) {
        return crossChainManager.readBadgeDataFromBlockchain(badgeId);
    }

    @GetMapping("/read-certificate/{certificateId}")
    public String readCertificate(@PathVariable String certificateId) {
        return crossChainManager.readCertificateDataFromBlockchain(certificateId);
    }

    @GetMapping("/connect")
    public ResponseEntity<Map<String, Object>> connectToBlockchain() {
                String solanaResponse = solanaConnectionChecker.getLatestBlockhash();

                JSONObject responseJson = new JSONObject(solanaResponse);
        JSONObject result = responseJson.getJSONObject("result");
        JSONObject context = result.getJSONObject("context");
        JSONObject value = result.getJSONObject("value");

        Map<String, Object> response = new HashMap<>();
        response.put("status", "Connected");
        response.put("details", Map.of(
                "blockhash", value.getString("blockhash"),
                "lastValidBlockHeight", value.getInt("lastValidBlockHeight"),
                "slot", context.getInt("slot"),
                "apiVersion", context.getString("apiVersion")
        ));

        return ResponseEntity.ok(response);
    }

}