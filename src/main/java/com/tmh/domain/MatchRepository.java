package com.tmh.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Match findById(String id);

    Match findByGameName(String gameName);
}

