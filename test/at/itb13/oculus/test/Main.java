package at.itb13.oculus.test;

import java.io.IOException;

import at.itb13.oculus.config.ConfigFactory;
import at.itb13.oculus.database.DBFacade;

public class Main {
	
	public static void main(String[] args) {
		
////		GUIApplication.main(args);		

		try {
			ConfigFactory.getInstance().getConfig(ConfigFactory.CONFIGFILE).load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		DBFacade dbFacade = new DBFacade();
		
		try {
			dbFacade.indexAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbFacade.close();
		
//		SearchResult<Patient> searchResult = dbFacade.search(Patient.class, new SearchMap<>(Patient.class), "Pat");
//		for(String[] res : searchResult.getResults()) {
//			System.out.println(res);
//		};
		
//		try {
//			LangFacade.load();
//		
//		try {
//			LoggerUtil.setup();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		LoggerUtil.close();
//		
//		DBFacade dbFacade = new DBFacade();
////		try {
////			dbFacade.indexAll();
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
////		dbFacade.close();
//		
////		Thread indexService = new IndexService();
////		indexService.start();
//		for(Patient patient : dbFacade.searchPatient("ika sch")) {
//			System.out.println(patient.getFirstname());
//		}
//		dbFacade.close();
//	
////		GUIApplication.main(args);
//		
//		//launch(args);
//		
////		TESTED
////		Anamnesis test = new Anamnesis();
////		Appointment test = new Appointment();
////		CalendarEntry test = new CalendarEntry();
////		Diagnosis test = new Diagnosis();
////		Doctor test = new Doctor();
////		Drug test = new Drug();
////		Employee test = new Employee();
////		EyePrescription test = new EyePrescription();
////		Insurance test = new Insurance();
////		Measurement test = new Measurement();
////		Orthoptist test = new Orthoptist();		
////		Patient test = new Patient();
////		Prescription test = new Prescription();
////		PrescriptionEntry test = new PrescriptionEntry();
////		Queue test = new Queue();
////		QueueEntry test = new QueueEntry();
////		Receptionist test = new Receptionist();
////		Referral test = new Referral();
////		SickNote test = new SickNote();
////		User user = new User();
////		UserRight test = new UserRight();
////		UserRole test = new UserRole();
////
////		Session session = null;
////		try {
////			session = HibernateUtil.openSession();
////			GenericDAO<Patient, String> dao = new GenericDAOImpl<Patient, String>(Patient.class, session);
////			Transaction tx = session.beginTransaction();
////			List<Patient> patients = dao.getByCriterion(Restrictions.like("firstname", "pat"), Restrictions.eq("lastname", "schedler"));
////			for(Patient patient : patients) {
////				System.out.println(patient.getFirstname());
////			}
////			//dao.save(test);
////			tx.commit();
////			session.flush();
////		} finally {
////			if(session != null) {
////				session.close();
////			}
////		}
//		
//		
//		try(DBFacade facade = new DBFacade()) {
////			facade.beginTransaction();
////			List<Patient> patients = facade.getAll(Patient.class);
////			for(Patient patient : patients) {
////				System.out.println(patient.getFirstname());
////			}
////			
//			facade.beginTransaction();
//			
//			List<Patient> patients2 = facade.getSearchedPatient("303013111987");
//			for(Patient patient : patients2) {
//				System.out.println(patient.getFirstname() + " " + patient.getLastname());
//			}
//			facade.commitTransaction();
//			
//			
//			
////			Patient test = new Patient();
////			test.setFirstname("Max");
////			test.setLastname("Mustermann");
////			test.setGender(Gender.MALE);
////			test.setAddress(new Address("Hof", "1104", "6861", "Alberschwende", "Österreich"));
////			facade.create(test);
////			facade.commitTransaction();
//			
////			facade.beginTransaction();
////			Drug drug = facade.get(Drug.class, "965b0461-b8e9-44aa-b9ca-77ab605cd77b");
////			drug.setDescription("Neue Droge");
////			facade.update(drug);
////			facade.commitTransaction();
// 		
//		
	}
}
