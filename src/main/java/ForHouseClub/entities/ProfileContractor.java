package ForHouseClub.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "profile_companies")
public class ProfileContractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @OneToOne
    @JoinColumn(name = "lk_contractor_id")
    private LkContractor lkContractor;

    @OneToMany
    @JoinColumn(name = "specialization_id")
    @ToString.Exclude
    private List<Specialization> specializations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProfileContractor that = (ProfileContractor) o;
        return Objects.equals(profileId, that.profileId);
    }

    @Override
    public int hashCode() {
        return profileId != null ? profileId.hashCode() : 0;
    }
}
