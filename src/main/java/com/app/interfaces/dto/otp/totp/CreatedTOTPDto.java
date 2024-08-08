package com.app.interfaces.dto.otp.totp;

import java.util.UUID;
import lombok.Data;

@Data
public class CreatedTOTPDto {
    private final UUID id;
    private final String value;
}
