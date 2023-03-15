package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@Table(name = "user_university")
public class Users implements UserDetails {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public Users(String username, String password, String name, String surname, Role userRole) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userRole = userRole;
    }

    public Users() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(userRole);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
