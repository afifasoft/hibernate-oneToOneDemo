package com.alakesoftware.hb01onetoone.demo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alakesoftware.hb01onetoone.entity.Instructor;
import com.alakesoftware.hb01onetoone.entity.InstructorDetail;

@Component
public class CreateInstructorDemo implements CommandLineRunner{

	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("CreateInstructorDemo ...");
		
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		
		try {
			
			// create the objects
			Instructor tempInstructor = new Instructor("Mike", "Rose", "rosemike@gmail.com");
			
			InstructorDetail tempInstructorDetail = new 
					InstructorDetail("https://youtube.com/mikerose", "Reading Books");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			
			// this will also save the instructorDetail object bcz of CascadeType.ALL
			// save the instructor
			System.out.println("Saving instructor : " + tempInstructor);
			session.save(tempInstructor);
			
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			session.clear();
			//sessionFactory.close();
		}
		
		
	}

}
