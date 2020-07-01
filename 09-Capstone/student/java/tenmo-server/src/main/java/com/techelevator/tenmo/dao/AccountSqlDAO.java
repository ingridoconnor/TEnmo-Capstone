package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.Account;


@Component
public class AccountSqlDAO implements AccountDAO {
	private JdbcTemplate jdbcTemplate;

	public AccountSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public BigDecimal viewCurrentBalance(long userId) {
		String sql = "SELECT balance FROM accounts WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		BigDecimal balance = null;
		if(results.next()) {
			balance = results.getBigDecimal("balance");
		}
		return balance;
	}

	@Override
	public BigDecimal creditBalance(BigDecimal amountToAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal deductBalance(BigDecimal amountToSubtract) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Account findAccountByUserId(long userId) {
		Account account = null;
		String sql = "SELECT * FROM accounts WHERE user_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
		if (result.next()) {
			account = mapRowToAccount(result);
		}
		return account;
	}

	private Account mapRowToAccount(SqlRowSet results) {
		Account account = new Account();
		account.setAccountBalance(results.getBigDecimal("balance"));
		account.setAccountId(results.getLong("account_id"));
		account.setUserId(results.getLong("user_id"));
		return account;
	}
}
