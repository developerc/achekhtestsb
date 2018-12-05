package com.example.achekhtestsb.dto;

import com.example.achekhtestsb.entity.People;
import com.example.achekhtestsb.entity.RockGroups;
import com.example.achekhtestsb.entity.SongPlayers;

import java.util.ArrayList;
import java.util.List;

public class PeopleDTO {
    private long id;
    private String human;
    private RockGroups rockGroups;
    private List<SongPlayersDTO> songPlayersList;

    public List<PeopleDTO> getPeopleDTOList(List<People> peopleList){
        List<PeopleDTO> peopleDTOList = new ArrayList<>();

        for (People people : peopleList){
            songPlayersList = new ArrayList<>();

            PeopleDTO peopleDTO = new PeopleDTO();
            peopleDTO.setId(people.getId());
            peopleDTO.setHuman(people.getHuman());
            peopleDTO.setRockGroups(people.getRockGroups());

            for (SongPlayers songPlayers : people.getSongItems()){
                SongPlayersDTO songPlayersDTO = new SongPlayersDTO();

                songPlayersDTO.setId(songPlayers.getId());
                songPlayersDTO.setSong(songPlayers.getSong());
                songPlayersDTO.setPoet(songPlayers.getPoet());
                songPlayersDTO.setComposer(songPlayers.getComposer());
                songPlayersDTO.setAlbum(songPlayers.getAlbum());
                songPlayersList.add(songPlayersDTO);
            }
            peopleDTO.setSongPlayersList(songPlayersList);
            peopleDTOList.add(peopleDTO);
        }
        return peopleDTOList;
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

    public RockGroups getRockGroups() {
        return rockGroups;
    }

    public void setRockGroups(RockGroups rockGroups) {
        this.rockGroups = rockGroups;
    }

    public List<SongPlayersDTO> getSongPlayersList() {
        return songPlayersList;
    }

    public void setSongPlayersList(List<SongPlayersDTO> songPlayersList) {
        this.songPlayersList = songPlayersList;
    }
}
