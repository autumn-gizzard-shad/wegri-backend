package shad.wegri.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shad.wegri.domain.Map;
import shad.wegri.domain.Pin;
import shad.wegri.dto.PinAddRequestDto;
import shad.wegri.dto.PinSearchDto;
import shad.wegri.repository.MapRepository;
import shad.wegri.repository.PinRepository;

@Service
@AllArgsConstructor
public class PinService {

    private final PinRepository pinRepository;
    private final MapRepository mapRepository;

    private static final int BICYCLE_MAP = 1;

    public Pin savePin(long mapId, String memberId, PinAddRequestDto request) {
        Pin pin = request.toEntity();
        Map map = mapRepository.findById(mapId).get();
        pin.setMap(map);
        if(mapId == BICYCLE_MAP) {
            pin.setProvider(memberId);
        }
        return pinRepository.save(pin);
    }

    public List<PinSearchDto> getPinsByMapId(long mapId) {
        Map map = mapRepository.findById(mapId).get();
        return pinRepository.findByMap(map)
            .stream()
            .map(pin -> pin.toSearchDto())
            .toList();
    }
}
