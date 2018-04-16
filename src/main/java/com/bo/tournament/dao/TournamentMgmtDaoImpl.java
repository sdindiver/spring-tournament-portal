package com.bo.tournament.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bo.tournament.hibernate.HibernateUtil;
import com.bo.tournament.hibernate.mapping.Tournament;

@Repository
public class TournamentMgmtDaoImpl implements TournamentMgmtDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Tournament> getTournaments() {
		//Session session = HibernateUtil.getSession();
		Session session = sessionFactory.openSession();
		Criteria crit = session.createCriteria(Tournament.class);
		List<Tournament> list = (List<Tournament>) crit.list();
		//HibernateUtil.closeSession();
		session.close();
		return list;
	}

	@Override
	public Tournament getTournament(int tournamentId) {
		Session session = sessionFactory.openSession();
		Tournament tournament = (Tournament) session.get(Tournament.class, tournamentId);
		//HibernateUtil.closeSession();
		session.close();
		return tournament;
	}

	@Override
	public boolean deleteTournament(int tournamentId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateTournament(Tournament tournament) {
		HibernateUtil.getSession().update(tournament);
		HibernateUtil.closeSession();

	}

	@Override
	public void saveTournament(Tournament tournament) {
		HibernateUtil.getSession().save(tournament);
		HibernateUtil.closeSession();

	}

}
