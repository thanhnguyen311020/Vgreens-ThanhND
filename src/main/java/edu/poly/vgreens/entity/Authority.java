package edu.poly.vgreens.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"Username", "Roleid"})
})
public class Authority  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "Username")
    private Account account;
    @ManyToOne  @JoinColumn(name = "Roleid")
    private Role role;
}