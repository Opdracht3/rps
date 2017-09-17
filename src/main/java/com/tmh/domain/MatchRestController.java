package com.tmh.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BookmarkRestController {


    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    @Autowired
    BookmarkRestController(MatchRepository matchRepository,
            UserRepository userRepository) {
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/join/{gameName}/{username}")
    Match joinMatch(@PathVariable String gameName,
            @PathVariable String username) {
        Match match = this.matchRepository.findByGameName(gameName);
        User user = new User(username);
        userRepository.save(user);
        match.addUser(user);
        this.matchRepository.save(match);
        return this.matchRepository.findByGameName(gameName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create/{gameName}")
    Match createMatch(@PathVariable String gameName) {
        Match match = new Match(gameName);
        this.matchRepository.save(match);
        return match;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{gameName}/status")
    String getStatus(@PathVariable String gameName) {
        Match match = this.matchRepository.findByGameName(gameName);
        if (match != null) {
            return match.status();
        } else {
            return "Game Not Found";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/games")
    List<Match> getGameList() {
        return this.matchRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{gameName}/{username}/{weapon}")
    ResponseEntity<Match> move(@PathVariable String gameName,
            @PathVariable String username, @PathVariable String weapon) {
        Match match = this.matchRepository.findByGameName(gameName);
        User user = this.userRepository.findByUserName(username);
        user.setWeapon(weapon);
        this.userRepository.save(user);
        return ResponseEntity.ok(match);

    } 
}