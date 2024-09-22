package shad.wegri.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class PinAvailableSearchDto {
    private int pin_id;

    public PinAvailableSearchDto(int pin_id) {
        this.pin_id = pin_id;
    }
}
