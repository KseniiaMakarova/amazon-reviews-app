package mateacademy.amazonreviewsapp.entity.product;

import java.util.List;
import mateacademy.amazonreviewsapp.entity.product.dto.ProductReviewsNumberDto;
import mateacademy.amazonreviewsapp.entity.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/most-reviewed")
    public List<ProductReviewsNumberDto> getMostReviewedProducts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "1000") int size) {
        return productService.getMostReviewed(page, size);
    }
}
