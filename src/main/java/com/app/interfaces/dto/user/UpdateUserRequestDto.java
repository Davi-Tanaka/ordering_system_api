package com.app.interfaces.dto.user;

import com.app.domain.database.entity.user.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigInteger;

@Schema(name = "Update User Request Dto.")
public class UpdateUserRequestDto extends UserEntity {
    public void setId(BigInteger id) {}
    
    @Schema(name = "Update User Response Dto.")
    static public record ResponseDto(BigInteger id) {}
}