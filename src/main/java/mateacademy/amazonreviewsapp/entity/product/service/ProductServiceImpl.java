package mateacademy.amazonreviewsapp.entity.product.service;

import java.util.List;
import java.util.Set;
import mateacademy.amazonreviewsapp.entity.product.Product;
import mateacademy.amazonreviewsapp.entity.product.ProductRepository;
import mateacademy.amazonreviewsapp.entity.product.dto.ProductReviewsNumberDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<Product> saveAll(Set<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<ProductReviewsNumberDto> getMostReviewed(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.getMostReviewed(pageable).toList();
    }

    @Override
    public Product get(String id) {
        return productRepository.getOne(id);
    }
}
