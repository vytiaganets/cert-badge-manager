package com.example.solana.certbadgemanager.service;

package com.example.tokenomics.service;

import com.example.solana.certbadgemanager.repository.InvestorRepository;
import com.example.tokenomics.dto.InvestorRequestDTO;
import com.example.tokenomics.dto.InvestorResponseDTO;
import com.example.tokenomics.entity.Investor;
import com.example.tokenomics.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvestmentService {

    private final InvestorRepository investorRepository;

    @Autowired
    public InvestmentService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
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
}
