package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("account")
public class AccountController {

	private AccountDAO accountDao;
	private UserDAO userDao;
	private TransferDAO transferDao;
	private static final String STATUS_APPROVED = "Approved";
	private static final String STATUS_PENDING = "Pending";
	private static final String STATUS_REJECTED = "Rejected";

	public AccountController(AccountDAO accountDao, UserDAO userDao, TransferDAO transferDao) {
		this.accountDao = accountDao;
		this.userDao = userDao;
		this.transferDao = transferDao;
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

	@RequestMapping(path = "/sendbucks", method = RequestMethod.PUT)
	public void sendBucks(@RequestBody Transfer transfer) {
		// Remember that Transfer objects from the client side only carry the USER IDs, not the accounts
		Account takeFrom = accountDao.findAccountByUserId(transfer.getAccountFromId());
		Account giveTo = accountDao.findAccountByUserId(transfer.getAccountToId());
		transfer.setAccountFromId(takeFrom.getAccountId());
		transfer.setAccountToId(giveTo.getAccountId());
		if (takeFrom.hasEnoughMoney(transfer.getAmount())) {
			accountDao.deductBalance(takeFrom, transfer.getAmount());
			accountDao.creditBalance(giveTo, transfer.getAmount());
			transferDao.addRowToTransfer(transfer);
		}
	}

	@RequestMapping(path = "/requestbucks", method = RequestMethod.PUT)
	public void requestBucks(@RequestBody Transfer transfer) {
		Account requestFrom = accountDao.findAccountByUserId(transfer.getAccountToId());
		transfer.setAccountFromId((accountDao.findAccountByUserId(transfer.getAccountFromId())).getAccountId());
		transfer.setAccountToId(requestFrom.getAccountId());
		if (requestFrom.hasEnoughMoney(transfer.getAmount())) {
			transferDao.addRowToTransfer(transfer);
		}
	}
	
	@RequestMapping(path = "/reviewpending", method = RequestMethod.PUT)
	public void approvePendingTransfers(@RequestBody Transfer transfer) {
		Account requestFrom = accountDao.findAccountByUserId(transfer.getAccountToId());
		Account me = accountDao.findAccountByUserId(transfer.getAccountFromId());
		String status = transfer.getTransferStatus();
		if (status.equalsIgnoreCase(STATUS_APPROVED)) {
			accountDao.deductBalance(requestFrom, transfer.getAmount());
			accountDao.creditBalance(me, transfer.getAmount());
			transferDao.updateTransfer(transfer);
		} else if (status.equalsIgnoreCase(STATUS_REJECTED)) {
			transferDao.updateTransfer(transfer);
		}
	}

	@RequestMapping(path = "/transfers/history", method = RequestMethod.GET)
	public Transfer[] getTransferHistory(Principal principal) {
		User user = userDao.findByUsername(principal.getName());
		Account account = accountDao.findAccountByUserId(user.getId());
		List<Transfer> transferList = transferDao.getAllTransfers(account);
		Transfer[] transfers = new Transfer[transferList.size()];
		transfers = transferList.toArray(transfers);
		return transfers;
	}

	@RequestMapping(path = "/finduser", method = RequestMethod.GET)
	public User[] getAllUsers() {
		List<User> userList = userDao.findAll();
		User[] users = new User[userList.size()];
		users = userList.toArray(users);
		return users;
	}
}
