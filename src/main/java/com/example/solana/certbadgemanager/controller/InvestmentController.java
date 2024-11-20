package com.example.solana.certbadgemanager.controller;

import com.example.solana.certbadgemanager.dto.*;
import com.example.solana.certbadgemanager.service.InvestmentService;
import com.example.solana.certbadgemanager.service.InvestorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;
    private final InvestorService investorService;
    public InvestmentController(InvestmentService investmentService, InvestorService investorService) {
        this.investmentService = investmentService;
        this.investorService = investorService;
    }


    @PostMapping("/investors")
    public ResponseEntity<InvestorResponseDTO> createInvestor(@RequestBody InvestorRequestDTO investorRequestDTO) {
        InvestorResponseDTO investorResponse = investmentService.createInvestor(investorRequestDTO);
        return ResponseEntity.ok(investorResponse);
    }

    @GetMapping("/investors")
    public ResponseEntity<List<InvestorResponseDTO>> getAllInvestors() {
        List<InvestorResponseDTO> investors = investmentService.getAllInvestors();
        return ResponseEntity.ok(investors);
    }

    @GetMapping("/{investorId}")
    public ResponseEntity<InvestorResponseDTO> getInvestorById(@PathVariable Long investorId) {
        InvestorResponseDTO investor = investorService.findById(investorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Investor not found"));
        return ResponseEntity.ok(investor);
    }


    @PostMapping("/allocations")
    public ResponseEntity<InvestmentAllocationResponseDTO> createInvestmentAllocation(
            @RequestBody InvestmentAllocationRequestDTO investmentAllocationRequestDTO) {
        InvestmentAllocationResponseDTO allocationResponse = investmentService.createInvestmentAllocation(investmentAllocationRequestDTO);
        return ResponseEntity.ok(allocationResponse);
    }

    @GetMapping("/allocations")
    public ResponseEntity<List<InvestmentAllocationResponseDTO>> getAllInvestmentAllocations() {
        List<InvestmentAllocationResponseDTO> allocations = investmentService.getAllInvestmentAllocations();
        return ResponseEntity.ok(allocations);
    }

    @GetMapping("/allocations/{id}")
    public ResponseEntity<InvestmentAllocationResponseDTO> getInvestmentAllocationById(
            @PathVariable("id") Long allocationId) {
        InvestmentAllocationResponseDTO allocationResponse = investmentService.getInvestmentAllocationById(allocationId);
        return ResponseEntity.ok(allocationResponse);
    }
    @GetMapping("/{investorId}/investments")
    public ResponseEntity<List<InvestmentResponseDTO>> getInvestmentsByInvestor(@PathVariable Long investorId) {
        InvestorResponseDTO investor = investorService.findById(investorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Investor not found"));

        List<InvestmentResponseDTO> investments = investmentService.getInvestmentsByInvestorId(investorId);
        return ResponseEntity.ok(investments);
    }
}
