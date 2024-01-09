package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.app.dao.TeamDaoImpl;

public class RemoveTeam {

	public static void main(String[] args) {
		try (SessionFactory sf=getFactory();
				Scanner sc=new Scanner(System.in)){
			TeamDaoImpl dao=new TeamDaoImpl();
			System.out.println("Enter the team abbreviation:");
			System.out.println(dao.removeTeam(sc.next()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
