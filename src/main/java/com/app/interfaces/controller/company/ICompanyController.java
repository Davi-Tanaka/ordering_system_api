package com.app.interfaces.controller.company;

import com.app.domain.database.entity.company.CompanyReviewEntity;
import com.app.interfaces.dto.companyReview.CreateCompanyReviewRequestDto;
import com.app.interfaces.dto.company.CreateCompanyResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface ICompanyController {

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            content = @Content(
                schema = @Schema(
                    implementation = CreateCompanyResponseDto.class
                )
            )
        )
    })
    public ResponseEntity<CreateCompanyResponseDto> signupCompany();

    
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201"
        )
    })
    public ResponseEntity updateCompanyData();
    
    
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(
                schema = @Schema(
                    implementation = CreateCompanyReviewRequestDto.class
                )
            )
        )
    })
    public ResponseEntity createReviewAboutCompany();

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(
                schema = @Schema(
                    implementation = CreateCompanyReviewRequestDto.class
                )
            )
        )
    })
    public ResponseEntity getReviewAboutCompany(Long company_id);
}
