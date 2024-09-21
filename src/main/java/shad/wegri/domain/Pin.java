package shad.wegri.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "PIN")
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 1씩 증가시키면서 id 생성
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "map_id")
    private long map_id;

    @Column(name = "date")
    private final String date;

    @Column(name = "latitude")
    private final double latitude;

    @Column(name = "longitude")
    private final double longitude;

    @Column(name = "image")
    private String image;

    @Column(name = "provider")
    private String provider;

    @Column(name = "is_rent")
    private Boolean is_rent;

    @Builder
    public Pin(long map_id, String date, double latitude, double longitude, String image, String provider, Boolean is_rent) {
        this.map_id = map_id;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
        this.provider = provider;
        this.is_rent = is_rent;
    }
}
