package shad.wegri.dto;

public record ProductResponse(
    long product_id,
    String product_name,
    String product_image,
    String product_desc,
    int product_price
) {

}
