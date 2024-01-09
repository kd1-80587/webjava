package beans;

import java.util.List;

import dao.TeamDao;
import dao.TeamDaoImpl;
import pojos.Team;

public class TeamBean {
	//depcy 
	private TeamDao teamDao;
	public TeamBean() {
		//create team dao instance
		teamDao=new TeamDaoImpl();
		System.out.println("team bean n dao created ....");
	}
	//Add B.L method to fetch team's details
	public List<Team> getAllTeams() 
	{
		List<Team> list = teamDao.getTeamDetails();
		System.out.println(list);
		return list;
	}
}
