/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Referral;

/**
 * @author Manu
 *
 */
class ReferralDAO extends GenericDAOImpl<Referral, String> {

	public ReferralDAO(Session session) {
		super(Referral.class, session);
	}
}
