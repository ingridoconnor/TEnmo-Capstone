package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
	
	private long transferId;
	private int transferTypeId;
	private String transferType;
	private int transferStatusId;
	private String transferStatus;
	private long accountFromId;
	private String userFrom;
	private long accountToId;
	private String userTo;
	private BigDecimal amount;
	

}
