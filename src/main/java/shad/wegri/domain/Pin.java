package shad.wegri.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import shad.wegri.dto.PinSearchDto;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Pin")
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
    private Boolean is_rent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id")
    private Map map;

    public PinSearchDto toSearchDto() {
        return new PinSearchDto(date, latitude, longitude, image);
    }
}
