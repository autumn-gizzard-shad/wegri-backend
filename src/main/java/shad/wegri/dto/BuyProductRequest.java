package shad.wegri.dto;

import jakarta.validation.constraints.NotBlank;

public record BuyProductRequest(
    @NotBlank
    long product_id
) {

}
