package com.sunbeam.beans;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;

public class DeleteCandidateBean {
private int candId;
private String message;
public DeleteCandidateBean() {
}
public int getCandId() {
	return candId;
}
public void setCandId(int candId) {
	this.candId = candId;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public void deleteCandidate() {
	try (CandidateDao cDao=new CandidateDaoImpl()){
		int count=cDao.deleteById(candId);
		this.message=count +" candidate deleted successfully..";
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
}
}
