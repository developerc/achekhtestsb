package com.example.achekhtestsb;

import com.example.achekhtestsb.entity.Album;
import com.example.achekhtestsb.entity.People;
import com.example.achekhtestsb.entity.RockGroups;
import com.example.achekhtestsb.entity.SongPlayers;
import com.example.achekhtestsb.repository.AlbumRepository;
import com.example.achekhtestsb.repository.PeopleRepository;
import com.example.achekhtestsb.repository.RockGroupsRepository;
import com.example.achekhtestsb.repository.SongPlayersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AchekhtestsbApplication implements CommandLineRunner {
    People people;
    People people2;
    List<People> peopleList = new ArrayList<>();
    List<RockGroups> rockGroupsList = new ArrayList<>();
    List<SongPlayers> songPlayersList = new ArrayList<>();
    List<Album> albumList = new ArrayList<>();
    RockGroups rockGroups;
    SongPlayers songPlayers;
    Album album;

    public static void main(String[] args) {
        SpringApplication.run(AchekhtestsbApplication.class, args);
    }
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    RockGroupsRepository rockGroupsRepository;

    @Autowired
    SongPlayersRepository songPlayersRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++){
            people = new People("Human" + i);
            peopleList.add(people);
            logger.info("Inserting People -> {}", peopleRepository.save(people));
        }

        for (int i = 0; i < 5; i++) {
            rockGroups = new RockGroups("RockGroups" + i);
            rockGroupsList.add(rockGroups);
            logger.info("Inserting rockGroup -> {}", rockGroupsRepository.save(rockGroups));
        }

        for (int i = 0; i < 30; i++) {
            songPlayers = new SongPlayers("Song"+i, "Human" + (int)(Math.random()*10), "Human"+ (int)(Math.random()*10), "Album"+ (int)(Math.random()*6));
            songPlayersList.add(songPlayers);
            logger.info("Insert songPlayers -> {}", songPlayersRepository.save(songPlayers));
        }

        for (int i = 0; i < 6; i++) {
            album = new Album("Album" + i);
            albumList.add(album);
            logger.info("Insert album -> {}", albumRepository.save(album));
        }

        //---------------------------------
//        addRockgroups(2);
        getAllRockgroups();
        int k = 0;
        for (int i = 0; i < 10; i++) {
            if ((i % 2) > 0) {
                updPeople(i, k);
                k++;
            } else {
                updPeople(i, k);
            }
        }
        for (int i = 0; i < 30; i++) {
            updSongPlayers(i);
        }
        for (int i = 0; i < 3; i++) {
            updAlbum(i);
        }
       /* updAlbum(4);
        updAlbum(5);*/
    }

    private void updAlbum(int albI) {
        final String ROOT = "http://localhost:8080/album/upd/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        album = albumList.get(albI);

        List<SongPlayers> songPlayersListUpdAlbum = new ArrayList<>();
        songPlayers = songPlayersList.get((int)(Math.random()*30));
        songPlayersListUpdAlbum.add(songPlayers);
        /*songPlayers = songPlayersList.get((int)(Math.random()*30));
        songPlayersListUpdAlbum.add(songPlayers);*/
        album.setSongPlayersList(songPlayersListUpdAlbum);
        HttpEntity entity = new HttpEntity(album, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<Album> updateAlbum = template.exchange(
                ROOT,
                HttpMethod.PUT,
                entity,
                Album.class,
                album.getId()
        );
        logger.info("Update Album status -> {}", updateAlbum.getStatusCode());
        album = null;
        songPlayers = null;
    }

    private void updSongPlayers(int spI) {
        final String ROOT = "http://localhost:8080/songplayers/upd/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        List<People> peopleListUpdSP = new ArrayList<>();
        int pplI = (int)(Math.random()*10);
//        people = peopleList.get(0);
        people = peopleList.get(pplI);
        peopleListUpdSP.add(people);
//        people2 = peopleList.get(1);
//        people2 = peopleList.get(pplI +1 > 9? 0 : pplI + 1);
//        peopleListUpdSP.add(people2);
        songPlayers = songPlayersList.get(spI);
        songPlayers.setSongInstrumentalist(peopleListUpdSP);
        songPlayersList.set(spI, songPlayers);
        HttpEntity entity = new HttpEntity(songPlayers, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<SongPlayers> updateSongPlayers = template.exchange(
                ROOT,
                HttpMethod.PUT,
                entity,
                SongPlayers.class,
                songPlayers.getId()
        );
        logger.info("Update SongPlayers status -> {}", updateSongPlayers.getStatusCode());
    }

    private void updPeople(int peopleI, int rockGroupsI) {
        final String ROOT = "http://localhost:8080/people/upd/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        people = peopleList.get(peopleI);
        rockGroups = rockGroupsList.get(rockGroupsI);

        people.setRockGroups(rockGroups);
        peopleList.set(peopleI, people);

        HttpEntity entity = new HttpEntity(people, headers);
        RestTemplate template = new RestTemplate();

        ResponseEntity<People> updatedPeople = template.exchange(
                ROOT,
                HttpMethod.PUT,
                entity,
                People.class,
                people.getId()
        );
        logger.info("Update People status -> {}", updatedPeople.getStatusCode());
    }

    private void addRockgroups(int numRockgroups) {
        final String ROOT = "http://localhost:8080/rockgroups/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        rockGroups = new RockGroups("RockGroups" + numRockgroups);
        HttpEntity entity = new HttpEntity(rockGroups, headers);

        RestTemplate template = new RestTemplate();
        RockGroups receivedRockGroups = template.exchange(
                ROOT,
                HttpMethod.POST,
                entity,
                RockGroups.class
        ).getBody();
//        logger.info("addRockGroups -> {}", rockGroupsRepository.saveAndFlush(rockGroups));
//        logger.info("addRockGroups -> {}", rockGroupsRepository.save(rockGroups));
//        logger.info("addRockGroups -> {}", receivedRockGroups.toString());

    }

    private void getAllRockgroups() {
         final String ROOT = "http://localhost:8080/rockgroups/all";
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<RockGroups>> responseEntity = template.exchange(
                ROOT,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RockGroups>>() {
                }
        );
        List<RockGroups> list = responseEntity.getBody();
        for (RockGroups rockGroups : list) {
            logger.info("getAllRockGroups -> {}", rockGroups);
        }
    }
}
