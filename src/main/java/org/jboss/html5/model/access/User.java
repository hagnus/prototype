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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL"))
public class User implements Serializable {
	private static final long serialVersionUID = -9035979791040809493L;

	@Id
    @GeneratedValue
    @Column(name = "USER_ID")	
	private long id;
    
    @Column(name = "USERNAME")    
    private String username;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "EMAIL")    
    private String email;  
 
    @OneToMany(mappedBy = "user")
    @Transient
    private List<UserFeature> userFeatures;
 
    public User() {
    }
 
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
     
    public void addFeature(UserFeature feature) {
        this.userFeatures.add(feature);
    }
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
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
