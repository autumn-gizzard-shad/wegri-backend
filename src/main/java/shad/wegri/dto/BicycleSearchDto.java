package shad.wegri.dto;

import lombok.*;
import shad.wegri.repository.PinRepository;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BicycleSearchDto {
    private Long pin_id;
    private String pin_date;
    private double pin_latitude;
    private double pin_longitude;
    private String pin_image;
    private String pin_provider;
    private boolean pin_is_rent;

    public BicycleSearchDto(Long id, String date, double latitude, double longitude, String image, String provider, boolean is_rent){
        this.pin_id = id;
        this.pin_date = date;
        this.pin_latitude = latitude;
        this.pin_longitude = longitude;
        this.pin_image = image;
        this.pin_provider = provider;
        this.pin_is_rent = is_rent;
    }

    public BicycleSearchDto(Long id, String date, double latitude, double longitude, String image, String provider){
        this.pin_id = id;
        this.pin_date = date;
        this.pin_latitude = latitude;
        this.pin_longitude = longitude;
        this.pin_image = image;
        this.pin_provider = provider;
    }
}
