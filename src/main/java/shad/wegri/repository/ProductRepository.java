package shad.wegri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shad.wegri.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
