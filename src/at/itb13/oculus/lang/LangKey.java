package at.itb13.oculus.lang;

/**
 * represents an entry in the configuration file that can be access with a specified key
 * @author Patrick
 */
public enum LangKey {
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
	ZIPCODE("zip"),
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
	ENTITYEXISTS("entityexists");
	
	private String _key;
	
	private LangKey(String key) {
		_key = key;
	}
	
	public String getKey() {
		return _key;
	}
}
