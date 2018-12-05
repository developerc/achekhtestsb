package com.example.achekhtestsb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class SongPlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String song;

    //у композиции один композитор
    private String composer;

    // и один автор стихов
    private String poet;

    //песня содержится в альбоме
    private String album;

    //и много исполнителей
    //исполнители могут исполнять разные песни
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<People> songInstrumentalist;

    public SongPlayers(){}

    public SongPlayers(long id, String song){
        this.id = id;
        this.song = song;
    }

    public SongPlayers(String song, String composer, String poet, String album){
        this.song = song;
        this.composer = composer;
        this.poet = poet;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getPoet() {
        return poet;
    }

    public void setPoet(String poet) {
        this.poet = poet;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<People> getSongInstrumentalist() {
        return songInstrumentalist;
    }

    public void setSongInstrumentalist(List<People> songInstrumentalist) {
        this.songInstrumentalist = songInstrumentalist;
    }

    @Override
    public String toString() {
        return String.format("[id=%s, song=%s, composer=%s, poet=%s, album=%s]", id, song, composer, poet, album);
    }
}
