package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
	private long accountId;
	private long userId;
	private BigDecimal accountBalance;

	public Account() {
		this.accountBalance = new BigDecimal("0.00");
	}
	
	public Account(long accountId, BigDecimal currentBalance) {
		this.accountId = accountId;
		this.accountBalance = currentBalance;
	}
	
	public Account(long accountId, long userId, BigDecimal currentBalance) {
		this.accountId = accountId;
		this.userId = userId;
		this.accountBalance = currentBalance;
	}
	
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		Account account = (Account)o;
		return (this.getUserId() == account.getUserId() && this.getAccountId() == account.getAccountId()
				&& this.getAccountBalance().compareTo(account.getAccountBalance()) == 0);
	}

	// Check sufficient balance from payer account
	public boolean hasEnoughMoney(BigDecimal amount) {
		return (this.getAccountBalance().compareTo(amount) >= 0);
	}

}
