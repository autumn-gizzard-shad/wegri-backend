package shad.wegri.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import shad.wegri.domain.Map;
import shad.wegri.domain.Pin;
import shad.wegri.dto.MapAddRequestDto;
import shad.wegri.dto.AddResponseDto;
import shad.wegri.dto.MapSearchDto;
import shad.wegri.dto.MapSearchResponseDto;
import shad.wegri.dto.PinAddRequestDto;
import shad.wegri.dto.PinSearchDto;
import shad.wegri.dto.PinSearchResponseDto;
import shad.wegri.service.BycicleService;
import shad.wegri.service.MapService;
import shad.wegri.service.PinService;

@RestController
@RequestMapping("/api/maps")
public class MapController {
    public int bicycleMap = 1;
    private List<MapSearchDto> mapList;

    @Autowired
    MapService mapService;

    @Autowired
    PinService pinService;

    @Autowired
    BycicleService bycicleService;



    // map add
    @PostMapping("/")
    public ResponseEntity<AddResponseDto> saveMap(@RequestBody MapAddRequestDto request) {
        Map savedMap = mapService.saveMap(request);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new AddResponseDto(HttpStatus.CREATED, "Map added successfully", savedMap.getId()));
    }

    // map search
    @GetMapping("/")
    public ResponseEntity<MapSearchResponseDto> searchMap() {
        mapList = mapService.getAllMapsWithPinCount();
        List<MapSearchDto> results;
        if (mapList.size() > 4) results = mapList.subList(0,4);
        else results = mapList;

        return ResponseEntity.status(HttpStatus.OK)
            .body(new MapSearchResponseDto(HttpStatus.OK, "Map searched successfully", results));
    }

    @GetMapping("/more")
    public ResponseEntity<MapSearchResponseDto> searchMoreMap() { // 더보기 누를 경우 맵 추가

        return ResponseEntity.status(HttpStatus.OK)
            .body(new MapSearchResponseDto(HttpStatus.OK, "More map searched successfully", mapList));
    }

    @GetMapping("/{map_id}/pins")
    public ResponseEntity<PinSearchResponseDto> searchPin(@PathVariable("map_id") long map_id) { // 핀 조회

        return ResponseEntity.status(HttpStatus.OK)
            .body(new PinSearchResponseDto(HttpStatus.OK, "Pin searched successfully", pinService.getPinsByMapId(map_id)));
    }

    @PostMapping("/{map_id}/pins")
    public ResponseEntity<AddResponseDto> savePin(@PathVariable("map_id") long map_id,
                                        @RequestBody PinAddRequestDto request) { // 핀 추가
        request.setMap_id(map_id);
        if (map_id == bicycleMap) {
            request.setProvider("토큰으로 저장");
            request.setIs_rent(true);
        }
        else {
            request.setProvider(null);
            request.setIs_rent(null);
        }

        Pin savedPin = pinService.savePin(request);

        return ResponseEntity.status(HttpStatus.OK)
            .body(new AddResponseDto(HttpStatus.OK, "Success", savedPin.getId()));
    }
}

