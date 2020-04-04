package ru.sibdigital.chkcovid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrganisationDTO {
    @NotBlank
    @Size(min = 10, max = 12)
    private String itn;

    @NotBlank
    private String organizationName;

    @NotBlank
    private String lastname;

    @NotBlank
    private String firstname;

    private String patronymic;
}
