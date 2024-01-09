package com.app.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
//import org.hibernate.*;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
	
	@Column(length = 100, unique = true)
	private String name;

	@Column(length = 10, unique = true)
	private String abbrevation;

	@Column(length = 20)
	private String owner;

	@Column(name = "max_age")
	private int maxAge;

	@Column(name = "batting_avg")
	private double battingAvg;

	@Column(name = "wickets_taken")
	private int wicketsTaken;

//	Association prop: Team 1-->* Player
// tech terms : one , parent , non owning(inverse)
	@OneToMany(mappedBy = "myTeam",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<Player> player = new ArrayList<>();

	public Team() {
	}

	public Team(String name, String abbrevation, String owner, int maxAge, double battingAvg, int wicketsTaken) {
		this.name = name;
		this.abbrevation = abbrevation;
		this.owner = owner;
		this.maxAge = maxAge;
		this.battingAvg = battingAvg;
		this.wicketsTaken = wicketsTaken;
	}

	public Team(Integer id, String abbrevation) {
		setId(id);
		this.abbrevation = abbrevation;
	}

//	all getters and setters are required.

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrevation() {
		return abbrevation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public double getBattingAvg() {
		return battingAvg;
	}

	public void setBattingAvg(double battingAvg) {
		this.battingAvg = battingAvg;
	}

	public int getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

	public List<Player> getPlayer() {
		return player;
	}

	public void setPlayer(List<Player> player) {
		this.player = player;
	}
	
	public void addPlayer(Player newPlayer) {
		player.add(newPlayer);
		newPlayer.setMyTeam(this);
	}

	@Override
	public String toString() {
		return "Team [teamId=" + getId() + ", name=" + name + ", abbrevation=" + abbrevation + ", owner=" + owner
				+ ", maxAge=" + maxAge + ", battingAvg=" + battingAvg + ", wicketsTaken=" + wicketsTaken + "]";
	}

}
