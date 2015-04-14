package at.itb13.oculus.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "receptionist", catalog = "oculus_c")
@PrimaryKeyJoinColumn(name="employee")
public class Receptionist extends Employee implements java.io.Serializable {
	private static final long serialVersionUID = -6364273493033293093L;

	public Receptionist() {}
}
