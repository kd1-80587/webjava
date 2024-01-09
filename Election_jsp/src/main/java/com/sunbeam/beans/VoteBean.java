package com.sunbeam.beans;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

public class VoteBean {
	private int candId;
	private int userId;
	private String message;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public VoteBean() {
		// TODO Auto-generated constructor stub
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

	public void registerVote() {
		try (UserDao udao = new UserDaoImpl()) {
			User user = udao.findById(userId);
			if (user.getStatus() != 0) {
				this.message = "You have voted already.";
				return;
			}
			try (CandidateDao cdao = new CandidateDaoImpl()) {
				int count = cdao.incrementVote(candId);
				if (count == 1) {
					count = udao.updateStatus(userId, true);
					if (count == 1)
						this.message = "Your vote registered successfully..";
				}
				if (count == 0) {
					this.message = "Sorry, voting failed..";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
