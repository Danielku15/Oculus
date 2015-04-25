package at.itb13.oculus.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "orthoptist", catalog = "oculus_c")
@PrimaryKeyJoinColumn(name="employee")
public class Orthoptist extends Employee implements java.io.Serializable {
	private static final long serialVersionUID = -3669100652643276339L;

	public Orthoptist() {}
}
