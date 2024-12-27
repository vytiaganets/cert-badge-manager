package com.example.avywhale.service;

import com.example.avywhale.dto.*;
import com.example.avywhale.model.Investment;
import com.example.avywhale.model.Investor;
import com.example.avywhale.repository.InvestmentRepository;
import com.example.avywhale.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvestmentService {

    private final InvestorRepository investorRepository;
    private final InvestmentRepository investmentRepository;

    @Autowired
    public InvestmentService(InvestorRepository investorRepository, InvestmentRepository investmentRepository) {
        this.investorRepository = investorRepository;
        this.investmentRepository = investmentRepository;
    }

    public InvestorResponseDTO createInvestor(InvestorRequestDTO investorRequestDTO) {
        Investor investor = mapToEntity(investorRequestDTO);
        Investor savedInvestor = investorRepository.save(investor);
        return mapToResponseDTO(savedInvestor);
    }

    public List<InvestorResponseDTO> getAllInvestors() {
        return investorRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<InvestorResponseDTO> getInvestorById(Long id) {
        return investorRepository.findById(id)
                .map(this::mapToResponseDTO);
    }

    public Optional<InvestorResponseDTO> updateInvestor(Long id, InvestorRequestDTO investorRequestDTO) {
        return investorRepository.findById(id).map(existingInvestor -> {
            existingInvestor.setName(investorRequestDTO.getName());
            existingInvestor.setEmail(investorRequestDTO.getEmail());
            existingInvestor.setPhoneNumber(investorRequestDTO.getPhoneNumber());
            existingInvestor.setInvestmentAmount(investorRequestDTO.getInvestmentAmount());
            existingInvestor.setDescription(investorRequestDTO.getDescription());
            Investor updatedInvestor = investorRepository.save(existingInvestor);
            return mapToResponseDTO(updatedInvestor);
        });
    }

    public boolean deleteInvestor(Long id) {
        if (investorRepository.existsById(id)) {
            investorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Investor mapToEntity(InvestorRequestDTO investorRequestDTO) {
        Investor investor = new Investor();
        investor.setName(investorRequestDTO.getName());
        investor.setEmail(investorRequestDTO.getEmail());
        investor.setPhoneNumber(investorRequestDTO.getPhoneNumber());
        investor.setInvestmentAmount(investorRequestDTO.getInvestmentAmount());
        investor.setDescription(investorRequestDTO.getDescription());
        return investor;
    }

    private InvestorResponseDTO mapToResponseDTO(Investor investor) {
        return new InvestorResponseDTO(
                investor.getId(),
                investor.getName(),
                investor.getEmail(),
                investor.getPhoneNumber(),
                investor.getInvestmentAmount(),
                investor.getDescription()
        );
    }

    public List<InvestmentResponseDTO> getInvestmentsByInvestorId(Long investorId) {
        return null;
    }

    public InvestmentAllocationResponseDTO getInvestmentAllocationById(Long allocationId) {
        return null;
    }

    public List<InvestmentAllocationResponseDTO> getAllInvestmentAllocations() {
        return null;
    }

    public InvestmentAllocationResponseDTO createInvestmentAllocation(InvestmentAllocationRequestDTO investmentAllocationRequestDTO) {
        return null;
    }
    public List<InvestmentResponseDTO> getAllInvestments() {
                return investmentRepository.findAll().stream()
                .map(this::mapToInvestmentResponseDTO)                 .collect(Collectors.toList());
    }

        private InvestmentResponseDTO mapToInvestmentResponseDTO(Investment investment) {
        return new InvestmentResponseDTO(
                investment.getId(),                              investment.getAmount(),                          investment.getAssetType(),                       investment.getInvestmentDate(),                   investment.getInvestor().getId()
        );
    }



}
