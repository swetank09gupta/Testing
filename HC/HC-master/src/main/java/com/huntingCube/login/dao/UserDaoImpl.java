package com.huntingCube.login.dao;

import com.huntingCube.global.resources.dao.AbstractDao;
import com.huntingCube.login.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int id) {

		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		try {
			logger.info("SSO : {}", sso);
			Criteria crit = createEntityCriteriaWithoutDeleted();
			crit.add(Restrictions.eq("ssoId", sso));
			User user = (User)crit.uniqueResult();
			if(user!=null){
                Hibernate.initialize(user.getUserProfiles());
            }
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteriaWithoutDeleted().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteriaWithoutDeleted();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

}
