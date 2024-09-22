package shad.wegri.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shad.wegri.argumentresolver.LoginMemberId;
import shad.wegri.dto.FavoriteResponse;
import shad.wegri.service.FavoriteService;

@Controller
@RequestMapping("/api/members/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<FavoriteResponse>> getFavorites(@LoginMemberId String memberId) {
        return ResponseEntity.ok().body(favoriteService.getFavorites(memberId));
    }
}
