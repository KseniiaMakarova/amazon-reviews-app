package mateacademy.amazonreviewsapp.entity.word;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "word")
@Data
public class Word {
    @Id
    private String value;
    private int useCount;
}
