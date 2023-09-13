package account.adapter.controller;

import java.math.BigDecimal;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import account.hexagonal.controller.TransferPort;
import account.hexagonal.entities.Account;
import account.hexagonal.entities.exceptions.BusinessException;
import account.hexagonal.entities.exceptions.ExceptionHandler;
import account.hexagonal.repositories.AccountRepository;
import account.hexagonal.service.Transfer;

@Named // Injeção de dependência (Bean de configuração) caso não coloque como static o método. Daí @Named vai instanciar automaticamente
public class TransferPortImpl implements TransferPort {

	private AccountRepository accountRepository;
	
	@Inject // Injeta dependência primeiro aqui
	public TransferPortImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account getAccountById(Integer accountId) throws ExceptionHandler, BusinessException {
		if (Objects.isNull(accountId)) { throw new ExceptionHandler("Informe o ID da conta"); }
		
		Account accountDatabase = accountRepository.getAccountById(accountId);
		if (Objects.isNull(accountDatabase)) {
			ExceptionHandler.unexists(accountId.toString());
		}
		
		return accountDatabase;
	}

	@Override
	@Transactional // Transação (Begin até o Commit) para poder fazer rollback (voltar ao estado anterior) se necessário
	public void transfer(BigDecimal amount, Account debit, Account credit) throws ExceptionHandler, BusinessException {
		Account originAccount = getAccountById(debit.getAccountId());
		Account destinationAccount = getAccountById(credit.getAccountId());
		
		if (originAccount.getAccountId().equals(destinationAccount.getAccountId())) {
			ExceptionHandler.sameAccount();
		}
		
		Transfer.initiateTransfer(amount, debit, credit); // atualizar java entity
		originAccount.debit(amount); // atualizar banco de dados
		destinationAccount.credit(amount);
	}

}
