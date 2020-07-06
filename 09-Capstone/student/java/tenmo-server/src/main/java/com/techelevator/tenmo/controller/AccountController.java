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
		Account me = convertClientInitiatorToServerInitiator(transfer);
		Account personOwed = convertClientReactorToServerReactor(transfer);
		if (me.hasEnoughMoney(transfer.getAmount())) {
			accountDao.deductBalance(me, transfer.getAmount());
			accountDao.creditBalance(personOwed, transfer.getAmount());
			transferDao.addRowToTransfer(transfer);
		}
	}

	@RequestMapping(path = "/requestbucks", method = RequestMethod.PUT)
	public void requestBucks(@RequestBody Transfer transfer) {
		Account payMeBack = convertClientReactorToServerReactor(transfer);
		Account me = convertClientInitiatorToServerInitiator(transfer);
		if (payMeBack.hasEnoughMoney(transfer.getAmount())) {
			transferDao.addRowToTransfer(transfer);
		}
	}

	@RequestMapping(path = "/reviewpending", method = RequestMethod.PUT)
	public void approvePendingTransfers(@RequestBody Transfer transfer) {
		Account requestFrom = convertClientReactorToServerReactor(transfer);
		Account me = convertClientInitiatorToServerInitiator(transfer);
		
		// TODO: Transfer status is set on the client side
		String status = transfer.getTransferStatus();
		if (status.equalsIgnoreCase(STATUS_APPROVED)) {
			accountDao.deductBalance(requestFrom, transfer.getAmount());
			accountDao.creditBalance(me, transfer.getAmount());
			transferDao.updateTransfer(transfer);
		} else if (status.equalsIgnoreCase(STATUS_REJECTED)) {
			transferDao.updateTransfer(transfer);
		}
	}

	private Account convertClientInitiatorToServerInitiator(Transfer transfer) {
		// From the client side, transferFromId and transferToId are both USER IDs,
		// NOT account IDs. Must find accounts by user ID 1st.
		// Then change the Transfer object before writing to database.

		// Account of User who initiatied a send/request
		Account initiatorAccount = accountDao.findAccountByUserId(transfer.getAccountFromId());
		transfer.setAccountFromId(initiatorAccount.getAccountId());
		return initiatorAccount;
	}

	private Account convertClientReactorToServerReactor(Transfer transfer) {
		// From the client side, transferFromId and transferToId are both USER IDs,
		// NOT account IDs. Must find accounts by user ID 1st.
		// Then change the Transfer object before writing to database.
		
		// Account of User who gets a send/request
		Account reactorAccount = accountDao.findAccountByUserId(transfer.getAccountToId());
		transfer.setAccountToId(reactorAccount.getAccountId());
		return reactorAccount;
	}

	@RequestMapping(path = "/transfers/history", method = RequestMethod.GET)
	public Transfer[] getTransferHistory(Principal principal) {
		User user = userDao.findByUsername(principal.getName());
		Account account = accountDao.findAccountByUserId(user.getId());
		List<Transfer> transferList = transferDao.getAllTransfers(account);
		for (Transfer serverTransfer : transferList) {
			convertServerTransferToClient(serverTransfer);
		}
		Transfer[] transfers = new Transfer[transferList.size()];
		transfers = transferList.toArray(transfers);
		return transfers;
	}
	
	/**
	 * On the server side, IDs stored in a Transfer object are Account IDs.
	 * Before passing the Transfer back to the client side, those must be
	 * translated back into User IDs.
	 */
	private void convertServerTransferToClient(Transfer transfer) {
		User userFrom = userDao.findUserByAccountId(transfer.getAccountFromId());
		User userTo = userDao.findUserByAccountId(transfer.getAccountToId());
		transfer.setAccountFromId(userFrom.getId());
		transfer.setAccountToId(userTo.getId());
	}

	@RequestMapping(path = "/finduser", method = RequestMethod.GET)
	public User[] getAllUsers() {
		List<User> userList = userDao.findAll();
		User[] users = new User[userList.size()];
		users = userList.toArray(users);
		return users;
	}
}
