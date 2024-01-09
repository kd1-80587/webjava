package com.app.dao;

import static com.app.utils.HibernateUtils.getFactory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.pojos.Owner;
import com.app.pojos.Team;

public class TeamDaoImpl implements TeamDao {

	@Override
	public List<Team> getTeamDetails() {
		List<Team> teams = null;
		// 1. get session from SF
		String jpql = "select new pojos.Team(id,abbreviation) from Team t";
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			teams = session.createQuery(jpql, Team.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return teams;
	}

	@Override
	public Team getSelectedTeamDetails(Integer teamId) {
		Team team=null;
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			team=session.get(Team.class, teamId);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return team;
	}

	@Override
	public String addTeamForSpecificOwner(String email, Team newTeam) {
		String msg="Adding Team failed..!";
		String jpql="select o from Owner o where o.email=:email";
		Owner owner=null;
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			owner=session.createQuery(jpql, Owner.class)
					.setParameter("email", email)
					.getSingleResult();
			Team team=new Team();
			team.setMyOwner(owner);
			System.out.println(team);
			msg="Team added successfully..!";
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}

	@Override
	public String updateTeamDetails(String name) {
		String msg="Team updation failed..!";
//		String jpql="select t from Team t where t.name=:teamName";
//		Team team=new Team();
//		Session session = getFactory().getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		try {
//			team=session.createQuery(jpql, Team.class).setParameter("teamName", name).getSingleResult();
//			team.get
//		} catch (RuntimeException e) {
//			if (tx!=null) {
//				tx.rollback();
//			}
//			throw e;
//		}
		return msg;
	}

	@Override
	public String removeTeam(String abbreviation) {
		String msg="Deletion failed..!";
		Team team=null;
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			team=session.get(Team.class, abbreviation);
			if (team!=null) {
				session.delete(team);
				msg="Team deleted successfully";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx!=null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}
}
