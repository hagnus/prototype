package org.jboss.html5.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.jboss.html5.data.UserDao;
import org.jboss.html5.data.UserFeatureDao;
import org.jboss.html5.model.access.User;


public class UserService {
    @Inject
    private Logger log;
    @Inject
    private Validator validator;
    @EJB
    private UserDao userDao; 
    @EJB
    private UserFeatureDao userFeatureDao;
    
    @Inject
    private Event<User> userEventSrc;	
    
    public List<User> getAllOrderedByName() {
    	return userDao.getListAll();
    }
    
    public List<User> getAll() {
    	return userDao.getListAll();
    }    
    
    /*public List<User> getAllWithFeatures() {
		List<User> users = new ArrayList<User>();
		users = userDao.getListAll();
		
		for (User user : users) {
			List<UserFeature> features = new ArrayList<UserFeature>();
			features = userFeatureDao.getListByUser(user.getId());

			for (UserFeature userFeature : features) {				
				user.addUserFeature(userFeature);
			}
		}
    	return users;
    } */
    
    public User getById(Long id) {
        return userDao.getById(id);
    }     
    
    public void add(User user) throws Exception {
        log.info("Registering " + user.getUsername());
        
        // Validates users using bean validation
        validate(user);
        
        userDao.Add(user);
        userEventSrc.fire(user);
    }
    
    public boolean emailAlreadyExists(String email) {
        User user = null;
        try {
            user = userDao.findByEmail(email);
        } catch (NoResultException e) {
            // ignore
        }
        return user != null;
    }
    
    public void validate(User user) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

        // Check the uniqueness of the email address
        if (emailAlreadyExists(user.getEmail())) {
            throw new ValidationException("Unique Email Violation");
        }
    }    
}
