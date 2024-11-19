package com.example.solana.certbadgemanager.mapper;

import com.example.solana.certbadgemanager.dto.*;
import com.example.solana.certbadgemanager.model.Badge;
import com.example.solana.certbadgemanager.model.Certificate;
import com.example.solana.certbadgemanager.model.Tokenomics;
import com.example.solana.certbadgemanager.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mappers {

    private final ModelMapper modelMapper;

    public Mappers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BadgeResponseDTO toBadgeResponseDto(Badge badge) {
        return modelMapper.map(badge, BadgeResponseDTO.class);
    }


    public Badge toBadgeEntity(BadgeRequestDTO badgeRequestDTO) {
        return modelMapper.map(badgeRequestDTO, Badge.class);
    }


    public CertificateResponseDTO toCertificateResponseDto(Certificate certificate) {
        return modelMapper.map(certificate, CertificateResponseDTO.class);
    }


    public Certificate toCertificateEntity(CertificateRequestDTO certificateRequestDTO) {
        return modelMapper.map(certificateRequestDTO, Certificate.class);
    }


    public UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }


    public User toUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public CertificateResponseDTO toCertificateResponseDTO(Certificate certificate) {
        return modelMapper.map(certificate, CertificateResponseDTO.class);
    }

    public Tokenomics toEntity(TokenomicsRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Tokenomics.class);
    }

    public TokenomicsResponseDTO toResponseDTO(Tokenomics tokenomics) {
        return modelMapper.map(tokenomics, TokenomicsResponseDTO.class);
    }
}
