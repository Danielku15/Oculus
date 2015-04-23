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
public interface TreatmentController extends AutoCloseable{

	//getter
	String getPatientFirstname();
	String getPatientLastname();
	String getPatientSocialSecurityNumber();
	
	//operations
	void activate();
	void createAppointment();
	void loadAppointment(String appointmentId) throws ObjectNotFoundException;
	void saveAppointment() throws IncompleteDataException, ObjectNotSavedException;
	boolean validateData() throws IncompleteDataException;
}
