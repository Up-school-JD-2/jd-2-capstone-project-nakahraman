package com.upschool.airport.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> {
    private int status;
    private boolean isSuccess;
    @Builder.Default
    private String error = "No message available.";
    private T data;
}
