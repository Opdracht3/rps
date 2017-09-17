package com.tmh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    // @OneToMany(mappedBy = "match_id")
    // private Set<Bookmark> bookmarks = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    public String userName;
    public String weapon;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    User(String userName) {
        this.userName = userName;
    }

    User() { // jpa only
    }
}