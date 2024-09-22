package shad.wegri.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shad.wegri.domain.Favorite;
import shad.wegri.dto.FavoriteResponse;
import shad.wegri.repository.FavoriteRepository;
import shad.wegri.repository.PinRepository;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final PinRepository pinRepository;

    public List<FavoriteResponse> getFavorites(String memberId) {
        List<Favorite> favorites = favoriteRepository.findByMemberId(memberId);
        return favorites.stream()
            .map(favorite -> favorite.toResponse(pinRepository.findByMap(favorite.getMap()).size()))
            .toList();
    }
}
