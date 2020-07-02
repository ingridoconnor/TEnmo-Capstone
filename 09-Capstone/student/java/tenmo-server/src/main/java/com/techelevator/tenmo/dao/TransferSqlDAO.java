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

	// Should this be part of AccountDAO instead? TransferDAO should only be talking to the transfers table
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

	//Refactor: Separate method for retrieving transfer_type_id
	private int getTransferTypeId(Transfer transfer) {
		String sqlGetTransferTypeId = "SELECT transfer_type_id FROM transfer_types WHERE transfer_type_desc = ?";
		SqlRowSet transferTypeResult = jdbcTemplate.queryForRowSet(sqlGetTransferTypeId, transfer.getTransferType());
		int transferTypeId = 0;
		if (transferTypeResult.next()) {
			transferTypeId = transferTypeResult.getInt("transfer_type_id");
			
		}
		return transferTypeId;
	}
	
	// Refactor: create separate method for retrieving transfer_status_id
	private int getTransferStatusId(Transfer transfer) {
		String sqlGetTransferStatusId = "SELECT transfer_Status_id FROM transfer_statuses WHERE transfer_status_desc = ?";
		SqlRowSet transferStatusResult = jdbcTemplate.queryForRowSet(sqlGetTransferStatusId,
				transfer.getTransferStatus());
		int transferStatusId = 0;
		if (transferStatusResult.next()) {
			transferStatusId = transferStatusResult.getInt("transfer_status_id");
			
		}
		return transferStatusId;
	}
	
	// Creates new row in transfers table
	@Override
	public boolean addRowToTransfer(Transfer transfer) {
		// We successfully retrieve both the status and type IDs
		int statusId = getTransferStatusId(transfer);
		int typeId = getTransferTypeId(transfer);
		if (statusId > 0 && typeId > 0) {
			String sqlAddRowToTransfer = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) "
					+ "VALUES (?, ?, ?, ?, ?) RETURNING transfer_id";
			SqlRowSet transferIdResult = jdbcTemplate.queryForRowSet(sqlAddRowToTransfer, typeId,
					statusId, transfer.getAccountFromId(), transfer.getAccountToId(), transfer.getAmount());
			if (transferIdResult.next()) {
				transfer.setTransferId(transferIdResult.getLong("transfer_id"));
				return true;
			}
		}
		return false;

	}

}
