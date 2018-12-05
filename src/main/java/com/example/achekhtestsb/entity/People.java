package com.example.achekhtestsb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String human;

    //несколько людей играют в одной группе
    @ManyToOne(cascade = CascadeType.ALL)
    private RockGroups rockGroups;

    //разные люди исполняют разные композиции
    @ManyToMany(mappedBy = "songInstrumentalist",fetch = FetchType.EAGER)
    private List<SongPlayers> songItems;

    public People(){}

    public People(long id, String human){
        this.id = id;
        this.human = human;
    }

    public People(String human){
        this.human = human;
    }

    public List<SongPlayers> getSongItems() {
        return songItems;
    }

    public void setSongItems(List<SongPlayers> songItems) {
        this.songItems = songItems;
    }

    public RockGroups getRockGroups() {
        return rockGroups;
    }

    public void setRockGroups(RockGroups rockGroups) {
        this.rockGroups = rockGroups;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHuman() {
        return human;
    }

    public void setHuman(String human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return String.format("[id=%s, human=%s]", id, human);
    }
}
