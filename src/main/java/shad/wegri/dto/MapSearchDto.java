package shad.wegri.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MapSearchDto {
    private long map_id;
    private String map_title;
    private String map_desc;
    private String map_emoji;
    private int pin_count;
}
