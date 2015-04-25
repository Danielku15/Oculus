package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Referral;
/**
 * 
 * DAO (Data Access Object) of all {@link Referral}
 * @category DAO
 *
 */
class ReferralDAO extends GenericDAOImpl<Referral, String> {

	public ReferralDAO(Session session) {
		super(Referral.class, session);
	}
}
