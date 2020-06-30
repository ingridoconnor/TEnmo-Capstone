package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {
	
	BigDecimal viewCurrentBalance(long userId);
	
	BigDecimal creditBalance(BigDecimal amountToAdd);
	
	BigDecimal deductBalance(BigDecimal amountToSubtract);
	
	
	

}
