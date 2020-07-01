package mateacademy.amazonreviewsapp.entity.user;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import mateacademy.amazonreviewsapp.entity.role.Role;
import mateacademy.amazonreviewsapp.util.UseExistingOrGenerateSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExistingOrGenerated")
    @GenericGenerator(name = "ExistingOrGenerated", strategy =
            "mateacademy.amazonreviewsapp.util.UseExistingOrGenerateSequenceIdGenerator",
            parameters = {
                    @Parameter(name =
                            UseExistingOrGenerateSequenceIdGenerator.VALUE_PREFIX_PARAMETER,
                            value = "U_"),
                    @Parameter(name =
                            UseExistingOrGenerateSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,
                            value = "%010d")})
    private String id;
    private String profileName;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
