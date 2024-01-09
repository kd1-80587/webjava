package beans;

import java.time.LocalDate;
import java.time.Period;

import dao.PlayerDao;
import dao.PlayerDaoImpl;
import dao.TeamDao;
import dao.TeamDaoImpl;
import pojos.Player;
import pojos.Team;

public class PlayerBean {
	private int myTeam;// team id
	private String fn;
	private String ln;
	private String dob;
	private double avg;
	private int wickets;
	// dependencies :
	private TeamDao teamDao;
	private PlayerDao playerDao;

	public PlayerBean() {
		teamDao = new TeamDaoImpl();
		playerDao = new PlayerDaoImpl();
	}

	public int getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(int myTeam) {
		this.myTeam = myTeam;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
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

	// add B.L to validate player details against team requirements
	// insert player details : in case of successful validations
	public String validatePlayer() {
		//get team details from it's id
		Team team=teamDao.getSelectedTeamDetails(myTeam);
		if(team == null)
			return "Adding Player Failed : Invalid team id !!";
		LocalDate playerDob=LocalDate.parse(dob);
		int playerAge=Period.between(playerDob, LocalDate.now()).getYears();
		if(playerAge >  team.getMaxAge() 
				|| wickets < team.getWicketsTaken() 
				|| avg < team.getBattingAvg())
			return "Adding Player Failed : Invalid player details!!!";
		//team n player : valid
		//create transient player instance
		Player player=new Player(fn, ln, playerDob, avg, wickets);
		return playerDao.addNewPlayer(myTeam, player);
	}

}
