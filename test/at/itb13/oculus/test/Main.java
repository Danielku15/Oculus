package at.itb13.oculus.test;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import at.itb13.oculus.config.Config;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.database.DBFacade;
import at.itb13.oculus.lang.Lang;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Address;
import at.itb13.oculus.model.Gender;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.presentation.CreateNewPatientGUIController;
import at.itb13.oculus.presentation.GUIApplication;

public class Main {
	
	public static void main(String[] args) {
		
		GUIApplication.main(args);
		
		//launch(args);
		
//		TESTED
//		Anamnesis test = new Anamnesis();
//		Appointment test = new Appointment();
//		CalendarEntry test = new CalendarEntry();
//		Diagnosis test = new Diagnosis();
//		Doctor test = new Doctor();
//		Drug test = new Drug();
//		Employee test = new Employee();
//		EyePrescription test = new EyePrescription();
//		Insurance test = new Insurance();
//		Measurement test = new Measurement();
//		Orthoptist test = new Orthoptist();		
//		Patient test = new Patient();
//		Prescription test = new Prescription();
//		PrescriptionEntry test = new PrescriptionEntry();
//		Queue test = new Queue();
//		QueueEntry test = new QueueEntry();
//		Receptionist test = new Receptionist();
//		Referral test = new Referral();
//		SickNote test = new SickNote();
//		User user = new User();
//		UserRight test = new UserRight();
//		UserRole test = new UserRole();
//
//		Session session = null;
//		try {
//			session = HibernateUtil.openSession();
//			GenericDAO<Patient, String> dao = new GenericDAOImpl<Patient, String>(Patient.class, session);
//			Transaction tx = session.beginTransaction();
//			List<Patient> patients = dao.getByCriterion(Restrictions.like("firstname", "pat"), Restrictions.eq("lastname", "schedler"));
//			for(Patient patient : patients) {
//				System.out.println(patient.getFirstname());
//			}
//			//dao.save(test);
//			tx.commit();
//			session.flush();
//		} finally {
//			if(session != null) {
//				session.close();
//			}
//		}
		
		/*
		try(DBFacade facade = new DBFacade()) {
			facade.beginTransaction();
			List<Patient> patients = facade.getAll(Patient.class);
			for(Patient patient : patients) {
				System.out.println(patient.getFirstname());
			}
			Patient test = new Patient();
			test.setFirstname("Max");
			test.setLastname("Mustermann");
			test.setGender(Gender.MALE);
			test.setAddress(new Address("Hof", "1104", "6861", "Alberschwende", "Österreich"));
			facade.create(test);
			facade.commitTransaction();
			
//			facade.beginTransaction();
//			Drug drug = facade.get(Drug.class, "965b0461-b8e9-44aa-b9ca-77ab605cd77b");
//			drug.setDescription("Neue Droge");
//			facade.update(drug);
//			facade.commitTransaction();
 		*/
		
	}
	
	

  
    

}
