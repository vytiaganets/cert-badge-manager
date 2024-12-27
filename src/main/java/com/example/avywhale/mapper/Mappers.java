package com.example.avywhale.mapper;

import com.example.avywhale.dto.*;
import com.example.avywhale.model.*;
import org.modelmapper.ModelMapper;
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

        public TokenomicsResponseDTO toTokenomicsResponseDTO(Tokenomics tokenomics) {
        return modelMapper.map(tokenomics, TokenomicsResponseDTO.class);
    }

    public Tokenomics toTokenomicsEntity(TokenomicsRequestDTO tokenomicsRequestDTO) {
        return modelMapper.map(tokenomicsRequestDTO, Tokenomics.class);
    }

        public UserResponseDTO toUserResponseDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public User toUserEntity(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }

        public NFTResponseDTO toNFTResponseDTO(NFT nft) {
        return modelMapper.map(nft, NFTResponseDTO.class);
    }

    public NFT toNFTEntity(NFTRequestDTO nftRequestDTO) {
        return modelMapper.map(nftRequestDTO, NFT.class);
    }

        public GameAchievementResponseDTO toGameAchievementResponseDTO(GameAchievement gameAchievement) {
        return modelMapper.map(gameAchievement, GameAchievementResponseDTO.class);
    }

    public GameAchievement toGameAchievementEntity(GameAchievementRequestDTO gameAchievementRequestDTO) {
        return modelMapper.map(gameAchievementRequestDTO, GameAchievement.class);
    }

        public RewardResponseDTO toRewardResponseDTO(Reward reward) {
        return modelMapper.map(reward, RewardResponseDTO.class);
    }

    public Reward toRewardEntity(RewardRequestDTO rewardRequestDTO) {
        return modelMapper.map(rewardRequestDTO, Reward.class);
    }

        public InvestmentAllocationResponseDTO toInvestmentAllocationResponseDTO(InvestmentAllocation investmentAllocation) {
        return modelMapper.map(investmentAllocation, InvestmentAllocationResponseDTO.class);
    }

    public InvestmentAllocation toInvestmentAllocationEntity(InvestmentAllocationRequestDTO investmentAllocationRequestDTO) {
        return modelMapper.map(investmentAllocationRequestDTO, InvestmentAllocation.class);
    }

        public InvestorResponseDTO toInvestorResponseDTO(Investor investor) {
        return modelMapper.map(investor, InvestorResponseDTO.class);
    }

    public Investor toInvestorEntity(InvestorRequestDTO investorRequestDTO) {
        return modelMapper.map(investorRequestDTO, Investor.class);
    }

        public TokenResponseDTO toTokenResponseDTO(Token token) {
        return modelMapper.map(token, TokenResponseDTO.class);
    }

    public Token toTokenEntity(TokenRequestDTO tokenRequestDTO) {
        return modelMapper.map(tokenRequestDTO, Token.class);
    }

        public InvestmentResponseDTO toInvestmentResponseDTO(Investment investment) {
        return modelMapper.map(investment, InvestmentResponseDTO.class);
    }

    public Investment toInvestmentEntity(InvestmentRequestDTO investmentRequestDTO) {
        return modelMapper.map(investmentRequestDTO, Investment.class);
    }

        public AuthResponseDTO toAuthResponseDTO(AuthRequest authRequest) {
        return modelMapper.map(authRequest, AuthResponseDTO.class);
    }

    public AuthRequest toAuthRequestEntity(AuthRequestDTO authRequestDTO) {
        return modelMapper.map(authRequestDTO, AuthRequest.class);
    }
}
