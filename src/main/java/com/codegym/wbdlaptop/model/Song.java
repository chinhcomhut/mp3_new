package com.codegym.wbdlaptop.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "song")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSong;
    private String avatarSong;
    @Lob
    private String lyrics;
    private String mp3Url;
    private String nameSinger;
    private String nameCategory;
    private String nameBand;
    private int likeSong;
    private int listenSong;
    private String CreateBy;

    @ManyToOne
    User user;

    public Song() {
    }
//    @JsonIgnore
    public Song(Long id, String nameSong, String avatarSong, String lyrics, String mp3Url, String nameSinger, String nameCategory, String nameBand, int likeSong, int listenSong, String createBy, User user) {
        this.id = id;
        this.nameSong = nameSong;
        this.avatarSong = avatarSong;
        this.lyrics = lyrics;
        this.mp3Url = mp3Url;
        this.nameSinger = nameSinger;
        this.nameCategory = nameCategory;
        this.nameBand = nameBand;
        this.likeSong = likeSong;
        this.listenSong = listenSong;
        CreateBy = createBy;
        this.user = user;
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

    public String getAvatarSong() {
        return avatarSong;
    }

    public void setAvatarSong(String avatarSong) {
        this.avatarSong = avatarSong;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
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

    public int getLikeSong() {
        return likeSong;
    }

    public void setLikeSong(int likeSong) {
        this.likeSong = likeSong;
    }

    public int getListenSong() {
        return listenSong;
    }

    public void setListenSong(int listenSong) {
        this.listenSong = listenSong;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
