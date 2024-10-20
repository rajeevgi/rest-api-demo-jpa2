package com.sprk.rest_api_demo_jpa2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
    name = "Response",
    description = "Schema to Hold Response"
)
public class ResponseDto<T> {

    @Schema(description = "status code", example = "200")
    private String statusCode;

    // private String statusMessage;

    @Schema(description = "Data", example = "Updated Successfully")
    private T data;
}
