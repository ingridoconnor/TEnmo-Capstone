package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
	private String transferType;
	private String transferStatus;
	private long accountFromId;
	private String userFrom;
	private long accountToId;
	private String userTo;
	private BigDecimal amount;

	public Transfer(String transferType, String transferStatus, long accountFromId, String userFrom, long accountToId,
			String userTo, BigDecimal amount, long transferId) {

		this.transferType = transferType;
		this.transferStatus = transferStatus;
		this.accountFromId = accountFromId;
		this.userFrom = userFrom;
		this.accountToId = accountToId;
		this.userTo = userTo;
		this.amount = amount;
		this.transferId = transferId;
	}
	public Transfer(String transferType, String transferStatus, long accountFromId, String userFrom, long accountToId,
			String userTo, BigDecimal amount) {

		this.transferType = transferType;
		this.transferStatus = transferStatus;
		this.accountFromId = accountFromId;
		this.userFrom = userFrom;
		this.accountToId = accountToId;
		this.userTo = userTo;
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

	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	public long getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(long accountToId) {
		this.accountToId = accountToId;
	}

	public String getUserTo() {
		return userTo;
	}

	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
