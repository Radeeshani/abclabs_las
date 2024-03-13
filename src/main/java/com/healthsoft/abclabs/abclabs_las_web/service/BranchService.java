package com.healthsoft.abclabs.abclabs_las_web.service;

import com.healthsoft.abclabs.abclabs_las_web.dao.BranchManager;
import com.healthsoft.abclabs.abclabs_las_web.model.Branch;

public class BranchService {
	private static BranchService branchService;
	
	private BranchService() {
		
	}
	
	public static BranchService getBranchService() {
		if(branchService==null) {
			branchService=new BranchService();
		}
		return branchService;
	}
	
	private BranchManager getBranchManager() {
		return new BranchManager();
	}
	
	public Branch getBranchById(int id) throws Exception {
		return getBranchManager().getBranchById(id);
	}
}
