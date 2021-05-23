package com.praveen.ipl_springboot.controllers;

import com.praveen.ipl_springboot.Model.Team;
import com.praveen.ipl_springboot.Repository.MatchRepository;
import com.praveen.ipl_springboot.Repository.TeamRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController { 
    
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
       // System.out.println("teamname  : " + teamName);
        Team team = teamRepository.findByTeamName(teamName);
        
        team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));
        return team;
    }
    
    


    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }
}
