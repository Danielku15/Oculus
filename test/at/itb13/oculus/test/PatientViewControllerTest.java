package at.itb13.oculus.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import at.itb13.oculus.application.*;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.model.*;

public class PatientViewControllerTest {
	PatientViewController patientController;
	Patient patient;
	
	@Before public void setUp() {
		patientController = new PatientViewControllerImpl_old();
		patient = new Patient("Carlos Gabriel","Rodriguez-Lopez", null, null, null, null, null, "0123456789",null,null,null);
		try{
			ConfigFacade.load();
			LangFacade.load();
		}catch(IOException e){
			fail(e.toString());
		}
		
	}
	
	@Test(expected = RegExpException.class)
	public void test() {
		try{
			String id = patientController.createPatient(patient);
			patient.setID(id);
		}catch(UniqueConstraintException e){
			fail(e.toString());
		}catch(RegExpException e){
			fail(e.toString());
		}
	}

}
