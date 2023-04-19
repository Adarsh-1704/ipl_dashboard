package io.adarsh.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.adarsh.ipldashboard.model.Match;
import io.adarsh.ipldashboard.model.Team;
import io.adarsh.ipldashboard.repository.MatchRepository;
import io.adarsh.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeam(){
        return this.teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);

        team.setMatches(this.matchRepository.findLatestMatchesbyTeam(teamName, 4));
        team.setLatestYear(this.matchRepository.findLatestMatchesbyTeam(teamName, 1).get(0).getDate().getYear());
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate starDate = LocalDate.of(year, 1, 1);
        LocalDate enDate = LocalDate.of(year + 1, 1, 1);
    
        return this.matchRepository.getMatchesByTeamBetweenDates(teamName, starDate, enDate);
    }
}