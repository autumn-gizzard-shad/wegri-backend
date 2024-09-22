package shad.wegri.dto;


import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class AddResponseDto {
    private final HttpStatus result;
    private final String message;
    private Long id;

    public AddResponseDto(final HttpStatus status, final String message) {
        this.result = status;
        this.message = message;
        this.id = null;
    }
}
