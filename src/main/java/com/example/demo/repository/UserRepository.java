package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ERole;
import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    
    Boolean existsByEmail(String email);

    
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name=:x")
    public List<User> findAll(@Param("x")ERole role);


}
