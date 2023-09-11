package sistema.hexagonal.repositories.adapter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Named;

import sistema.hexagonal.domain.Account;
import sistema.hexagonal.domain.exceptions.ExceptionHandler;
import sistema.hexagonal.repositories.AccountRepository;

@Named // Injeção de dependência (Bean de configuração) caso não coloque como static o método. Daí @Named vai instanciar automaticamente
public class AccountRepositoryImpl implements AccountRepository {
	
	Map<Integer, Account> accountDatabaseMock = new HashMap<Integer, Account>(); // Banco de dados mockado num Map para o teste
	
	public AccountRepositoryImpl() {
		this.accountDatabaseMock.put(1, new Account(1, new BigDecimal(100), "Igor"));
		this.accountDatabaseMock.put(2, new Account(2, new BigDecimal(500), "Roberto"));
	}
	
	@Override
	public Account getAccountById(Integer accountId) throws ExceptionHandler {
		if (Objects.isNull(accountId)) { throw new ExceptionHandler("Informe o ID da conta"); }
		return accountDatabaseMock.get(accountId);
	}
	
	@Override
	public void updateAccount(Account account) throws ExceptionHandler {
		if (Objects.isNull(account)) { throw new ExceptionHandler("Informe a conta"); }
		Account varAccountDatabase = accountDatabaseMock.get(account.getAccountId());
		
		if (Objects.nonNull(varAccountDatabase)) {
			this.accountDatabaseMock.put(varAccountDatabase.getAccountId(), account);
		} else {
			throw new ExceptionHandler("Conta nao encontrada");
		}
	}
	
}
