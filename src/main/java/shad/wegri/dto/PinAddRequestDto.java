package shad.wegri.dto;

import shad.wegri.domain.Pin;

public record PinAddRequestDto(
    String pin_date,
    double pin_latitude,
    double pin_longitude,
    String pin_image
) {

    public Pin toEntity() {
        return Pin.builder()
            .date(pin_date)
            .latitude(pin_latitude)
            .longitude(pin_longitude)
            .image(pin_image)
            .build();
    }
}
