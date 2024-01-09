package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import static utils.HibernateUtils.getFactory;

import pojos.Player;
import pojos.Team;

public class PlayerDaoImpl implements PlayerDao {

	@Override
	public String addNewPlayer(Integer teamId, Player newPlayer) {
		String mesg="Adding new player failed!!!!!!!!";
		// 1. get session from SF
		Session session=getFactory().getCurrentSession();
		//2. begin a tx
		Transaction tx=session.beginTransaction();
		try {
			//get team from it's id
			Team team=session.get(Team.class, teamId);
			if(team != null)
			{
				team.addPlayer(newPlayer);
				mesg="Added new player .....";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
	
		return mesg;
	}

}
