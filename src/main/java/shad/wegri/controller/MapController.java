package shad.wegri.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import shad.wegri.domain.Map;
import shad.wegri.domain.Pin;
import shad.wegri.dto.MapAddRequestDto;
import shad.wegri.dto.AddResponseDto;
import shad.wegri.dto.PinAddRequestDto;
import shad.wegri.service.BycicleService;
import shad.wegri.service.MapService;
import shad.wegri.service.PinService;

@RestController
@RequestMapping("/api/maps")
public class MapController {
    public int bicycleMap = 2;
    @Autowired
    MapService mapService;

    @Autowired
    PinService pinService;

    @Autowired
    BycicleService bycicleService;

    @PostMapping("/")
    public ResponseEntity<AddResponseDto> saveMap(@RequestBody MapAddRequestDto request) {
        Map savedMap = mapService.saveMap(request);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new AddResponseDto(HttpStatus.CREATED, "Map added successfully", savedMap.getId()));
    }

    @GetMapping("/more")
    public String searchMoreMap() { // 더보기 누를 경우 맵 추가
        return "더보기 연결 됨";
    }

    @GetMapping("/{map_id}/pins")
    public String searchPin(@PathVariable long map_id) { // 핀 조회
        return "핀 조회 연결됨";
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/{map_id}/pins")
    public ResponseEntity<AddResponseDto> addPin(@PathVariable long map_id,
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

