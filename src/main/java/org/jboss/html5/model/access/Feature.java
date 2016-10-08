package org.jboss.html5.model.access;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "feature")
public class Feature implements Serializable {
	private static final long serialVersionUID = -8591255043722449337L;

	@Id
    @GeneratedValue
    @Column(name = "FEATURE_ID")	
	private long id;
    
    @Column(name = "NAME")
    private String name;
    
    @OneToMany(mappedBy = "feature")
    @Transient
    private List<UserFeature> userFeatures;
     
    public Feature() {
    }
 
    public Feature(String name) {
        this.name = name;
    }
    
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public List<UserFeature> getUserFeatures() {
        return userFeatures;
    }
 
    public void setUserFeatures(List<UserFeature> features) {
        this.userFeatures = features;
    }
     
    public void addUserFeature(UserFeature userFeature) {
        this.userFeatures.add(userFeature);
    }
}
