package com.techelevator.tenmo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransferDAO;

@RestController
@PreAuthorize("isAuthorized()")
@RequestMapping("/transfer")
public class TransferController {
	
	private TransferDAO transferDao;
	
	public TransferController(TransferDAO transferDao) {
		this.transferDao = transferDao;
	}

}
