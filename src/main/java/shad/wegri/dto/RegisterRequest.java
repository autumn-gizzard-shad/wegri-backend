package shad.wegri.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
    @NotBlank
    String member_id,

    @NotBlank
    String password,

    String image
) {

}
