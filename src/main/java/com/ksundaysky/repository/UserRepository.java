package com.ksundaysky.repository;

import com.ksundaysky.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
   // List<User> findUsersByRole_id(int id);
   // List<User> findAllByRole_id(int role_id);
    //User findByRole_id(int id);
    User findById(int id);
    @Transactional
    Long deleteById(int id);

}