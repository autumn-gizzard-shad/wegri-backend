package shad.wegri.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BicycleSearchResponseDto {
    private HttpStatus result;
    private String message;
    private List<BicycleSearchDto> pin_List;
}
