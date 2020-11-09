package com.codegym.wbdlaptop.model;

import javax.persistence.*;

@Entity
@Table(name = "likehlt")
public class LikeHocLapTrinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String nameVideo;

    public LikeHocLapTrinh() {
    }

    public LikeHocLapTrinh(Long id, String username, String nameVideo) {
        this.id = id;
        this.username = username;
        this.nameVideo = nameVideo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }
}
