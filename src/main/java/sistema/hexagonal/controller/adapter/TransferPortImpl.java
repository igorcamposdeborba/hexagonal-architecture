package sistema.hexagonal.controller.adapter;

import java.math.BigDecimal;
import java.util.Objects;

import sistema.hexagonal.controller.TransferPort;
import sistema.hexagonal.domain.Account;
import sistema.hexagonal.domain.exceptions.BusinessException;
import sistema.hexagonal.domain.exceptions.ExceptionHandler;
import sistema.hexagonal.repositories.AccountRepository;
import sistema.hexagonal.service.Transfer;

public class TransferPortImpl implements TransferPort {

	private AccountRepository accountRepository;
	
	@Override
	public Account getAccountById(Integer accountId) throws ExceptionHandler {
		if (Objects.isNull(accountId)) { throw new ExceptionHandler("Informe o ID da conta"); }
		return accountRepository.getAccountById(accountId);
	}

	@Override
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
