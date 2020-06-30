package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDAO {
	
	BigDecimal viewCurrentBalance();
	
	BigDecimal creditBalance(BigDecimal amountToAdd);
	
	BigDecimal deductBalance(BigDecimal amountToSubtract);
	
	
	

}
