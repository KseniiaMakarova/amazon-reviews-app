package mateacademy.amazonreviewsapp.entity.product;

import mateacademy.amazonreviewsapp.entity.product.dto.ProductReviewsNumberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT p.id AS id, COUNT(r) AS reviewsNumber "
            + "FROM Review r JOIN r.product p "
            + "GROUP BY p ORDER BY reviewsNumber DESC, id ASC")
    Page<ProductReviewsNumberDto> getMostReviewed(Pageable pageable);
}
