package com.bo.tournament.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import com.bo.tournament.conditional.DevDataSourceCondition;
import com.bo.tournament.exception.DataAcessException;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;

@Repository
//@Qualifier(value="UserMgmtAutowiredDaoImpl")
@Conditional(value=DevDataSourceCondition.class)
public class UserMgmtAutowiredDaoImpl implements UserMgmtDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public TournamentUserMaster getUserDetail(String userName) throws DataAcessException {
		TournamentUserMaster userDetails = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(TournamentUserMaster.class);
			criteria.add(Restrictions.eq("username", userName));
			userDetails = (TournamentUserMaster) criteria.uniqueResult();
			session.close();
		} catch (Exception e) {
			session.close();
			throw new DataAcessException("Some Problem with Database");
		}
		return userDetails;
	}

	@Override
	public void saveUserDetail(TournamentUserMaster userMaster) throws DataAcessException {
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(userMaster);
			tx.commit();
			session.close();
		} catch (Exception e) {
			try {
				tx.rollback();
				e.printStackTrace();
			} catch (Exception ex1) {
				ex1.printStackTrace();
				session.close();
				throw new DataAcessException("Some Problem with Database");
			}
		}
	}

}
