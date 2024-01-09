package com.app.dao;
import static com.app.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.pojos.Owner;
import com.app.pojos.Team;

public class OwnerDaoImpl implements OwnerDao {

	@Override
	public String addNewOwner(Owner newOwner) {
		String msg="Adding Owner failed..!!";
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			session.persist(newOwner);
			msg="Owner added successfully..!!";
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
