package dao;

import java.util.List;

import pojos.Team;

public interface TeamDao {
	//add a method to get selected(id n abbreviation) of all teams
	List<Team> getTeamDetails();
	//add a method to get complete details of the selected team
	Team getSelectedTeamDetails(Integer teamId);
	
}
