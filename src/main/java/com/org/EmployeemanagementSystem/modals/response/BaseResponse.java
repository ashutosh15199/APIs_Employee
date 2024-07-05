package com.org.EmployeemanagementSystem.modals.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.*;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  BaseResponse<T> {

    @Schema(description = "Response Data")
    private T data;
    @Schema(description = "HTTP Status code")
    private Integer status;
    @Schema(description = "Response Message")
    private String message;
    @Schema(description = "Response Info in case of error")
    private String info;


    public BaseResponse() {
        this.data = null;
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = HttpStatus.BAD_REQUEST.getReasonPhrase();
    }

    public BaseResponse(T data) {
        this();
        this.data = data;
        this.status = HttpStatus.OK.value();
        this.message = " ";
    }
    public BaseResponse(T data, Integer status, String message) {
        this(data);
        this.status = status;
        this.message = message;
    }
    public BaseResponse(T data, Integer status, String message, String info) {
        this(data, status, message);
        this.info = info;
    }
}
