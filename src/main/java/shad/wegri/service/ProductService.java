package shad.wegri.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shad.wegri.domain.Member;
import shad.wegri.domain.Product;
import shad.wegri.dto.BuyProductRequest;
import shad.wegri.dto.ProductResponse;
import shad.wegri.exception.NoSuchMemberException;
import shad.wegri.exception.NoSuchProductException;
import shad.wegri.repository.MemberRepository;
import shad.wegri.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
            .stream()
            .filter(product -> product.getMember() == null)
            .map(product -> product.toResponse())
            .toList();
    }

    public void buyProduct(String memberId, BuyProductRequest buyProductRequest){
        Product product = productRepository.findById(buyProductRequest.product_id())
            .orElseThrow(NoSuchProductException::new);
        Member member = memberRepository.findById(memberId)
            .orElseThrow(NoSuchMemberException::new);
        if(member.getPoint() >= product.getPrice()) {
            member.setPoint(member.getPoint()-product.getPrice());
            product.setMember(member);
            productRepository.save(product);
        }
    }
}
