package com.tmh.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(String id);

    User findByUserName(String userName);

}

