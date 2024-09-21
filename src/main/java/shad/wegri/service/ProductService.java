package shad.wegri.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shad.wegri.dto.ProductResponse;
import shad.wegri.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
            .stream()
            .filter(product -> product.getMember() == null)
            .map(product -> product.toResponse())
            .toList();
    }
}
