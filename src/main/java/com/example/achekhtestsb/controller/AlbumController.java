package com.example.achekhtestsb.controller;

import com.example.achekhtestsb.dto.AlbumDTO;
import com.example.achekhtestsb.entity.Album;
import com.example.achekhtestsb.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumRepository repository;

    @GetMapping("/all")
    public List<AlbumDTO> getAllAlbum(){
        AlbumDTO albumDTO = new AlbumDTO();
        return albumDTO.getAllAlbumDTOList(repository.findAll());
    }

    @PostMapping("/add")
    public Album addAlbum(@RequestBody Album album){
        return repository.save(album);
    }

    @DeleteMapping("/del/{id}")
    public void delAlbum(@PathVariable long id ){
        repository.deleteById(id);
    }

    @PutMapping("/upd/{id}")
    public ResponseEntity<Object> updAlbum(@RequestBody Album album, @PathVariable long id){
        Optional<Album> albumOptional = repository.findById(id);
        if (!albumOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        album.setId(id);
        repository.save(album);
        return ResponseEntity.noContent().build();
    }
}
