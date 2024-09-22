package shad.wegri.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shad.wegri.dto.BicycleSearchDto;
import shad.wegri.repository.PinRepository;
import shad.wegri.domain.Pin;

@RequiredArgsConstructor
@Service
public class BicycleService {
    private Long BicycleMapId = 1L;
    private final PinRepository pinRepository;
    private List<BicycleSearchDto> bicycleSearchDtoList = new ArrayList<>();

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

    public Boolean availableBicycle(int pinId){
        long pinIdLong = pinId;

        if (pinRepository.findByPinIdAndMapId(pinIdLong, BicycleMapId) == 1) {
            pinRepository.updateIsRentByMapIdAndPinId(false, BicycleMapId, pinIdLong);
            return true;
        }
        else {
            return false;
        }
    }
}
