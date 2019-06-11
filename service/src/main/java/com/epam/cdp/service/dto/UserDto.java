package com.epam.cdp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class UserDto {

    @NotNull
    @Size(min = 1, max = 3)
    private String userId;

    @NotNull
    @Size(min = 3, max = 7)
    @Pattern(regexp = "(?i)[A-Z]+")
    private String firstName;

    @NotNull
    @Pattern(regexp = "(?i)[A-Z]+")
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Valid
    @NotNull
    private AddressDto address;
}
