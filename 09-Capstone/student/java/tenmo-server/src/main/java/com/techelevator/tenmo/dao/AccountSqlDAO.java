package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class AccountSqlDAO implements AccountDAO {

	@Override
	public BigDecimal viewCurrentBalance() {
		// TODO Auto-generated method stub
		return null;
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

}
