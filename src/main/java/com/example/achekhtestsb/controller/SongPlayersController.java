package com.example.achekhtestsb.controller;

import com.example.achekhtestsb.dto.PeopleDTO;
import com.example.achekhtestsb.dto.SongPlayersDTO;
import com.example.achekhtestsb.entity.Album;
import com.example.achekhtestsb.entity.People;
import com.example.achekhtestsb.entity.SongPlayers;
import com.example.achekhtestsb.repository.SongPlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songplayers")
public class SongPlayersController {
    @Autowired
    SongPlayersRepository repository;

    @GetMapping("/all")
    public List<SongPlayersDTO> getSongPlayers(){
        SongPlayersDTO songPlayersDTO = new SongPlayersDTO();
        return songPlayersDTO.getSongPlayersDTOList(repository.findAll());
    }
//найти всех исполнителей которые исполняли данную композицию
    @GetMapping("/getpeoplebysong/{song}")
    public List<People> getPeopleBySong(@PathVariable String song){
        SongPlayersDTO songPlayersDTO = new SongPlayersDTO();
        List<SongPlayersDTO> songPlayersDTOList = songPlayersDTO.getSongPlayersDTOList(repository.findSongPlayersBySong(song));
        List<List<PeopleDTO>> peopleDTOList = new ArrayList<>();
        for (SongPlayersDTO sngPlDTO : songPlayersDTOList){
            peopleDTOList.add(sngPlDTO.getSongInstrumentalistList());
        }
        List<People> peopleList = new ArrayList<>();
        for (List<PeopleDTO> pplDTOList : peopleDTOList){

            for (PeopleDTO pplDTO : pplDTOList){
                peopleList.add(new People(pplDTO.getId(), pplDTO.getHuman()));
            }

        }
        return peopleList;
    }
    //найти все альбомы на которых издавалась данная композиция
    @GetMapping("/getalbumbysong/{song}")
    public List<String> getAlbumBySong(@PathVariable String song){
        SongPlayersDTO songPlayersDTO = new SongPlayersDTO();
        List<SongPlayersDTO> songPlayersDTOList = songPlayersDTO.getSongPlayersDTOList(repository.findSongPlayersBySong(song));
        List<String> albumList = new ArrayList<>();
        for (SongPlayersDTO sngPlDTO : songPlayersDTOList){
            albumList.add(sngPlDTO.getAlbum());
        }
        return albumList;
    }

    //найти все композиции заданного композитолра
    @GetMapping("/getsongbycomposer/{composer}")
    public List<String> getSongByComposer(@PathVariable String composer){
        SongPlayersDTO songPlayersDTO = new SongPlayersDTO();
        List<SongPlayersDTO> songPlayersDTOList = songPlayersDTO.getSongPlayersDTOList(repository.findSongPlayersByComposer(composer));
        List<String> songList = new ArrayList<>();
        for (SongPlayersDTO sngPlDTO : songPlayersDTOList){
            songList.add(sngPlDTO.getSong());
        }
        return songList;
    }
    //найти все композиции заданного поэта
    @GetMapping("/getsongbypoet/{poet}")
    public List<String> getSongByPoet(@PathVariable String poet){
        SongPlayersDTO songPlayersDTO = new SongPlayersDTO();
        List<SongPlayersDTO> songPlayersDTOList = songPlayersDTO.getSongPlayersDTOList(repository.findSongPlayersByPoet(poet));
        List<String> songList = new ArrayList<>();
        for (SongPlayersDTO sngPlDTO : songPlayersDTOList){
            songList.add(sngPlDTO.getSong());
        }
        return songList;
    }
    /*public List<SongPlayersDTO> getSongPlayersBySong(@PathVariable String song){
        SongPlayersDTO songPlayersDTO = new SongPlayersDTO();
        return songPlayersDTO.getSongPlayersDTOList(repository.findSongPlayersBySong(song));
    }*/

    @PostMapping("/add")
    public SongPlayers addSongPlayers(@RequestBody SongPlayers songPlayers){
        return repository.save(songPlayers);
    }

    @DeleteMapping("/del/{id}")
    public void delSongPlayers(@PathVariable long id){
        repository.deleteById(id);
    }

    @PutMapping("/upd/{id}")
    public ResponseEntity<Object> updSongPlayers(@RequestBody SongPlayers songPlayers, @PathVariable long id){
        Optional<SongPlayers> songPlayersOptional = repository.findById(id);
        if (!songPlayersOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        songPlayers.setId(id);
        repository.save(songPlayers);
        return ResponseEntity.noContent().build();
    }
}
