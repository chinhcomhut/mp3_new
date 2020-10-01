package com.codegym.wbdlaptop.model;

import javax.persistence.*;

@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameVideo;
    private String linkYoutube;
    private String nameSinger;
    private String nameCategory;
    private String nameBand;
    private String createBy;
    private int likeVideo;
    private double viewVideo;
    @ManyToOne
    User user;

    public Video() {
    }

    public Video(Long id, String nameVideo, String linkYoutube, String nameSinger, String nameCategory, String nameBand, String createBy, int likeVideo, double viewVideo, User user) {
        this.id = id;
        this.nameVideo = nameVideo;
        this.linkYoutube = linkYoutube;
        this.nameSinger = nameSinger;
        this.nameCategory = nameCategory;
        this.nameBand = nameBand;
        this.createBy = createBy;
        this.likeVideo = likeVideo;
        this.viewVideo = viewVideo;
        this.user = user;
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

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameBand() {
        return nameBand;
    }

    public void setNameBand(String nameBand) {
        this.nameBand = nameBand;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
