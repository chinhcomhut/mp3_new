package com.codegym.wbdlaptop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameBand;
    private String avatarBand;
    private String createBy;
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "band_song",
    joinColumns = @JoinColumn(name = "band_id"),
    inverseJoinColumns = @JoinColumn(name = "song_id"))
    List<Song> songList;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "band_player",
    joinColumns = @JoinColumn(name = "band_id"),
    inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    List<Playlist> playlists;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "band_video",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id"))
    List<Video> videoList;

    public Band() {
    }

    public Band(Long id, String nameBand, String avatarBand, String createBy, User user, List<Song> songList, List<Playlist> playlists, List<Video> videoList) {
        this.id = id;
        this.nameBand = nameBand;
        this.avatarBand = avatarBand;
        this.createBy = createBy;
        this.user = user;
        this.songList = songList;
        this.playlists = playlists;
        this.videoList = videoList;
    }

    public Long getId() {
        return id;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBand() {
        return nameBand;
    }

    public void setNameBand(String nameBand) {
        this.nameBand = nameBand;
    }

    public String getAvatarBand() {
        return avatarBand;
    }

    public void setAvatarBand(String avatarBand) {
        this.avatarBand = avatarBand;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
