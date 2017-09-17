package com.tmh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Match {

    // @OneToMany(mappedBy = "match_id")
    // private Set<Bookmark> bookmarks = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    public String gameName;

    public String gameWonBy;

    @OneToOne
    public User player1;

    @OneToOne
    public User player2;

    public Long getId() {
        return id;
    }

    public void addUser(User user) {
        if (player1 == null && !user.getUserName().equals(player2)) {
            player1 = user;
        } else if (player2 == null && !user.getUserName().equals(player1)) {
            player2 = user;
        }
    }

    public void addWeapon(String userName, String weapon) {
        if (userName.equals(player1)) {
            player1.setWeapon(weapon);
        } else if (userName.equals(player2)) {
            player2.setWeapon(weapon);
        }
    }

    public String status() {
        String status = "Status is ";
        status += determineStatusOfPlayer();
        return status;
    }

    private String determineStatusOfPlayer() {
        String status = "";
        if (player1 == null) {
            status += " Waiting for player1 ";
        }
        if (player2 == null) {
            status += " Waiting for player2 ";
        }

        if (player1 != null && player1.getWeapon() == null) {
            status += "Waiting for choise of " + player1.getUserName();
        }

        if (player2 != null && player2.getWeapon() == null) {
            status += "Waiting for choise of " + player2.getUserName();
        }

        if (player1 != null && player1.getWeapon() != null && player2 != null
                && player2.getWeapon() != null) {
            return outcomeOfGame();
        }
        return status;

    }

    private String outcomeOfGame() {
        switch (player1.getWeapon() + " " + player2.getWeapon()) {
        case "ROCK PAPER":
            return player2.getUserName() + " wins";
        case "ROCK SCISSOR":
            return player1.getUserName() + " wins";
        case "ROCK ROCK":
            return " Game ends in a tie";

        case "PAPER ROCK":
            return player1.getUserName() + " wins";
        case "PAPER SCISSOR":
            return player2.getUserName() + " wins";
        case "PAPER PAPER":
            return " Game ends in a tie";

        case "SCISSOR PAPER":
            return player1.getUserName() + " wins";
        case "SCISSOR ROCK":
            return player2.getUserName() + " wins";
        case "SCISSOR SCISSOR":
            return " Game ends in a tie";

        default:
            break;
        }
        
        
        return null;
    }

    public String getGameName() {
        return gameName;
    }

    public Match(String gameName) {
        this.gameName = gameName;
    }

    Match() { // jpa only
    }
}