package uz.pdp.kichikproekt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userlar")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    @Column(unique = true,length = 9)
    private String phoneNumber;

    @Column(unique = true)
    private Integer code;

    private String password;

    private boolean active;

    @ManyToMany
    private Set<Warehouse> warehouse;
}
