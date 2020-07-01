package com.techelevator.tenmo.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("account")
public class AccountController {

	private AccountDAO accountDao;
	private UserDAO userDao;
	public AccountController(AccountDAO accountDao, UserDAO userDao) {
		this.accountDao = accountDao;
		this.userDao = userDao;
	}
//	@RequestMapping(path = "/{id}/balance", method = RequestMethod.GET)
//	public BigDecimal getBalance(@PathVariable long id) {
//		return accountDao.viewCurrentBalance(id);
//	}
	
	// Maybe client should send over User?
	@RequestMapping(path = "/balance", method = RequestMethod.GET)
	public Account getBalance(Principal principal) {
		String userName = principal.getName();
		long userId = userDao.findIdByUsername(userName);
		Account account = accountDao.findAccountByUserId(userId);
		return account;
	}
	
	
}
