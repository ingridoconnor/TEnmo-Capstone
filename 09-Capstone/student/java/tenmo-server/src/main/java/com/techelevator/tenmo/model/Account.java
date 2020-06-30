package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
	private long accountId;
	private long userId;
	private BigDecimal accountBalance;

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

	public boolean equals(Account account) {
		return (this.getUserId() == account.getUserId() && this.getAccountId() == account.getAccountId()
				&& this.getAccountBalance().compareTo(account.getAccountBalance()) == 0);
	}

	// Check sufficient balance from payer account
	public boolean hasEnoughMoney(BigDecimal amount) {
		return (this.getAccountBalance().compareTo(amount) >= 0);
	}

}
