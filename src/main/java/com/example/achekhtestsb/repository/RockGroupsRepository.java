package com.example.achekhtestsb.repository;

import com.example.achekhtestsb.entity.RockGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RockGroupsRepository extends JpaRepository<RockGroups, Long> {
}
