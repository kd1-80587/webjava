package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;
import java.util.Scanner;
import org.hibernate.SessionFactory;
import com.app.dao.OwnerDao;
import com.app.dao.OwnerDaoImpl;
import com.app.pojos.Owner;

public class AddNewOwner {
	
public static void main(String[] args) {
	try (SessionFactory sf=getFactory()){
		Scanner sc=new Scanner(System.in);
		OwnerDaoImpl ownerDao=new OwnerDaoImpl();
		System.out.println("Enter the owner details:fName,lName,email");
		Owner owner=new Owner(sc.next(),sc.next(),sc.next());
		System.out.println(ownerDao.addNewOwner(owner));
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
