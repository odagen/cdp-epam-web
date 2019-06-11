package com.epam.cdp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NotNull
    @NotBlank(message = "Country cannot be empty")
    private String country;

    @NotNull
    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotNull
    @NotBlank(message = "AddressLine1 cannot be empty")
    private String addressLine1;

    @NotBlank(message = "AddressLine2 cannot be empty")
    private String addressLine2;

    @NotBlank(message = "Postal code cannot be empty")
    @Pattern(regexp = "(?i)[A-Z0-9]{6}", message = "Postal code has invalid format")
    private String postalCode;

    @Email(message = "Specified email is invalid")
    private String email;
}
