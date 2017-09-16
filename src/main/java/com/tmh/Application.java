package com.tmh;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tmh.domain.Match;
import com.tmh.domain.MatchRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(MatchRepository matchRepository) {
        return (evt) -> Arrays.asList(
                "Game1,Game2,Game3,Game4,Game5,Game6,Game7,Game8"
                        .split(","))
                .forEach(a -> {
                    matchRepository
                            .save(new Match(a));
                });
    }

}