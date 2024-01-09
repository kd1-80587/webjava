package com.app.utils;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory factory;
	static {
		System.out.println("in static init block");
		factory=new Configuration() //empty config object
				     .configure() //reads props from hib cfg file : populated config
				     .buildSessionFactory();
		System.out.println("SF created ....");
	}
	public static SessionFactory getFactory() {
		return factory;
	}
	

}
