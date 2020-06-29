package mateacademy.amazonreviewsapp.entity.product.service;

import java.util.List;
import java.util.Set;
import mateacademy.amazonreviewsapp.entity.product.Product;
import mateacademy.amazonreviewsapp.entity.product.dto.ProductReviewsNumberDto;

public interface ProductService {
    List<Product> saveAll(Set<Product> products);

    List<ProductReviewsNumberDto> getMostReviewed(int page, int size);

    Product get(String id);
}
