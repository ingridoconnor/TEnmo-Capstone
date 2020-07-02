package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	/**
	 * This is a bonus method. Default status is "Pending"
	 * 
	 * @param requestSenderId
	 * @param requestReceiverId
	 * @param amount
	 */
	void request(long requestSenderId, long requestReceiverId, BigDecimal amount);
	
	/**
	 * This is a required method. Default status is "Approved"
	 * 
	 * @param senderId
	 * @param receiverId
	 * @param amount
	 */
	void send(long senderId, long receiverId, BigDecimal amount);

	boolean addRowToTransfer(Transfer transfer);

}
