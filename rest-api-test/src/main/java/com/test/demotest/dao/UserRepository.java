package com.test.demotest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demotest.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
