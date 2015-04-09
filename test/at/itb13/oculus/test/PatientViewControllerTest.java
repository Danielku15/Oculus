package at.itb13.oculus.test;

import static org.junit.Assert.*;

import java.util.regex.PatternSyntaxException;

import org.junit.Test;

import at.itb13.oculus.application.*;
import at.itb13.oculus.model.*;

public class PatientViewControllerTest {

	PatientViewController patientController = new PatientViewControllerImpl();
	Patient patient = new Patient("123","d12",null,null,null,null,null,null,null,null,null);
	
	@Test
	public void test() {
		try{
			assertEquals(patientController.createPatient(patient), "12");
			
		}catch(PatternSyntaxException e){
			System.out.println(e.toString());
		}
	}

}
