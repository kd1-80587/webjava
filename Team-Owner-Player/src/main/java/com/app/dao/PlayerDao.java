package com.app.dao;

import com.app.pojos.Player;

public interface PlayerDao {
//add a method to insert new player details
	String addNewPlayer(Integer teamId, Player newPlayer);
}
