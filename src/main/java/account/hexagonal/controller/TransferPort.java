package account.hexagonal.controller;

import java.math.BigDecimal;

import account.hexagonal.entities.Account;
import account.hexagonal.entities.exceptions.BusinessException;
import account.hexagonal.entities.exceptions.ExceptionHandler;

public interface TransferPort {

	public Account getAccountById(Integer accountId) throws ExceptionHandler;
	
	public void transfer(BigDecimal amount, Account debit, Account credit) throws ExceptionHandler, BusinessException;
}
