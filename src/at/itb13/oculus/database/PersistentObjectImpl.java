package at.itb13.oculus.database;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import at.itb13.oculus.util.IdGenerator;

/**
 * @author Patrick
 *
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class PersistentObjectImpl implements PersistentObject {
	
    private String _id = IdGenerator.createId();
	private int _version;

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
    public String getID() {
        return _id;
    }

	public void setID(String id) {
		_id = id;
    }

	@Version
	@Column(name = "version")
    public int getVersion() {
    	return _version;
    }
    
    public void setVersion(int version) {
    	_version = version;
    }

    @Override
    public boolean equals(Object object) {
    	// if it's the same reference, return true
        if (this == object) {
        	return true;
        }
        
        // if it's null or not a persistence object, return false
        if (object == null || !(object instanceof PersistentObjectImpl)) {
            return false;
        }
        
        PersistentObjectImpl other = (PersistentObjectImpl) object;
        // if the UUID is missing, return false
        if (_id == null) {
        	return false;
        }

        // equivalence by UUID
        return _id.equals(other.getID());
    }

    @Override
    public int hashCode() {
        if (_id != null) {
            return _id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    @Override
    public String toString() {
        return getClass().getName()+ "[id=" + _id + "]";
    }
}
