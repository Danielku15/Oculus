package at.itb13.oculus.test;

import java.io.IOException;
import java.util.List;

import at.itb13.oculus.config.Config;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.database.DBFacade;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Drug;
import at.itb13.oculus.model.Patient;

public class Main {

	public static void main(String[] args) {
		try {
			ConfigFacade.load();
			ConfigFacade configFacade = ConfigFacade.getInstance();
			System.out.println(configFacade.getProperty(Config.LANGUAGE));
			System.out.println(configFacade.getProperty(Config.COUNTRY));
			
			LangFacade.load();
			LangFacade langFacade = LangFacade.getInstance();
			System.out.println(langFacade.getString(LangKey.DOCTOR));
			System.out.println(langFacade.getString(LangKey.ORTHOPTIST));
			System.out.println(langFacade.getString(LangKey.RECEPTIONIST));			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		
		try(DBFacade facade = new DBFacade()) {
			facade.beginTransaction();
			List<Patient> patients = facade.getAll(Patient.class);
			for(Patient patient : patients) {
				System.out.println(patient.getFirstname());
			}
			facade.commitTransaction();
			
//			facade.beginTransaction();
//			Drug drug = facade.get(Drug.class, "965b0461-b8e9-44aa-b9ca-77ab605cd77b");
//			drug.setDescription("Neue Droge");
//			facade.update(drug);
//			facade.commitTransaction();
		}
	}
}
