package shad.wegri.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
public class PinSearchDto {
    private String pin_date;
    private double pin_latitude;
    private double pin_longitude;
    private String pin_image;
}
