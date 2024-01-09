package com.sunbeam.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sunbeam.util.DBUtil;

public class Dao implements AutoCloseable {
	public Connection con;

	public Dao() throws Exception {
		con = DBUtil.getConnection();
	}

	@Override
	public void close() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

}
