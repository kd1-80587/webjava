package dao;

import pojos.Player;

public interface PlayerDao {
//add a method to insert new player details
	String addNewPlayer(Integer teamId, Player newPlayer);
}
