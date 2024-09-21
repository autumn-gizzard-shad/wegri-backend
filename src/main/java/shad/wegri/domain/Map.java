package shad.wegri.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "desc")
    private String desc;

    @Column(name = "emoji")
    @NonNull
    private String emoji;

    @Builder
    public Map(long id, String title, String desc, String emoji){
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.emoji = emoji;
    }
}
