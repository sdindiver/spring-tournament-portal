package com.bo.tournament.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import com.bo.tournament.conditional.DevDataSourceCondition;
import com.bo.tournament.exception.DataAcessException;
import com.bo.tournament.hibernate.HibernateUtil;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;
@Repository
@Qualifier(value="UserMgmtDaoImpl")
@Conditional(value=DevDataSourceCondition.class)
public class UserMgmtDaoImpl implements UserMgmtDao {
	
	public UserMgmtDaoImpl(){
		System.out.println("User Managment Dao Bean creating");
	}

	@Override
	public TournamentUserMaster getUserDetail(String userName) throws DataAcessException {
		TournamentUserMaster userDetails = null;
		try{
			Session session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(TournamentUserMaster.class);
			criteria.add(Restrictions.eq("username", userName));
			userDetails = (TournamentUserMaster) criteria.uniqueResult();
			HibernateUtil.closeSession();
		}catch(Exception e){
			HibernateUtil.closeSession();
			throw new DataAcessException("Some Problem with Database");
		}
		return userDetails;
	}

	@Override
	public void saveUserDetail(TournamentUserMaster userMaster) throws DataAcessException{
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(userMaster);
			tx.commit();
			HibernateUtil.closeSession();
		}catch(Exception e){
			try {
				tx.rollback();
				e.printStackTrace();
			} catch (Exception ex1) {
				ex1.printStackTrace();
				HibernateUtil.closeSession();
				throw new DataAcessException("Some Problem with Database");
			}
		}
		
	}


}
