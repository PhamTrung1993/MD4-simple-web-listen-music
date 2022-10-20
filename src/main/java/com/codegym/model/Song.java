package com.codegym.model;


import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String songName;
    private String singer;
    private String category;
    private String link;

    public Song() {
    }

    public Song(Long id, String songName, String singer, String category, String link) {
        this.id = id;
        this.songName = songName;
        this.singer = singer;
        this.category = category;
        this.link = link;
    }

    public Song(String songName, String singer, String category, String link) {
        this.songName = songName;
        this.singer = singer;
        this.category = category;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
