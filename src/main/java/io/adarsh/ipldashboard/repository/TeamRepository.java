package io.adarsh.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import io.adarsh.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team,Long>{
    

    Team findByTeamName(String teamName);
    

}
