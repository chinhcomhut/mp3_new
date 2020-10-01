package com.codegym.wbdlaptop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "likesong")
public class LikeSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSong;
    private String username;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "likesong_user",
//            joinColumns = @JoinColumn(name = "likesong_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> users;


    public LikeSong() {
    }

    public LikeSong(Long id, String nameSong, String username) {
        this.id = id;
        this.nameSong = nameSong;
        this.username = username;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
