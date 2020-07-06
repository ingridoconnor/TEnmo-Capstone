package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

@RestController
@PreAuthorize("isAuthorized()")
@RequestMapping("transfer")
public class TransferController {
	
	private TransferDAO transferDao;
	private UserDAO userDao;
	
	public TransferController(TransferDAO transferDao, UserDAO userDao) {
		this.transferDao = transferDao;
		this.userDao = userDao;
	}
	
//	@RequestMapping(path = "/send", method = RequestMethod.GET)
//	public List<User> listAllUsers() {
//		return userDao.findAll();
//		
//	}
//	@RequestMapping(path = "/send/{id}", method = RequestMethod.POST)
//	public Transfer sendMoney(Principal principal) {
//		//get our own id to deduct from our balance
//		String userName = principal.getName();
//		long userId = userDao.findIdByUsername(userName);
//		//get recip id
////		Account account = accountDao.findAccountByUserId(userId);
//	}

}
