package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
	private String transferType;
	private String transferStatus;
	private long accountFromId;
	private long accountToId;
	private BigDecimal amount;

	public Transfer(String transferType, String transferStatus, long accountFromId, long accountToId, 
			BigDecimal amount, long transferId) {

		this.transferType = transferType;
		this.transferStatus = transferStatus;
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.amount = amount;
		this.transferId = transferId;
	}
	public Transfer(String transferType, String transferStatus, long accountFromId, long accountToId,
			BigDecimal amount) {

		this.transferType = transferType;
		this.transferStatus = transferStatus;
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.amount = amount;
		
	}
	public Transfer() {
		
	}
	

	private long transferId;

	public long getTransferId() {
		return transferId;
	}

	public void setTransferId(long transferId) {
		this.transferId = transferId;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public long getAccountFromId() {
		return accountFromId;
	}

	public void setAccountFromId(long accountFromId) {
		this.accountFromId = accountFromId;
	}

	public long getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(long accountToId) {
		this.accountToId = accountToId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
