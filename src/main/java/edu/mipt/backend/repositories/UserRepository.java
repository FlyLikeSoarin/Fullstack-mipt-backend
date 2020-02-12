package edu.mipt.backend.repositories;

import edu.mipt.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    User findOneById(Long id);
    User findOneByUsername(String username);
    User findOneByEmail(String email);
    User findOneByUsernameAndPasswordEquals(String username, String password);
    Optional<User> findByToken(String token);
//    User addRoomByUserName(String username);

}
