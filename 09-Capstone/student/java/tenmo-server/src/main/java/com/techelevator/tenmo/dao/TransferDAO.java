package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	


	boolean addRowToTransfer(Transfer transfer);

	List<Transfer> getAllTransfers();

}
