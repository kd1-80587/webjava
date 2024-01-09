package com.app.dao;

import static com.app.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.pojo.Player;
import com.app.pojo.Team;

public class PlayerDaoImpl implements PlayerDao {

	@Override
	public String addNewPlayer(Integer teamId, Player newPlayer) {
		String msg = "Adding player failed";
		Team team = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			team = session.get(Team.class, teamId);
			if (team != null) {
				team.addPlayer(newPlayer);
				msg = "Player added successfully";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return null;
	}

}
