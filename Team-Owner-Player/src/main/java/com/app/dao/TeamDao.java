package com.app.dao;

import java.util.List;

import com.app.pojos.Team;

public interface TeamDao {
	//add a method to get selected(id n abbreviation) of all teams
	List<Team> getTeamDetails();
	//add a method to get complete details of the selected team
	Team getSelectedTeamDetails(Integer teamId);
	public String addTeamForSpecificOwner(String email,Team newTeam);
//	Update team details
	public String updateTeamDetails(String name);
//	Remove Team Details
	public String removeTeam(String abbreviation);
}
