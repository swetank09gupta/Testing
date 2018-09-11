package com.huntingCube.login.dao;

import com.huntingCube.global.resources.dao.AbstractDao;
import com.huntingCube.login.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {

	public UserProfile findById(int id) {
		return getByKey(id);
	}

	public UserProfile findByType(String type) {
		Criteria crit = createEntityCriteriaWithoutDeleted();
		crit.add(Restrictions.eq("type", type));
		return (UserProfile) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){
		Criteria crit = createEntityCriteriaWithoutDeleted();
		crit.addOrder(Order.asc("type"));
		return (List<UserProfile>)crit.list();
	}
	
}
