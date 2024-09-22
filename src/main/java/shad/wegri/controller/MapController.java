package shad.wegri.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shad.wegri.argumentresolver.LoginMemberId;
import shad.wegri.domain.Map;
import shad.wegri.domain.Pin;
import shad.wegri.dto.AddResponseDto;
import shad.wegri.dto.MapAddRequestDto;
import shad.wegri.dto.MapSearchDto;
import shad.wegri.dto.MapSearchResponseDto;
import shad.wegri.dto.PinAddRequestDto;
import shad.wegri.dto.PinSearchResponseDto;
import shad.wegri.service.BycicleService;
import shad.wegri.service.MapService;
import shad.wegri.service.PinService;

@RestController
@RequestMapping("/api/maps")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final PinService pinService;
    private final BycicleService bycicleService;

    private List<MapSearchDto> mapList;

    // map add
    @PostMapping
    public ResponseEntity<AddResponseDto> saveMap(@RequestBody MapAddRequestDto request) {
        Map savedMap = mapService.saveMap(request);

        return ResponseEntity.ok().body(new AddResponseDto(HttpStatus.CREATED, "Map added successfully", savedMap.getId()));
    }

    // map search
    @GetMapping
    public ResponseEntity<MapSearchResponseDto> searchMap() {
        mapList = mapService.getAllMapsWithPinCount();
        List<MapSearchDto> results = mapList;
        if (mapList.size() > 4) {
            results = mapList.subList(0, 4);
        }

        return ResponseEntity.ok().body(new MapSearchResponseDto(HttpStatus.OK, "Map searched successfully", results));
    }

    @GetMapping("/more")
    public ResponseEntity<MapSearchResponseDto> searchMoreMap() { // 더보기 누를 경우 맵 추가
        List<MapSearchDto> results = null;
        if (mapList.size() > 4) {
            results = mapList.subList(4, mapList.size());
        }

        return ResponseEntity.ok().body(new MapSearchResponseDto(HttpStatus.OK, "More map searched successfully", results));
    }

    @GetMapping("/{map_id}/pins")
    public ResponseEntity<PinSearchResponseDto> searchPin(
        @PathVariable("map_id") long map_id) { // 핀 조회

        return ResponseEntity.ok().body(new PinSearchResponseDto(HttpStatus.OK, "Pin searched successfully", pinService.getPinsByMapId(map_id)));
    }

    @PostMapping("/{map_id}/pins")
    public ResponseEntity<AddResponseDto> savePin(@PathVariable("map_id") long map_id,
        @LoginMemberId String memberId, @RequestBody PinAddRequestDto request) { // 핀 추가
        Pin savedPin = pinService.savePin(map_id, memberId, request);
        return ResponseEntity.ok().body(new AddResponseDto(HttpStatus.OK, "Success", savedPin.getId()));
    }
}

