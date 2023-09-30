package com.insurance.Hospital.daos;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.insurance.Hospital.contractors.DashboardDaoInterface;
import com.insurance.Hospital.models.ClaimBills;
import com.insurance.Hospital.models.Claim;
import com.insurance.Hospital.rowmappers.ClaimBillsRowMappers;
import com.insurance.Hospital.rowmappers.ClaimMapper;

@Component
public class DashboardDao implements DashboardDaoInterface {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String SELECT_ALL_CLAIM_BILLS = "SELECT * FROM _claims  ";
	private static final String SELECT_FULL_CLAIM_BILLS = "SELECT * FROM _claims WHERE (LOWER(clam_status) = LOWER('APRV')) and (EXTRACT(YEAR FROM clam_date) = EXTRACT(YEAR FROM CURRENT_DATE))";
	private static final String SELECT_FULL_CLAIMED_AMOUNT = "SELECT * FROM _claims where LOWER(pay_status)=LOWER('PAID')";
	private static final String SELECT_TOTAL_AMOUNT = "SELECT * FROM _claims WHERE (LOWER(pay_status) = LOWER('PENDING...')) or (LOWER(pay_status)=LOWER('PAID')) ";
	private static final String SELECT_ALL_REJECTED = "SELECT * FROM claim_bills WHERE LOWER(clbl_status)=LOWER('REJT')";

	@Override
	public List<Claim> getAllApplicants() {
		//get all the applicants from database
		return jdbcTemplate.query(SELECT_ALL_CLAIM_BILLS, new ClaimMapper());

	}

	@Override
	public List<Claim> getActiveApplicants() {
		//get all active applicants for the current year
		return jdbcTemplate.query(SELECT_FULL_CLAIM_BILLS, new ClaimMapper());
	}

	@Override
	public List<ClaimBills> getRejectedLoans() {
		// get all the rejected claims.
		return jdbcTemplate.query(SELECT_ALL_REJECTED, new ClaimBillsRowMappers());

	}

	@Override
	public List<Claim> getClaimedAmount() {
		// list the completely claimed amount from hospital side 
		return jdbcTemplate.query(SELECT_FULL_CLAIMED_AMOUNT, new ClaimMapper());
	}

	@Override
	public List<Claim> getTotalAmount() {
		//get the paid and processing amount from the _claims table
		return jdbcTemplate.query(SELECT_TOTAL_AMOUNT, new ClaimMapper());
	}

}
