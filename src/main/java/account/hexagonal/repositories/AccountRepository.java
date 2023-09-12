package account.hexagonal.repositories;

import account.hexagonal.entities.Account;
import account.hexagonal.entities.exceptions.ExceptionHandler;

public interface AccountRepository {

	public Account getAccountById(Integer accountId) throws ExceptionHandler;
	
	public void updateAccount(Account account) throws ExceptionHandler;
}
