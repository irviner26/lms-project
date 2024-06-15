package com.smkr.lms.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse<T> {
    private Integer code;
    private String message;
    private String error;
    private T data;
}
