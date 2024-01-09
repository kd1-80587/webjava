package com.app.beans;

import java.util.List;

import com.app.dao.TeamDao;
import com.app.dao.TeamDaoImpl;
import com.app.pojo.Team;

public class TeamBean {
	private TeamDao teamDao;
	public TeamBean() {
	teamDao =new TeamDaoImpl();
	System.out.println("Team bean and dao created..");
	}
	public List<Team> getAllTeamIdAbbrevation(){
		List<Team> list=teamDao.getAllTeamIdAbbrevation();
		System.out.println(list);
		return list;
	}
}
