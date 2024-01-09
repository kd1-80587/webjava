package com.sunbeam.beans;

import java.util.List;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

public class CandidateListBean {
	List<Candidate> candlist;

	public CandidateListBean() {

	}

	public List<Candidate> getCandlist() {
		return candlist;
	}

	public void setCandlist(List<Candidate> candlist) {
		this.candlist = candlist;
	}

	public void fetchCandidateList() {
		try (CandidateDao cDao = new CandidateDaoImpl()) {
			candlist = cDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
