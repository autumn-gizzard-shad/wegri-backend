package shad.wegri.service;

import java.util.List;
import lombok.*;
import org.springframework.stereotype.Service;
import shad.wegri.domain.Pin;
import shad.wegri.dto.PinAddRequestDto;
import shad.wegri.dto.PinSearchDto;
import shad.wegri.repository.PinRepository;

@Service
@AllArgsConstructor
public class PinService {
    private final PinRepository pinRepository;
    private List<PinSearchDto> pinSearchDto;

    public Pin savePin(PinAddRequestDto request) {
        return pinRepository.save(request.toEntity());
    }

    public List<PinSearchDto> getPinsByMapId(Long mapId) {
        List<Pin> pinList = pinRepository.findByMapId(mapId);

        for (Pin row : pinList) {
            pinSearchDto.add(new PinSearchDto(row.getDate(), row.getLatitude(), row.getLongitude(), row.getImage()));
        }

        return pinSearchDto;
    }
}
