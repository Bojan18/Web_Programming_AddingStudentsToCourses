package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.model.enums.Role;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.Impl.UserServiceImpl;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
//        String username, String password, String name, String surname, Role userRole
        Users user = new Users("username", "password", "name", "surname", Role.ROLE_USER);

        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        userService = Mockito.spy(new UserServiceImpl(userRepository, passwordEncoder));
    }

    @Test
    public void testSuccessRegister(){
        Users user = this.userService.register("username", "password", "password", "name", "surname", Role.ROLE_USER);

        Mockito.verify(this.userService).register("username", "password", "password", "name", "surname", Role.ROLE_USER);

        //prvo kazuvame koja e porakata
        Assert.assertNotNull("User is null", user);

        Assert.assertEquals("name do not match", "name", user.getName());
        Assert.assertEquals("surname do not match", "surname",user.getSurname());
        Assert.assertEquals("username do not match", "username",user.getUsername());
        Assert.assertEquals("password do not match", "password",user.getPassword());
        Assert.assertEquals("role do not mach", Role.ROLE_USER, user.getUserRole());
    }
    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.userService.register(null, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userService).register(null, "password", "password", "name", "surename", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.userService.register(username, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userService).register(username, "password", "password", "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.userService.register(username, password, "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userService).register(username, password, "password", "name", "surename", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.userService.register(username, password, "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userService).register(username, password, "password", "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.userService.register(username, password, confirmPassword, "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userService).register(username, password, confirmPassword, "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testDuplicateUsername() {
        Users user = new Users("username", "password", "name", "surename", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.userService.register(username, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userService).register(username, "password", "password", "name", "surename", Role.ROLE_USER);
    }
}
