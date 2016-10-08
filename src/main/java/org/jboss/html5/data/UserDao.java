package org.jboss.html5.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.html5.data.base.GenericDaoImpl;
import org.jboss.html5.model.access.User;
import org.jboss.html5.model.access.UserFeature;

@Stateless
public class UserDao extends GenericDaoImpl<User, Long>{	  
	
    public User findByEmail(String email) {   			
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> criteria = cb.createQuery(User.class);
            Root<User> user = criteria.from(User.class);
            
            criteria.select(user).where(cb.equal(user.get("email"), email));
            return em.createQuery(criteria).getSingleResult();
        } finally {
            em.close();
        }    	
    }
    
	public List<User> getEagerListAll() {
		List<User> users = new ArrayList<User>();
        users = getListAll();
        
        for (User user : users) {
        	List<UserFeature> features = new ArrayList<UserFeature>();
        	
            CriteriaBuilder cbF = em.getCriteriaBuilder();
            CriteriaQuery<UserFeature> criteriaF = cbF.createQuery(UserFeature.class);
            Root<UserFeature> UserFeature = criteriaF.from(UserFeature.class);
            
            criteriaF.select(UserFeature);
            //criteriaF.where(cbF.equal(UserFeature.get(UserFeature_.), user.getId()));
            
            features = em.createQuery(criteriaF).getResultList();
        	
        	for (UserFeature userFeature : features) {
        		user.getUserFeatures().add(userFeature);
			}
		}
        
        return users;
	}
}
