package shad.wegri.dto;

import lombok.*;
import shad.wegri.domain.Map;

@AllArgsConstructor
@Getter
public class MapAddRequestDTO {

    private String map_title;
    private String map_desc;
    private String map_emoji;

    public Map toEntity() {
        return Map.builder()
            .title(map_title)
            .desc(map_desc)
            .emoji(map_emoji)
            .build();
    }
}
