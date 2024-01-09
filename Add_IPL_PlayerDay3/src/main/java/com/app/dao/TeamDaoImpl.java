package com.app.dao;

import static com.app.utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.pojo.Team;

public class TeamDaoImpl implements TeamDao {

	@Override
	public String addNewTeam(Team newTeam) {
		String msg = "Adding Team failed";
		/*
		 * get Session from SF. begin a tx. try-save team details,commit catch-runtime.
		 * exc: rollback tx,throw exc e.
		 */
//		Get session from SF
		Session session = getFactory().getCurrentSession();
//		Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			Serializable teamId = session.save(newTeam);
			tx.commit();
			msg = "Team added with id: " + teamId;
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		}
		return msg;
	}

	@Override
	public Team getTeamDetailsById(Integer id) {
		Team team = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			team = session.get(Team.class, id);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		}
		return team;
	}

	@Override
	public List<Team> getAllTeamIdAbbrevation() {
		List<Team> team = null;
		String jpql = "select new com.app.pojo.Team(id,abbrevation) from Team t";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			team = session.createQuery(jpql, Team.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return team;
	}

	@Override
	public List<Team> displayTeamsBySpecifications(int maxAge, int wicketsTaken) {
		List<Team> team = null;
		String jpql = "select t from Team t where t.maxAge<:maxAgeLimit and t.wicketsTaken>:totalWicketsTaken";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			team = session.createQuery(jpql, Team.class).setParameter("maxAgeLimit", maxAge)
					.setParameter("totalWicketsTaken", wicketsTaken).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.commit();
			}
			throw e;
		}
		return team;
	}

	@Override
	public String updateMaxAgeAndBattingAvg(String name, int maxAge, double battingAvg) {
		String msg = "Updation Failed..!";
		String jpql = "select t from Team t where t.name=:name";
		Team team = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			team = session.createQuery(jpql, Team.class).setParameter("name", name).getSingleResult();
			team.setMaxAge(team.getMaxAge() + maxAge);
			team.setBattingAvg(team.getBattingAvg() + battingAvg);
			msg = "Updated " + team.getName() + " max age to: " + team.getMaxAge() + " & battingAvg to: "
					+ team.getBattingAvg();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}

	@Override
	public String deleteTeamById(Integer teamId) {
		String msg = "Team deletion failed..!";
		Team team = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			team = session.get(Team.class, teamId);
			if (team != null) {
				session.delete(team);
				msg = "Details of deleted team: " + team.getName();
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}

	@Override
	public Team getSelectedTeamDetails(Integer teamId) {
		Team team = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			team=session.get(Team.class, teamId);
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return team;
	}

}
