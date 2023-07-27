package com.employee.Group.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="groups")
public class AppGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String token;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private AppUser owner;
}
