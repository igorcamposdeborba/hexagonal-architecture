package sistema.hexagonal.controller;

import java.math.BigDecimal;

import sistema.hexagonal.domain.Account;
import sistema.hexagonal.domain.exceptions.BusinessException;
import sistema.hexagonal.domain.exceptions.ExceptionHandler;

public interface TransferPort {

	public Account getAccountById(Integer accountId) throws ExceptionHandler;
	
	public void transfer(BigDecimal amount, Account debit, Account credit) throws ExceptionHandler, BusinessException;
}
