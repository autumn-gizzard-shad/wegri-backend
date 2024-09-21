package shad.wegri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shad.wegri.service.MapService;

@RestController("/api/maps")
public class MapController {

    @Autowired
    MapService mapService;

    @GetMapping("/")
    public void searchMap() { // top4 맵 조회

    }

    @PostMapping("/")
    public void mapAdd() { // 맵 추가

    }

    @GetMapping("/more")
    public void searchMoreMap() { // 더보기 누를 경우 맵 추가

    }

    @GetMapping("/{map_id}/pins")
    public void searchPin(@PathVariable long map_id) { // 핀 조회

    }

    @PostMapping("/{map_id}/pins")
    public void addPin(@PathVariable long map_id) { // 핀 추가

    }
}
