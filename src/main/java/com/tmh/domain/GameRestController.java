package com.tmh.domain;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GameRestController {

    private final Logger logger = LoggerFactory
            .getLogger(GameRestController.class.getName());
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    @Autowired
    GameRestController(MatchRepository matchRepository,
            UserRepository userRepository) {
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/join/{gameName}/{username}")
    Match joinMatch(@PathVariable String gameName,
            @PathVariable String userName) {
        Match match = this.matchRepository.findByGameName(gameName);
        User user = new User(userName);
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
        if (user != null && weapon != null) {
            user.setWeapon(weapon);
            this.userRepository.save(user);
        }
        return ResponseEntity.ok(match);

    }

    // user api

    @RequestMapping(method = RequestMethod.GET, value = "/use   rs")
    ResponseEntity<List<User>> getAll() {
        List<User> user = this.userRepository.findAll();
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    ResponseEntity<User> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(this.userRepository.findById(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    ResponseEntity<User> createUser(@RequestBody User user) {
        this.userRepository.save(user);
        logger.debug("Created Name {}", user.getUserName());
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}")
    ResponseEntity<User> updateUser(@RequestBody Map<String, String> body,
            @PathVariable String userId) {
        logger.debug("Update for : {}", userId);
        User user = this.userRepository.findById(userId);
        user.setWeapon(body.get("weapon"));
        this.userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    ResponseEntity<String> removeUserById(@PathVariable String userId) {
        logger.debug("Remove for : {}", userId);
        User user = this.userRepository.findById(userId);
        String userName = user.getUserName();
        this.userRepository.delete(user);
        return ResponseEntity.ok(userName + " is deleted");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    ResponseEntity<User> loginUserByIdAndPassword(
            @RequestBody User user) {
        logger.debug("loginUserBy Name {} and password {}", user.getUserName(),
                user.getPassWord());
        User userInDatabase = this.userRepository
                .findByUserName(user.getUserName());
        if (userInDatabase != null) {
            if (userInDatabase.getPassWord() != null && userInDatabase
                    .getPassWord().equals(user.getPassWord())) {
                return ResponseEntity.ok(userInDatabase);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/logs")
    ResponseEntity<String> insertLogs(@RequestBody Map<Object, Object> body) {
        String level = "";
        String message = "";
        String timestamp = "";
        if (body.containsKey("level")) {
            level = (String) body.get("level");
        }
        if (body.containsKey("message")) {
            message = (String) body.get("message");
        }
        if (body.containsKey("timestamp")) {
            timestamp = (String) body.get("timestamp");
        }
        logger.debug("{} {} : {}", timestamp, level, message);
        return ResponseEntity.ok("message logged in backend");
    }
}