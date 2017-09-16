package com.tmh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Match {

    // @OneToMany(mappedBy = "match_id")
    // private Set<Bookmark> bookmarks = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    public String gameName;
    public String player1;
    public String player2;

    public Long getId() {
        return id;
    }

    public void addUser(String userName) {
        if (player1 == null && !userName.equals(player2)) {
            player1 = userName;
        } else if (player2 == null && !userName.equals(player1)) {
            player2 = userName;
        }
    }

    public String getGameName() {
        return gameName;
    }

    public Match(String username) {
        this.gameName = username;
    }

    Match() { // jpa only
    }
}