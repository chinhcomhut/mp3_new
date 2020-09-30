package com.codegym.wbdlaptop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "singer")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSinger;
    private String avatarSinger;
    private String createBy;
    @Lob
    private String information;
    private String birthday;
    private String gender;
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "singer_player",
    joinColumns = @JoinColumn(name = "singer_id"),
    inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Playlist> playlists;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "singer_song",
    joinColumns = @JoinColumn(name = "singer_id"),
    inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songList;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "singer_video",
            joinColumns = @JoinColumn(name = "singer_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id"))
    private List<Video> videoList;
    public Singer() {
    }

    public Singer(Long id, String nameSinger, String avatarSinger, String createBy, String information, String birthday, String gender, User user, List<Playlist> playlists, List<Song> songList, List<Video> videoList) {
        this.id = id;
        this.nameSinger = nameSinger;
        this.avatarSinger = avatarSinger;
        this.createBy = createBy;
        this.information = information;
        this.birthday = birthday;
        this.gender = gender;
        this.user = user;
        this.playlists = playlists;
        this.songList = songList;
        this.videoList = videoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getAvatarSinger() {
        return avatarSinger;
    }

    public void setAvatarSinger(String avatarSinger) {
        this.avatarSinger = avatarSinger;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}
