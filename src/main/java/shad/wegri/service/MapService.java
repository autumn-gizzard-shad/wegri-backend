package shad.wegri.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shad.wegri.domain.Map;
import shad.wegri.dto.MapAddRequestDto;
import shad.wegri.dto.MapSearchDto;
import shad.wegri.repository.MapRepository;

@RequiredArgsConstructor
@Service
public class MapService {
    private final MapRepository mapRepository;

    public Map saveMap(MapAddRequestDto request) {
        return mapRepository.save(request.toEntity());
    }

    public List<MapSearchDto> getAllMapsWithPinCount() {
        List<Object[]> results = mapRepository.findAllMapsWithPinCount();
        List<MapSearchDto> mapSearchDtos = new ArrayList<>();

        long mapId;

        for (Object[] row : results) {
            if (row[0] instanceof Integer) {
                mapId = ((Integer) row[0]).longValue();
            } else {
                mapId = ((BigInteger) row[0]).longValue();
            }
            String title = (String) row[1];
            String desc = (String) row[2];
            String emoji = (String) row[3];
            int pCount = ((Long) row[4]).intValue();

            mapSearchDtos.add(new MapSearchDto(mapId, title, desc, emoji, pCount));
        }

        return mapSearchDtos;
    }
}
