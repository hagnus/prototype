package org.jboss.html5.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.html5.data.base.GenericJpaDao;
import org.jboss.html5.model.access.UserFeature;

@Stateless
public class UserFeatureDao extends GenericJpaDao<UserFeature, Long> {	
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
	}
	
	public List<UserFeature> getListByUser(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserFeature> criteria = cb.createQuery(UserFeature.class);
        Root<UserFeature> root = criteria.from(UserFeature.class);
        
        criteria.select(root).where(cb.equal(root.get("userId"), userId));
        
        return em.createQuery(criteria).getResultList();
	}
}
