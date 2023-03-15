package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.model.enums.Role;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUserCredentialsExceptions;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new BadCredentialsException("Invalid Arguments");
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsExceptions::new);
    }

    @Override
    public Users register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new BadCredentialsException("Username or password is empty or null");
        }
        if(!password.equals(repeatPassword)) {
            throw new BadCredentialsException("Passwords do not match");
        }
        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
            throw new BadCredentialsException(String.format("Username with %s already exists", username));

        Users user = new Users(username, password, name, surname, userRole);
        userRepository.save(user);

        return user;
    }
}
