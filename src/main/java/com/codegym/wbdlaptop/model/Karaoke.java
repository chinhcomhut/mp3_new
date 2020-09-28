package com.codegym.wbdlaptop.model;

import javax.persistence.*;

@Entity
@Table(name = "karaoke")
public class Karaoke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSong;
    private String linkYoutube;
    private int likeKaraoke;
    private int countSong;
    @ManyToOne
    User user;
    public Karaoke() {
    }

    public Karaoke(Long id, String nameSong, String linkYoutube, User user, int likeKaraoke, int countSong) {
        this.id = id;
        this.nameSong = nameSong;
        this.linkYoutube = linkYoutube;
        this.user = user;
        this.likeKaraoke = likeKaraoke;
        this.countSong = countSong;
    }

    public Long getId() {
        return id;
    }

    public int getCountSong() {
        return countSong;
    }

    public int getLikeKaraoke() {
        return likeKaraoke;
    }

    public void setCountSong(int countSong) {
        this.countSong = countSong;
    }

    public void setLikeKaraoke(int likeKaraoke) {
        this.likeKaraoke = likeKaraoke;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }
}
