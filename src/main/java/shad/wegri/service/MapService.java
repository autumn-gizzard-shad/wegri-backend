package shad.wegri.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shad.wegri.domain.Map;
import shad.wegri.dto.MapAddRequestDTO;
import shad.wegri.repository.MapRepository;

@RequiredArgsConstructor
@Service
public class MapService {
    private final MapRepository mapRepository;

    public Map saveMap(MapAddRequestDTO request) {
        return mapRepository.save(request.toEntity());
    }
}
