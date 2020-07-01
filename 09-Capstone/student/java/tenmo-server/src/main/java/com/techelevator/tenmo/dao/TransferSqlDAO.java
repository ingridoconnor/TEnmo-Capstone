package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
@Component
public class TransferSqlDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;
	private AccountDAO accountDao;

	public TransferSqlDAO(JdbcTemplate jdbcTemplate, AccountDAO accountDao) {
		this.jdbcTemplate = jdbcTemplate;
		this.accountDao = accountDao;
	}

	@Override
	public void request(long requestSenderId, long requestReceiverId, BigDecimal amount) {
		// TODO Optional

	}

	@Override
	public void send(long senderId, long receiverId, BigDecimal amount) {
		// Take money from sender
//		String sqlGetPayerAccount = "SELECT * FROM accounts WHERE user_id = ?";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetPayerAccount, senderId);
//		Account payer = new Account(); 
//		if (result.next()) {
//			payer = map
//		}
//		if (deductFromAccount())
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
	private boolean addedRowToTransfer(Transfer transfer) {

		String sqlGetTransferTypeId = "SELECT transfer_type_id FROM transfer_types WHERE transfer_type_desc = ?";
		SqlRowSet transferTypeResult = jdbcTemplate.queryForRowSet(sqlGetTransferTypeId, transfer.getTransferType());
		int transferTypeId = 0;
		if (transferTypeResult.next()) {
			transferTypeId = transferTypeResult.getInt("transfer_type_id");

		}
		String sqlGetTransferStatusId = "SELECT transfer_Status_id FROM transfer_statuses WHERE transfer_status_desc = ?";
		SqlRowSet transferStatusResult = jdbcTemplate.queryForRowSet(sqlGetTransferStatusId,
				transfer.getTransferStatus());
		int transferStatusId = 0;
		if (transferStatusResult.next()) {
			transferStatusId = transferStatusResult.getInt("transfer_status_id");

		}
		if (transferStatusId > 0 && transferTypeId > 0) {
			String sqlAddRowToTransfer = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) "
					+ "VALUES (?, ?, ?, ?, ?) RETURNING transfer_id";
			SqlRowSet transferIdResult = jdbcTemplate.queryForRowSet(sqlAddRowToTransfer, transferTypeId,
					transferStatusId, transfer.getAccountFromId(), transfer.getAccountToId(), transfer.getAmount());
			if (transferIdResult.next()) {
				transfer.setTransferId(transferIdResult.getLong("transfer_id"));
				return true;

			}
		}

		return false;

	}

}
