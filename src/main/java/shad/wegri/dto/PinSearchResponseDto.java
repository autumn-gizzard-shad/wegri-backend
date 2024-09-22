package shad.wegri.dto;

import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class PinSearchResponseDto {
    private HttpStatus result;
    private String message;
    private List<PinSearchDto> pin_list;
}
