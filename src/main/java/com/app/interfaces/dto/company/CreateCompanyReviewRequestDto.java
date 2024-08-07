package com.app.interfaces.dto.company;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateCompanyReviewRequestDto {
    @NotBlank
    private Long company_id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String review;
    
    @NotBlank
    @Min(1)
    @Max(5)
    private Integer rating;
}