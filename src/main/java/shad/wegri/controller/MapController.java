package shad.wegri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import shad.wegri.domain.Map;
import shad.wegri.dto.MapAddRequestDTO;
import shad.wegri.dto.MapResponseDto;
import shad.wegri.service.BycicleService;
import shad.wegri.service.MapService;

@RestController
//@RequestMapping("/api/maps")
public class MapController {

    @Autowired
    MapService mapService;

    @Autowired
    BycicleService bycicleService;

    @GetMapping("/")
    @ResponseBody
    public String searchMap() { // top4 맵 조회
        return "연결 됨";
    }

    @PostMapping("/")
    public ResponseEntity<MapResponseDto> saveMap(@RequestBody MapAddRequestDTO request) {
        Map savedMap = mapService.saveMap(request);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new MapResponseDto(HttpStatus.CREATED, "Map added successfully", savedMap.getId()));
    }

    @GetMapping("/more")
    public String searchMoreMap() { // 더보기 누를 경우 맵 추가
        return "더보기 연결 됨";
    }

    @GetMapping("/{map_id}/pins")
    public String searchPin(@PathVariable long map_id) { // 핀 조회
        return "핀 조회 연결됨";
    }

    @PostMapping("/{map_id}/pins")
    public String addPin(@PathVariable long map_id) { // 핀 추가
        return "핀 추가 연결됨";
    }
}

