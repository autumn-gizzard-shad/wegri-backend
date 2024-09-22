package shad.wegri.dto;

import lombok.*;
import shad.wegri.domain.Pin;

@Data
@RequiredArgsConstructor
@Setter
public class PinAddRequestDto {
    private final String pin_date;
    private final double pin_latitude;
    private final double pin_longitude;
    private final String pin_image;
    private long map_id;
    private String provider;
    private Boolean is_rent;

    public PinAddRequestDto() {
        this.pin_date = "20240921";
        this.pin_latitude = 0.0;
        this.pin_longitude = 0.0;
        this.pin_image = null;
    }

    public Pin toEntity() {
        return Pin.builder()
            .map_id(map_id)
            .date(pin_date)
            .latitude(pin_latitude)
            .longitude(pin_longitude)
            .image(pin_image)
            .provider(provider)
            .is_rent(is_rent)
            .build();
    }
}
