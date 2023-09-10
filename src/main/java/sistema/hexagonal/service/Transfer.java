package sistema.hexagonal.service;

import java.math.BigDecimal;
import java.util.Objects;

import sistema.hexagonal.domain.Account;
import sistema.hexagonal.domain.exceptions.BusinessException;
import sistema.hexagonal.domain.exceptions.ExceptionHandler;

public class Transfer {

	public void initiateTransfer(BigDecimal amount, Account debit, Account credit) throws ExceptionHandler, BusinessException {
		if (Objects.isNull(amount)|| Objects.isNull(debit) || Objects.isNull(credit)) {
			throw new ExceptionHandler("Preencha todos os campos obrigatorios.");
		}
		debit.debit(amount);
		credit.credit(amount);
	}
}
