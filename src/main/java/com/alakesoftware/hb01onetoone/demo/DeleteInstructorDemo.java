package com.alakesoftware.hb01onetoone.demo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alakesoftware.hb01onetoone.entity.Instructor;

@Component
public class DeleteInstructorDemo implements CommandLineRunner{

	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("DeleteInstructorDemo");
		
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		
		
		try {
			
			session.beginTransaction();
			
			// get instructor by primary key / id
			int theId = 1;
			Instructor tempInstructor = 
					session.get(Instructor.class, theId);
			
			System.out.println("Found Instructor: "+ tempInstructor);
			
			// delete the instructors
			if(tempInstructor != null) {
				
				System.out.println("Deleting: " + tempInstructor);
				
				// will also delete associated "instructorDetail" object
				// because of CascadeType.ALL
				session.delete(tempInstructor);
				
			}
			
			
			session.getTransaction().commit();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
		
	}

}
