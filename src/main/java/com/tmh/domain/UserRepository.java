package com.tmh.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    User findById(String id);

    User findByUserName(String userName);
}