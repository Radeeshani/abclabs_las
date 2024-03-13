package com.healthsoft.abclabs.abclabs_las_web.dao;

import java.sql.ResultSet;

import com.healthsoft.abclabs.abclabs_las_web.model.Branch;

public class BranchManager {

	public Branch getBranchById(int id) throws Exception {
		Branch branch = null;

		ResultSet rs = MySQL.search("SELECT * FROM `branch` WHERE `id`=" + id);
		if (rs.next()) {
			branch = new Branch();
			branch.setId(rs.getInt("id"));
			branch.setCity(rs.getString("city"));
			branch.setAddress(rs.getString("address"));
			branch.setTelephone(rs.getString("telephone"));
		}

		return branch;
	}

}
