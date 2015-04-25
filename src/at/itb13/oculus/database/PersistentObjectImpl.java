package at.itb13.oculus.database;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDefs;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import at.itb13.oculus.util.IdGenerator;
/**
 * 
 * implements {@link PersistentObject} and describes the search functionality and search algorithms
 *
 */
@AnalyzerDefs({
	@AnalyzerDef(name = "AutoCompleteAnalyzer",
	tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
	filters = {
		@TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
			@Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
			@Parameter(name = "replacement", value = " "),
			@Parameter(name = "replace", value = "all")
		}),
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
			@Parameter(name = "minGramSize", value = "3"),
			@Parameter(name = "maxGramSize", value = "50")
		})
	}),
	@AnalyzerDef(name = "NGramAnalyzer",
	tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class ),
	filters = {
		@TokenFilterDef(factory = StandardFilterFactory.class),
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = NGramFilterFactory.class, params = {
			@Parameter(name = "minGramSize", value = "3"),
			@Parameter(name = "maxGramSize", value = "5")
	    })
	})
})

@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
@Analyzer(definition="NGramAnalyzer")
public abstract class PersistentObjectImpl implements PersistentObject {
	
    private String _id = IdGenerator.createId();
	private Integer _version = null;

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
    public Integer getVersion() {
    	return _version;
    }
    
    public void setVersion(Integer version) {
    	_version = version;
    }
    
    public boolean isCreation() {
    	return (_version == null);
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
