package org.jboss.html5.model.access;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USERS_FEATURES")
public class UserFeature implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    @Column(name = "USER_FEATURE_ID")    
	private long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_FK", referencedColumnName="USER_ID")
    private User user;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FEATURE_FK", referencedColumnName="FEATURE_ID")
    private Feature feature;
     
    @Column(name = "ACTIVATED")
    private boolean activated;
    
    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.DATE)
    private Date registeredDate;
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
  
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public Feature getFeature() {
        return feature;
    }
 
    public void setFeature(Feature feature) {
        this.feature = feature;
    }
    
    public boolean getActivated() {
        return activated;
    }

	public void setActivated(boolean activated) {
        this.activated = activated;
    }
 
    public Date getRegisteredDate() {
        return registeredDate;
    }
 
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
