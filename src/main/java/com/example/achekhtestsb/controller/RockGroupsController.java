package com.example.achekhtestsb.controller;

import com.example.achekhtestsb.entity.RockGroups;
import com.example.achekhtestsb.repository.RockGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rockgroups")
public class RockGroupsController {
    @Autowired
    private RockGroupsRepository repository;

    @GetMapping("/all")
    public List<RockGroups> getAllRockGroups(){
        return repository.findAll();
    }

    @PostMapping("/add")
    public RockGroups addRockGroups(@RequestBody RockGroups rockGroups){
        return repository.save(rockGroups);
    }

    @DeleteMapping("/del/{id}")
    public void delRockGroups(@PathVariable long id){
        repository.deleteById(id);
    }

    @PutMapping("/upd/{id}")
    public ResponseEntity<RockGroups> updRockGroups(@RequestBody RockGroups rockGroups, @PathVariable long id){
        Optional<RockGroups> rockGroupsOptional = repository.findById(id);
        if(!rockGroupsOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        rockGroups.setId(id);
        repository.save(rockGroups);
        return ResponseEntity.noContent().build();
    }
}
