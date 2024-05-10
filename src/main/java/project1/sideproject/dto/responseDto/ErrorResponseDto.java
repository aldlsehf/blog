package project1.sideproject.dto.responseDto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponseDto {

    private String code;
    private String message;
    final Map<String, String> validation = new HashMap<>();

    public ErrorResponseDto() {

    }

    @Builder
    public ErrorResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void addValidation(String field, String defaltMessage) {
        validation.put(field, defaltMessage);
    }
}
