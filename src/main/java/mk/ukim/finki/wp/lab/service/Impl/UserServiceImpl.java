package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.model.enums.Role;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Users register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException();

        Users users = new Users(username, passwordEncoder.encode(password), name, surname, userRole);
        userRepository.save(users);
        return users;
    }

}
