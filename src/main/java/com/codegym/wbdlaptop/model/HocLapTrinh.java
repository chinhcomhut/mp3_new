package com.codegym.wbdlaptop.model;

import javax.persistence.*;

@Entity
@Table(name = "programing")
public class HocLapTrinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameVideo;
    private String linkYoutube;
    private int likeVideo;
    private double viewVideo;
    @ManyToOne
    User user;

    public HocLapTrinh() {
    }

    public HocLapTrinh(Long id, String nameVideo, int likeVideo, double viewVideo, User user, String linkYoutube) {
        this.id = id;
        this.nameVideo = nameVideo;
        this.likeVideo = likeVideo;
        this.viewVideo = viewVideo;
        this.user = user;
        this.linkYoutube = linkYoutube;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public int getLikeVideo() {
        return likeVideo;
    }

    public void setLikeVideo(int likeVideo) {
        this.likeVideo = likeVideo;
    }

    public double getViewVideo() {
        return viewVideo;
    }

    public void setViewVideo(double viewVideo) {
        this.viewVideo = viewVideo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
