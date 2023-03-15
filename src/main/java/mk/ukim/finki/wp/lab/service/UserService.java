package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Users register(String username, String password, String repeatPassword, String name, String surname, Role userRole);
}
