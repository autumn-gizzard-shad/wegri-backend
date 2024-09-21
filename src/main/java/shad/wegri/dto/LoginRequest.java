package shad.wegri.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank
    String member_id,

    @NotBlank
    String password
) {

}
