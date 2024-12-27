package com.example.avywhale.service;

import com.example.avywhale.exception.ResourceNotFoundException;
import com.example.avywhale.dto.InvestorResponseDTO;
import org.springframework.stereotype.Service;

import com.example.avywhale.dto.InvestorRequestDTO;
import com.example.avywhale.model.Investor;
import com.example.avywhale.repository.InvestorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public InvestorResponseDTO createInvestor(InvestorRequestDTO requestDTO) {
        Investor investor = new Investor();
        investor.setName(requestDTO.getName());
        investor.setEmail(requestDTO.getEmail());
        investor.setPhoneNumber(requestDTO.getPhoneNumber());
        investorRepository.save(investor);

        return mapToResponseDTO(investor);
    }

    public List<InvestorResponseDTO> getAllInvestors() {
        List<Investor> investors = investorRepository.findAll();
        return investors.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<InvestorResponseDTO> findById(Long id) {
        return investorRepository.findById(id).map(this::mapToResponseDTO);
    }

    public InvestorResponseDTO updateInvestor(Long id, InvestorRequestDTO requestDTO) {
        Investor investor = investorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor not found with ID: " + id));

        investor.setName(requestDTO.getName());
        investor.setEmail(requestDTO.getEmail());
        investor.setPhoneNumber(requestDTO.getPhoneNumber());
        investorRepository.save(investor);

        return mapToResponseDTO(investor);
    }

    public void deleteInvestor(Long id) {
        if (!investorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Investor not found with ID: " + id);
        }
        investorRepository.deleteById(id);
    }

    private InvestorResponseDTO mapToResponseDTO(Investor investor) {
        InvestorResponseDTO responseDTO = new InvestorResponseDTO();
        responseDTO.setId(investor.getId());
        responseDTO.setName(investor.getName());
        responseDTO.setEmail(investor.getEmail());
        responseDTO.setPhoneNumber(investor.getPhoneNumber());
        return responseDTO;
    }
}
