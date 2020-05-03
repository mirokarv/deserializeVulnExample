package com.mahproject.spring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    // did you except something to implement this? get rekt. hint decorator might do it
    UserModel findByUsername(String username);
    UserModel findById(int id);
    List<UserModel> findAll();
}
