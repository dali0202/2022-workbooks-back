package com.example.back.exception.dto;

import com.example.back.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private String code;
    private List<FieldError> errors;

    private ErrorResponse(ErrorCode code, List<FieldError> errors) {
        this.message = code.getMessage();
        this.code = code.getCode();
        this.errors = errors;
    }
    private ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.code = code.getCode();
        this.errors = new ArrayList<>();
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }
    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    private static class FieldError {
        private String field;
        private String value;
        private String message;

        private FieldError(String field, String value, String message) {
            this.field = field;
            this.value = value;
            this.message = message;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream().map(error -> new FieldError(error.getField(), error.getRejectedValue().toString(), error.getDefaultMessage())).collect(Collectors.toList());
        }
    }
}
