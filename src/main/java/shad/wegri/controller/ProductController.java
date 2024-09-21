package shad.wegri.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shad.wegri.argumentresolver.LoginMemberId;
import shad.wegri.dto.ProductResponse;
import shad.wegri.dto.BuyProductRequest;
import shad.wegri.service.ProductService;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity buyProduct(@LoginMemberId String memberId, @RequestBody BuyProductRequest buyProductRequest) {
        productService.buyProduct(memberId, buyProductRequest);
        return ResponseEntity.ok().build();
    }
}
