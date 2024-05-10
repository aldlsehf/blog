package project1.sideproject.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
