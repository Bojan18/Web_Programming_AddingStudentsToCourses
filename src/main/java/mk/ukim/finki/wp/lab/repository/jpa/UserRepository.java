package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String>{
    Optional<Users> findByUsernameAndPassword(String username, String password);
    Optional<Users> findByUsername(String username);
//    void save(Users users);
}
