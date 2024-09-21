package shad.wegri.dto;


import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class MapResponseDto {
    private final HttpStatus status;
    private final String message;
    private Long id;

    public MapResponseDto(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
        this.id = null;
    }
}
