package com.app.interfaces.dto.companyReview;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

public class CreateCompanyReviewRequestDto {
    @NonNull
    private Long company_id;
    
    @NonNull
    @Size(min = 3, max = 255)
    private String title;    
    
    @NonNull
    @Size(min = 3, max = 255)
    private String review;
    
    @NonNull
    @Min(1)
    @Max(5)
    private Integer rating;
}