package com.codegym.wbdlaptop.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlist")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePlayList;
    private String avatarPlayList;
    private String createBy;
    private String nameSinger;
    private String nameBand;
    private String nameCategory;
    private String nameAlbum;
    @ManyToOne
    User user;
    //        @JsonIgnore
//    @JsonView
//    @JsonIgnoreProperties

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "player_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songList;

    public Playlist() {
    }

    public Playlist(Long id, String namePlayList, String avatarPlayList, String createBy, String nameSinger, String nameBand, String nameCategory, String nameAlbum, User user, List<Song> songList) {
        this.id = id;
        this.namePlayList = namePlayList;
        this.avatarPlayList = avatarPlayList;
        this.createBy = createBy;
        this.nameSinger = nameSinger;
        this.nameBand = nameBand;
        this.nameCategory = nameCategory;
        this.nameAlbum = nameAlbum;
        this.user = user;
        this.songList = songList;
    }

    public Long getId() {
        return id;
    }

    //    @JsonIgnore
    public List<Song> getSongList() {
        return songList;
    }

    //    @JsonIgnore
    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePlayList() {
        return namePlayList;
    }

    public void setNamePlayList(String namePlayList) {
        this.namePlayList = namePlayList;
    }

    public String getAvatarPlayList() {
        return avatarPlayList;
    }

    public void setAvatarPlayList(String avatarPlayList) {
        this.avatarPlayList = avatarPlayList;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getNameBand() {
        return nameBand;
    }

    public void setNameBand(String nameBand) {
        this.nameBand = nameBand;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
