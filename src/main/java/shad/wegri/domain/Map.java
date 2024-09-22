package shad.wegri.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "Map")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "description")
    private String desc;

    @Column(name = "emoji")
    @NonNull
    private String emoji;
}
