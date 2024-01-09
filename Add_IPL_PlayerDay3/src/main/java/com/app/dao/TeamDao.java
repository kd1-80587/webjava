package com.app.dao;

import java.util.List;

import com.app.pojo.Team;

public interface TeamDao {
//	Add a method to add new method
	String addNewTeam(Team newTeam);
	
//	Display team details , of a team by it's id
	Team getTeamDetailsById(Integer id);
	
//	Display  team id n abbreviation of all the teams
	List<Team> getAllTeamIdAbbrevation();
	
//	Display all those teams who need , player's max age < 
//	specific age n min no of wickets taken > specified wickets
	List<Team> displayTeamsBySpecifications(int maxAge,int wicketsTaken);
	
//	Update max age n batting avg requirement of a specific team by it's name
	String updateMaxAgeAndBattingAvg(String name,int maxAge,double battingAvg); 
	
//	Delete team details i/p : team id
	String deleteTeamById(Integer teamId);
	
//	get selected team details:
	Team getSelectedTeamDetails(Integer teamId);
}

