package sistema.hexagonal.repositories;

import sistema.hexagonal.domain.Account;
import sistema.hexagonal.domain.exceptions.ExceptionHandler;

public interface AccountRepository {

	public Account getAccountById(Integer accountId) throws ExceptionHandler;
	
	public void updateAccount(Account account) throws ExceptionHandler;
}
