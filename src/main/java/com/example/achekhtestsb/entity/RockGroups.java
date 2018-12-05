package com.example.achekhtestsb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RockGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String rockGroup;

    public RockGroups(){}

    public RockGroups(String rockGroup){
        this.rockGroup = rockGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRockGroup() {
        return rockGroup;
    }

    public void setRockGroup(String rockGroup) {
        this.rockGroup = rockGroup;
    }

    @Override
    public String toString() {
        return String.format("[id=%s, rockGroup=%s]", id, rockGroup);
    }
}
