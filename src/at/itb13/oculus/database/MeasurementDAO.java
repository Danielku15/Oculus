package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Measurement;
/**
 * 
 * DAO (Data Access Object) of all {@link Measurement}
 * @category DAO
 *
 */
class MeasurementDAO extends GenericDAOImpl<Measurement, String> {

	public MeasurementDAO(Session session) {
		super(Measurement.class, session);
	}
}
