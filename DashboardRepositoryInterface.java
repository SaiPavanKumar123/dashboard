package com.insurance.Hospital.contractors;

import java.util.List;


import com.insurance.Hospital.models.ClaimBills;
import com.insurance.Hospital.models.Claim;

public interface DashboardRepositoryInterface {

	List<ClaimBills> getRejectedLoans();

	List<Claim> getAllApplicants();

	List<Claim> getClaimedAmount();

	List<Claim> getTotalAmount();

	List<Claim> getActiveApplicants();
}
