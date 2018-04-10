package com.bo.tournament.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bo.tournament.conditional.StagingDataSourceCondition;
import com.bo.tournament.exception.DataAcessException;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;

@Repository
//@Qualifier(value = "UserMgmtJpaDaoImpl")
@Conditional(value=StagingDataSourceCondition.class)
public class UserMgmtJpaDaoImpl implements UserMgmtDao {

	@PersistenceContext
	private EntityManager entityManager;

	public UserMgmtJpaDaoImpl() {
		System.out.println("User Managment Dao Bean creating");
	}

	@Transactional(readOnly = true)
	@Override
	public TournamentUserMaster getUserDetail(String userName) throws DataAcessException {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TournamentUserMaster> criteriaQuery = entityManager.getCriteriaBuilder()
				.createQuery(TournamentUserMaster.class);
		Root<TournamentUserMaster> root = criteriaQuery.from(TournamentUserMaster.class);
		criteriaQuery = criteriaQuery.where(builder.equal(root.get("username"), userName));
		return (entityManager.createQuery(criteriaQuery).getFirstResult()>0) 
				? entityManager.createQuery(criteriaQuery).getSingleResult():null;
	}

	@Transactional
	@Override
	public void saveUserDetail(TournamentUserMaster userMaster) throws DataAcessException {
		entityManager.persist(userMaster);
	}

}
