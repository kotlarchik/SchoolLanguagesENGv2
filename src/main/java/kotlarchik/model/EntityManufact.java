package kotlarchik.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manufacturer")
public class EntityManufact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name", nullable = false)
    private  String name;

    @Column(name = "LastVisitDate", nullable = false)
    private Date lastVisitDate;

    @OneToMany(mappedBy = "entityManufact", fetch = FetchType.EAGER)
    private Set<EntityProduct> productSet;

    @Override
    public String toString() {
        return "EntityManufact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastVisitDate=" + lastVisitDate +
                '}';
    }
}
