/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 22.04.2015
 */
package at.itb13.oculus.application;

/**
 * @author Manu
 *
 */
public interface AppointmentController extends AutoCloseable{

	//getter
	String getFirstName();
	String getLastName();
	String getSocialSecurityNumber();
	
	//setter
	
	//operations
	void loadPatient(String patientId) throws ObjectNotFoundException;
}
