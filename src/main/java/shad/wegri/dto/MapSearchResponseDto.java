package shad.wegri.dto;

import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class MapSearchResponseDto {
    private List<MapSearchDto> map_list;
}
