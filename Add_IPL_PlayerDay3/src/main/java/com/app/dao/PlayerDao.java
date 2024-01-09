package com.app.dao;

import java.util.List;

import com.app.pojo.Player;

public interface PlayerDao {
// Add new player to the team
	String addNewPlayer(Integer teamId,Player newPlayer);
}
