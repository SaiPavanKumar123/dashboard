package com.insurance.Hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insurance.Hospital.contractors.DashboardRepositoryInterface;
import com.insurance.Hospital.models.ClaimBills;
import com.insurance.Hospital.models.Claim;

@Controller
public class DashboardController {

	DashboardRepositoryInterface dashboard;
	@Autowired
	public DashboardController(DashboardRepositoryInterface dashboard) {
		this.dashboard = dashboard;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getDashBoard() {
		// returns the dashboard page
		return "index";
	}

	@RequestMapping(value = "/applicants", method = RequestMethod.GET)
	public String getAllClaims(Model model) {
		// this gives the information about the Applicants list
		List<Claim> claims = dashboard.getAllApplicants();
		model.addAttribute("claims", claims);
		
		
		// this gives the information about the active applicants in the fin year
		List<Claim> activeApplicants = dashboard.getActiveApplicants();
		model.addAttribute("activeApplicants", activeApplicants);

		return "applicants";

	}

	@RequestMapping(value = "/rejected", method = RequestMethod.GET)
	public String getAllRejectedLoans(Model model) {
		// this shows all rejected bills.
		List<ClaimBills> claimbills = dashboard.getRejectedLoans();
		model.addAttribute("rejectedbills", claimbills);

		return "rejected";
	}

	@RequestMapping(value = "/claims", method = RequestMethod.GET)
	//this method gives the info. about the amount claimed and to be claimed by the hospital
	public String getClaimedValue(Model model) {
		
		
		List<Claim> amtRecived = dashboard.getClaimedAmount();
		// shows the complete claimed amount by the hospital
		model.addAttribute("claims", amtRecived);

		
		List<Claim> totalAmt = dashboard.getTotalAmount();
		//shows the amount claimed and to be claimed for the financial year(status="paid" or"pending")
		model.addAttribute("total_amount", totalAmt);

		return "claimvalue";
	}

}
