package shad.wegri.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shad.wegri.dto.BicycleSearchDto;
import shad.wegri.repository.PinRepository;
import shad.wegri.domain.Pin;

@AllArgsConstructor
@Service
public class BicycleService {
    private final PinRepository pinRepository;
    private List<BicycleSearchDto> bicycleSearchDtoList;

    public List<BicycleSearchDto> getBicyclePins() {
        List<Pin> pinList = pinRepository.findByMapId(1L);

        for (Pin row : pinList) {

            try {
                row.getIs_rent();
                bicycleSearchDtoList.add(new BicycleSearchDto(row.getId(), row.getDate(),
                    row.getLatitude(), row.getLongitude(), row.getImage(), row.getProvider(), row.getIs_rent()));

            } catch (Exception e) {
                bicycleSearchDtoList.add(new BicycleSearchDto(row.getId(), row.getDate(),
                    row.getLatitude(), row.getLongitude(), row.getImage(), row.getProvider()));
            }
        }

        return bicycleSearchDtoList;

//        return pinRepository.findByBicycleMapId(1L);
    }
}
