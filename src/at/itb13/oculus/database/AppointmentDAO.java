/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;


import org.hibernate.Session;
import at.itb13.oculus.model.Appointment;

/**
 * @author Manu
 *
 */
class AppointmentDAO extends GenericDAOImpl<Appointment, String> {


	public AppointmentDAO(Session session) {
		super(Appointment.class, session);

	}


}
