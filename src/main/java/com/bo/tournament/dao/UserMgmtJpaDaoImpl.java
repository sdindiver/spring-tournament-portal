package com.bo.tournament.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bo.tournament.conditional.StagingDataSourceCondition;
import com.bo.tournament.exception.DataAcessException;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;

@Repository
@Qualifier(value = "UserMgmtJpaDaoImpl")
@Conditional(value=StagingDataSourceCondition.class)
public class UserMgmtJpaDaoImpl implements UserMgmtDao {

	@PersistenceContext
	private EntityManager entityManager;

	public UserMgmtJpaDaoImpl() {
		System.out.println("User Managment Dao Bean creating");
	}

	@Transactional(value = "transactionManager")
	@Override
	public TournamentUserMaster getUserDetail(String userName) throws DataAcessException {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TournamentUserMaster> criteriaQuery = entityManager.getCriteriaBuilder()
				.createQuery(TournamentUserMaster.class);
		Root<TournamentUserMaster> root = criteriaQuery.from(TournamentUserMaster.class);
		criteriaQuery = criteriaQuery.where(builder.equal(root.get("username"), userName));
		TypedQuery<TournamentUserMaster>  typedQuery = entityManager.createQuery(criteriaQuery);
		TournamentUserMaster userDetails=null;
		try{
			System.out.println("cheking if user exist.....");
			userDetails = typedQuery.getSingleResult();
			System.out.println("checked  user exist.....");
		}catch(NoResultException e){
			userDetails=null;
		}
		
		return userDetails;
	}

	@Transactional(value = "transactionManager")
	@Override
	public void saveUserDetail(TournamentUserMaster userMaster) throws DataAcessException {
		entityManager.persist(userMaster);
	}

}
