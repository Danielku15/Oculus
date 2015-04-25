package at.itb13.oculus.application;

import java.util.List;
/**
 * Is thrown when the systems expected input is incomplete or missing
 * @author Patrick
 *
 */
public class IncompleteDataException extends Exception {
	private static final long serialVersionUID = 4362486823868138704L;
	
	private List<String> _fieldNames;
	
	public IncompleteDataException(List<String> fieldNames) {
		_fieldNames = fieldNames;
	}
	
	public List<String> getFieldNames() {
		return _fieldNames;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("The values of the following fields are invalid or missing: ");
		for(String fieldName : _fieldNames) {
			strBuilder.append(fieldName);
			strBuilder.append("; ");
		}
		return strBuilder.toString();
	}
}
