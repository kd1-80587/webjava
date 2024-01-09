package com.app.beans;

import java.time.LocalDate;
import java.time.Period;

import com.app.dao.PlayerDao;
import com.app.dao.PlayerDaoImpl;
import com.app.dao.TeamDao;
import com.app.dao.TeamDaoImpl;
import com.app.pojo.Player;
import com.app.pojo.Team;

public class PlayerBean {
	private int teamId;
	private String fName;
	private String lName;
	private String dob;
	private double avg;
	private int wickets;

	private PlayerDao playerDao;
	private TeamDao teamDao;

	public PlayerBean() {
		teamDao=new TeamDaoImpl();
		playerDao=new PlayerDaoImpl();
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public PlayerDao getPlayerDao() {
		return playerDao;
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	public TeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	public String validateAndAddPlayer() {
		String msg = "";
		Team chosenTeam = teamDao.getTeamDetailsById(teamId);
		LocalDate date = LocalDate.parse(dob);
		int age = Period.between(date, LocalDate.now()).getYears();
		if (age <= chosenTeam.getMaxAge()) {
			if (wickets >= chosenTeam.getWicketsTaken()) {
				if (avg >= chosenTeam.getBattingAvg()) {
					msg = playerDao.addNewPlayer(teamId, new Player(fName, lName, date, age, wickets));
				}
			}
		}
		return msg;

	}

}
