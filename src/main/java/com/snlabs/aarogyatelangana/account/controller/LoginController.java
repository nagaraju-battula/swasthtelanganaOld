/**
 * 
 */
package com.snlabs.aarogyatelangana.account.controller;

/**
 * @author nbattula
 *
 */
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.snlabs.aarogyatelangana.account.beans.LoginUser;
import com.snlabs.aarogyatelangana.account.beans.NewUser;
import com.snlabs.aarogyatelangana.account.beans.UserDetails;
import com.snlabs.aarogyatelangana.account.service.AccountService;
import com.snlabs.aarogyatelangana.account.utils.AccountUtils;

@Controller
public class LoginController {

	@Autowired
	public AccountService accountService;
	
	private AccountUtils accountUtils = new AccountUtils();
	
	@RequestMapping(value = {"loginsubmission.action"} ,method = RequestMethod.POST)
	public String loginsubmission(@RequestBody LoginUser loginUser, HttpSession session, ModelMap modelMap) {
		
		loginUser.setPassword(accountUtils.md5(loginUser.getPassword()));
		
		UserDetails userDetails = accountService.getAccountDetails(loginUser);
		
		if(userDetails != null){
			session.setAttribute("userDetails", userDetails);
			return "workdesk";
		}
		return "home";
	}
	
	
	@RequestMapping(value = {"createaccountsubmission.action"} ,method = RequestMethod.POST)
	public String createaccountsubmission(@RequestBody NewUser user, ModelMap model) {
		//Show patient entry form, Log the request.
		
		String hashedPassword = accountUtils.md5(user.getPassword());
		user.setPassword(hashedPassword);
		
		boolean result = accountService.createAccount(user);
		
		String view = null;
		view = result?"createaccountsubmissionsuccess":"createaccountsubmissionfail";
		
		return view;
	}
	
	@RequestMapping(value = {"updateaccount.action"} ,method = RequestMethod.POST)
	public String updateaccount(ModelMap model) {
		//Show patient entry form, Log the request.
		return "updateaccount";
	}

	@RequestMapping(value = {"updateaccountsubmission.action"} ,method = RequestMethod.POST)
	public String updateaccountsubmission(@RequestBody NewUser user, ModelMap model) {
		//Show patient entry form, Log the request.
		
		String hashedPassword = accountUtils.md5(user.getPassword());
		user.setPassword(hashedPassword);
		
		boolean result = accountService.updateAccount(user);
		
		String view = null;
		view = result?"updateaccountsubmissionsuccess":"updateaccountsubmissionfail";
		
		return view;
	}
	
	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
}