package com.mahproject.spring.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileModel, Integer> {
    ProfileModel findByUserId(int user_id);
}
