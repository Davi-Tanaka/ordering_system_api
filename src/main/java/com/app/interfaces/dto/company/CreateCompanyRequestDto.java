package com.app.interfaces.dto.company;

import com.app.domain.database.entity.company.CompanyContactEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateCompanyRequestDto {

    @NotBlank
    @Size(
        min = 2, max = 25,
        message = "Company name must contain at least two non special characters, and 25 maximum."
    )
    @JsonProperty("name")
    private String name;

    @NotBlank
    @Size(
        min = 10, 
        max = 255, 
        message = "Description must contain at least 10 character, and 255 maximum."
    )
    @JsonProperty("description")
    private String description;
    
    @NotBlank
    @JsonProperty("contacts")
    private List<CompanyContactEntity> contacts;    
}
