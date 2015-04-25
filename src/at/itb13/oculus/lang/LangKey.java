package at.itb13.oculus.lang;

/**
 * 
 * Represents an entry in the language file that can be access with a specified key
 * 
 */
public enum LangKey {
	ID("id"),
	DOCTOR("doctor"),
	RECEPTIONIST("receptionist"),
	ORTHOPTIST("orthoptist"),
	FIRSTNAME("firstname"),
	LASTNAME("lastname"),
	BIRTHDAY("birthday"),
	GENDER("gender"),
	MALE("male"),
	FEMALE("female"),
	PHONENUMBER("phonenumber"),
	EMAIL("email"),
	ZIP("zip"),
	CITY("city"),
	PATIENTNAME("patientname"),
	APPOINTMENTSTART("appointmentstart"),	
	COUNTRY("country"),
	STREET("street"),
	STREETNUMBER("streetnumber"),
	SOCIALSECURITYNUMBER("socialsecuritynumber"),
	EMPLOYER("employer"),
	SAVE("save"),
	CLEAR("clear"),
	ENGLISH("english"),
	GERMAN("german"),
	LANGUAGE("language"),
	HELP("help"),
	ABOUT("about"),
	FILE("file"),
	EXIT("exit"),
	REGEXPERROR("regexperror"),
	ENTITYEXISTS("entityexists"),
	CREATENEWPATIENT("createnewpatient"),
	NEWPATIENT("newpatient"),
	NEWTREATMENT("newtreatment"),
	DESCRIPTION("description"),
	TITLE("title"),
	START("start"),
	PATIENTSEARCHTITLE("patientsearchtitle"),
	TREATMENTSEARCHTITLE("treatmentsearchtitle"),
	INCOMPLETEDATAHEADER("incompletedataheader"),
	ERRORDIALOGTITEL("errordialogtitel"),
	OBJECTNOTSAVEDCONTENT("objectnotsavedcontent"),
	OBJECTNOTSAVEDHEADER("objectnotsavedheader"),
	NOSEARCHRESULT("nosearchresult");
	
	private String _key;
	
	private LangKey(String key) {
		_key = key;
	}
	
	public String getKey() {
		return _key;
	}
}
