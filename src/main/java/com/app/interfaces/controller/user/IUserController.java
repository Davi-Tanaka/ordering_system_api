package com.app.interfaces.controller.user;

import com.app.application.service.jwt.JwtTokens;
import com.app.domain.database.entity.user.UserEntity;
import com.app.interfaces.dto.auth.UserAuthenticationDto;
import com.app.interfaces.dto.jwt.JwtAcessTokenPayloadDto;
import com.app.interfaces.dto.share.DeleteDataDto;
import com.app.interfaces.dto.user.CreateUserRequestDto;
import com.app.interfaces.dto.user.CreateUserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "User API endpoints")
public interface IUserController {

    @Operation(description = "Get user data by the ID in \"Authorization\" header token.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Successfully retrieved",
            content = @Content(
                schema = @Schema(implementation = UserEntity.class),
                mediaType = "application/json"
            )
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Unauthorized"
        )
    })
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserEntity> getAuthenticatedUserData();
    
    @Operation(description = "Signup user")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "User successfully created.",
            content = { 
                @Content(
                    schema = @Schema(implementation = CreateUserResponseDto.class),
                    mediaType = "application/json"
                )
            }
        ),
        @ApiResponse(responseCode = "409", description = "Information send already exist.")
    })
    public ResponseEntity<CreateUserResponseDto> signUp(CreateUserRequestDto body);

    @Operation(description = "Authenticate user")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "User is allowed to authenticate.",
            content = @Content(
                schema = @Schema(implementation = JwtTokens.class),
                mediaType = "application/json"
            )
        ),

        @ApiResponse(
            responseCode = "401", 
            description = "User does not have permission to authenticate.",
            content = @Content(
                mediaType = "application/json"
            )
        )
    })
    public ResponseEntity<JwtTokens> login(UserAuthenticationDto body);
    
    @Operation(description = "Delete user by user ID in \"Authorization\" header token.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User deleted sucesfully",
            content = @Content(
                schema = @Schema(implementation = DeleteDataDto.class),
                mediaType = "application/json"
            )
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Not Found"
        )
    })
    public ResponseEntity delete(JwtAcessTokenPayloadDto tokenObject);
}