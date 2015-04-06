/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Measurement;

/**
 * @author Manu
 *
 */
class MeasurementDAO extends GenericDAOImpl<Measurement, String> {

	public MeasurementDAO(Session session) {
		super(Measurement.class, session);
	}
}
