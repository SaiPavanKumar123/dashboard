package com.insurance.Hospital.Repositories;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.Hospital.contractors.DashboardDaoInterface;
import com.insurance.Hospital.contractors.DashboardRepositoryInterface;
import com.insurance.Hospital.models.ClaimBills;
import com.insurance.Hospital.models.Claim;

@Service
public class DashboardRepository implements DashboardRepositoryInterface {

	@Autowired
	DashboardDaoInterface dbi;

	@Override
	public List<ClaimBills> getRejectedLoans() {

		return dbi.getRejectedLoans();
	}
	@Override
	public List<Claim> getAllApplicants() {

		return dbi.getAllApplicants();
	}

	@Override
	public List<Claim> getClaimedAmount() {

		return dbi.getClaimedAmount();
	}

	@Override
	public List<Claim> getTotalAmount() {

		return dbi.getTotalAmount();
	}

	@Override
	public List<Claim> getActiveApplicants() {

		return dbi.getActiveApplicants();
	}
}
