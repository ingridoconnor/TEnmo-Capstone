package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
@Component
public class TransferSqlDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;
	

	public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
	}
	@Override
	public List<Transfer> getAllTransfers(){
		List<Transfer> transfers = new ArrayList<>();
		String sql = "SELECT * FROM transfers";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Transfer transfer = addRowToTransfer(results);
            transfers.add(transfer);
        }

        return transfers;
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
	
	private Transfer mapRowToTransfer(SqlRowSet results) {
		Transfer transfer = new Transfer();
		transfer.setAccountFromId(results.getLong("account_from"));
		transfer.setAccountToId(results.getLong("account_to"));
		transfer.set
	}



	

}
