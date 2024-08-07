package com.app.interfaces.dto.company;

import com.app.domain.database.entity.company.CompanyContactEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UpdateCompanyRequestDto {
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("contacts")
    private List<CompanyContactEntity> contacts;    
    
    static record ResponseDto (
        @JsonProperty("id") Long id
    ) {} 
}
