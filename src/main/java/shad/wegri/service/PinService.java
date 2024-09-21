package shad.wegri.service;

import lombok.*;
import org.springframework.stereotype.Service;
import shad.wegri.domain.Pin;
import shad.wegri.dto.PinAddRequestDto;
import shad.wegri.repository.PinRepository;

@Service
@AllArgsConstructor
public class PinService {
    private final PinRepository pinRepository;

    public Pin savePin(PinAddRequestDto request) {
        return pinRepository.save(request.toEntity());
    }

}
