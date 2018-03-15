package com.bo.tournament.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bo.tournament.hibernate.HibernateUtil;
import com.bo.tournament.hibernate.mapping.Tournament;

@Repository
public class TournamentMgmtDaoImpl implements TournamentMgmtDao {

	@Override
	public List<Tournament> getTournaments() {
		Session session = HibernateUtil.getSession();
		Criteria crit = session.createCriteria(Tournament.class);
		List<Tournament> list = (List<Tournament>) crit.list();
		HibernateUtil.closeSession();
		return list;
	}

	@Override
	public Tournament getTournament(int tournamentId) {
		Session session = HibernateUtil.getSession();
		Tournament tournament = (Tournament) session.get(Tournament.class, tournamentId);
		HibernateUtil.closeSession();
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
