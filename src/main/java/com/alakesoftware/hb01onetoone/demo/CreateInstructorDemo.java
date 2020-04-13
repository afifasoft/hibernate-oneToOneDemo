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
			Instructor tempInstructor1 = new Instructor("John", "K", "johnk@gmail.com");
			
			InstructorDetail tempInstructorDetail = new 
					InstructorDetail("https://youtube.com/mikerose", "Reading Books");
			
			InstructorDetail tempInstructorDetail1 = new 
					InstructorDetail("https://youtube.com/john", "Playing Cricket");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			tempInstructor1.setInstructorDetail(tempInstructorDetail1);
			
			// start a transaction
			session.beginTransaction();
			
			
			// this will also save the instructorDetail object bcz of CascadeType.ALL
			// save the instructor
			System.out.println("Saving instructor : " + tempInstructor);
			session.save(tempInstructor);
			session.save(tempInstructor1);
			
			
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
