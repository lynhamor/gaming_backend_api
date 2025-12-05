package com.api.auth.dto;

import com.api.auth.constants.StatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseDto {

    private String message;
    private StatusType statusType;
    private Object data;
    private Long timeMills;

    @JsonIgnore
    public ResponseEntity<ResponseDto> getResponseEntity() {
        this.timeMills = System.currentTimeMillis();
        HttpStatus httpStatus = switch (statusType) {
            case SUCCESS, WARNING, INFO -> HttpStatus.OK;
            case ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case INVALID -> HttpStatus.BAD_REQUEST;
        };
        return new ResponseEntity<>(this, httpStatus);
    }


    @JsonIgnore
    public static ResponseDto success(String message) {
        return success(message, null);
    }
    @JsonIgnore
    public static ResponseDto success(String message, Object data) {
        return ResponseDto.builder()
                .statusType(StatusType.SUCCESS)
                .message(message)
                .data(data)
                .build();
    }
    @JsonIgnore
    public static ResponseDto error(String message) {
        return error(message, null);
    }
    @JsonIgnore
    public static ResponseDto error(String message, Object data) {
        return ResponseDto.builder()
                .statusType(StatusType.ERROR)
                .message(message)
                .data(data)
                .build();
    }
    @JsonIgnore
    public static ResponseDto invalid(String message) {
        return invalid(message, null);
    }
    @JsonIgnore
    public static ResponseDto invalid(String message, Object data) {
        return ResponseDto.builder()
                .statusType(StatusType.INVALID)
                .message(message)
                .data(data)
                .build();
    }
    @JsonIgnore
    public static ResponseDto unauthorized(String message) {
        return unauthorized(message, null);
    }
    @JsonIgnore
    public static ResponseDto unauthorized(String message, Object data) {
        return ResponseDto.builder()
                .statusType(StatusType.UNAUTHORIZED)
                .message(message)
                .data(data)
                .build();
    }
    @JsonIgnore
    public static ResponseDto forbidden(String message) {
        return forbidden(message, null);
    }
    @JsonIgnore
    public static ResponseDto forbidden(String message, Object data) {
        return ResponseDto.builder()
                .statusType(StatusType.FORBIDDEN)
                .message(message)
                .data(data)
                .build();
    }
    @JsonIgnore
    public static ResponseDto warning(String message) {
        return warning(message, null);
    }
    @JsonIgnore
    public static ResponseDto warning(String message, Object data) {
        return ResponseDto.builder()
                .statusType(StatusType.WARNING)
                .message(message)
                .data(data)
                .build();
    }
    @JsonIgnore
    public static ResponseDto info(String message) {
        return info(message, null);
    }
    @JsonIgnore
    public static ResponseDto info(String message, Object data) {
        return ResponseDto.builder()
                .statusType(StatusType.INFO)
                .message(message)
                .data(data)
                .build();
    }
}
