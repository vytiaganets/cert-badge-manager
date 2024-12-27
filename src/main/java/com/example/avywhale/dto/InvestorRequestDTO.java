package com.example.avywhale.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvestorRequestDTO {

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email must be valid.")
    private String email;

    @NotBlank(message = "Phone number cannot be blank.")
    @Pattern(regexp = "\\+?\\d{10,15}", message = "Phone number must be valid.")
    private String phoneNumber;

    @NotNull(message = "Investment amount cannot be null.")
    private BigDecimal investmentAmount;

    private String description;

}
