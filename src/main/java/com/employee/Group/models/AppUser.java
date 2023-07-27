package com.employee.Group.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date lastChanges;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    @JoinColumn(name = "in_group", referencedColumnName = "id")
    private AppGroup inGroup;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(getRole().getRoleName());
        return Collections.singletonList(simpleGrantedAuthority);
    }
}
