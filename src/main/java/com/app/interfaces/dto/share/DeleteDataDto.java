package com.app.interfaces.dto.share;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteDataDto {
    @JsonProperty("id")
    private Long id;
}
