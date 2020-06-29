package mateacademy.amazonreviewsapp.entity.review;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mateacademy.amazonreviewsapp.entity.product.Product;
import mateacademy.amazonreviewsapp.entity.user.User;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "review")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(generator = "ExistingOrGenerated")
    @GenericGenerator(name = "ExistingOrGenerated",
            strategy = "mateacademy.amazonreviewsapp.util.UseExistingOrGenerateNumericIdGenerator")
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private LocalDateTime time;
    private String summary;
    @Lob
    private String text;
}
