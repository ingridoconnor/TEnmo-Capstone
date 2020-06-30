package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.tenmo.model.Account;

public class TransferSqlDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;

	public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void request(long requestSenderId, long requestReceiverId, BigDecimal amount) {
		// TODO Optional

	}

	@Override
	public void send(long senderId, long receiverId, BigDecimal amount) {
		// Take money from sender
		// Put money into receiver
		// Stick results into transfer
	}

	// Updates accounts and takes out money
	private boolean deductFromAccount(Account payAccount, BigDecimal amount) {
		if (payAccount.hasEnoughMoney(amount)) {
			// TODO Do we need to program for users with multiple accounts?
			String sqlDeductMoney = "UPDATE accounts SET balance = ? WHERE user_id = ?";
			return (jdbcTemplate.update(sqlDeductMoney, payAccount.getAccountBalance().subtract(amount),
					payAccount.getUserId()) == 1);
		}
		return false;
	}

	// Updates accounts and puts in money
	private boolean addToAccount(Account receiveAccount, BigDecimal amount) {
		String sqlAddMoney = "UPDATE accounts SET balance = ? WHERE user_id = ?";
		return (jdbcTemplate.update(sqlAddMoney, receiveAccount.getAccountBalance().add(amount),
				receiveAccount.getUserId()) == 1);
	}
	// Creates new row in transfers table

}
