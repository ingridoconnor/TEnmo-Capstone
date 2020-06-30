package com.techelevator.tenmo.controller;

import java.math.BigDecimal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/account")
public class AccountController {

	private AccountDAO accountDao;
	
	public AccountController(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}
	@RequestMapping(path = "/{id}/balance", method = RequestMethod.GET)
	public BigDecimal getBalance(@PathVariable long id) {
		return accountDao.viewCurrentBalance(id);
	}
}
