package com.codegnan;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.codegnan.entity.Address;
import com.codegnan.entity.Governer;

public class App {
	public static void main(String[] args) {

		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;
		try {
			registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();

			try (Session session = sessionFactory.openSession()) {
//				Transaction tx = session.beginTransaction();
//				Address address = new Address("Hyderabad", "Telangana", "500001");
//				Governer governer = new Governer("Jishnu Dev Varma", 400000);
//				address.setGoverner(governer);
//				governer.setAddress(address);
//				session.save(governer);
//				tx.commit();
//				System.out.println("Record inserted succesfully");
//				System.out.println(governer);
				System.out.println("Retreiving Governer and Address");
				Governer gov = session.get(Governer.class, 1);
				if(gov!=null) {
					System.out.println("Governer Id: "+gov.getGovId());
					System.out.println("Governer Name: "+gov.getGovName());
					System.out.println("Governer Salary: "+gov.getGovSalary());
					Address addr = gov.getAddress();
					if(addr!=null) {
						System.out.println("Address Details");
						System.out.println("City: "+addr.getCity());
						System.out.println("State: "+addr.getState());
						System.out.println("Pin Code: "+addr.getPincode());
					} else {
						System.out.println("No Address Found for this Governer");
					}
				} else {
					System.out.println("Governer not found");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionFactory != null) {
				sessionFactory.close();
			}
			if (registry != null) {
				StandardServiceRegistryBuilder.destroy(registry);
			}
		}
	}
}
