package com.tmh.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BookmarkRestController {


    private final MatchRepository matchRepository;

    @Autowired
    BookmarkRestController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{gameName}")
    Match readMatch(@PathVariable String gameName) {
        return this.matchRepository.findByGameName(gameName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{gameName}/{username}")
    ResponseEntity<Match> move(@PathVariable String gameName,
            @PathVariable String username) {
        Match match = this.matchRepository.findByGameName(gameName);
        match.addUser(username);
        this.matchRepository.save(match);
        return ResponseEntity.ok(match);

    } 
}