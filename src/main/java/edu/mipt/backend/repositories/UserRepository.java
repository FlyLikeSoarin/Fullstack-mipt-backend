package edu.mipt.backend.repositories;

import edu.mipt.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByUsername(String username);
    User findOneByEmail(String email);
//    User addRoomByUserName(String username);

}
