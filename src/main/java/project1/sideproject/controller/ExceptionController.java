package project1.sideproject.controller;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project1.sideproject.dto.responseDto.ErrorResponseDto;

import java.util.List;

@RestControllerAdvice
public class ExceptionController {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto invalidRequestHandler(MethodArgumentNotValidException e) {
        //ErrorResponseDto errorResponseDto = new ErrorResponseDto("400", "잘못된 요청입니다.");
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        List<FieldError> fieldErrors = e.getFieldErrors();


        for (FieldError fieldError : fieldErrors) {
            errorResponseDto.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return errorResponseDto;

    }

}
