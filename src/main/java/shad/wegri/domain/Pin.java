package shad.wegri.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity
@Getter
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 1씩 증가시키면서 id 생성
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "date")
    @NonNull
    private String date;

    @Column(name = "latitude")
    @NonNull
    private double latitude;

    @Column(name = "longitude")
    @NonNull
    private double longitude;

    @Column(name = "image")
    private String image;

    @Column(name = "provider")
    private String provider;

    @Column(name = "is_rent")
    private char is_rent;

    @Builder
    public Pin(long id, String date, double latitude, double longitude, String image, String provider, char is_rent) {
        this.id = id;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this. image = image;
        this.provider = provider;
        this.is_rent = is_rent;
    }
}
