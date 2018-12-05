package com.example.achekhtestsb.repository;

import com.example.achekhtestsb.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    @Query("select h from People h where h.human=?1")
    List<People> searchByHuman(String human);
    List<People> findPeopleByHuman(String human);
}
