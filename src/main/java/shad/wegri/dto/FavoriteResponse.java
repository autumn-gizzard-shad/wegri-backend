package shad.wegri.dto;

public record FavoriteResponse(
    long favorite_id,
    long map_id,
    String map_title,
    String map_desc,
    String map_emoji,
    int pin_count
) {

}
