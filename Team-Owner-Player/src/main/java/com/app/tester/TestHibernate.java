package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;

import org.hibernate.SessionFactory;

public class TestHibernate {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory()) {
			System.out.println("Hibernate booted successfully!!!!");
		} //JVM : sf.close=> DBCP is clened up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
