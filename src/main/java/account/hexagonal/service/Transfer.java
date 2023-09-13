package account.hexagonal.service;

import java.math.BigDecimal;
import java.util.Objects;

import javax.inject.Named;

import account.hexagonal.entities.Account;
import account.hexagonal.entities.exceptions.BusinessException;
import account.hexagonal.entities.exceptions.ExceptionHandler;

@Named // Injeção de dependência caso não coloque como static o método. Daí @Named vai instanciar automaticamente assim como faz o @Autowired. @Named é do Java EE e @Autowired é do SpringBoot (não misture frameworks, ou use uma ou use outra annotation)
public class Transfer {

	public static void initiateTransfer(BigDecimal amount, Account debit, Account credit) throws ExceptionHandler, BusinessException {
		if (Objects.isNull(amount)|| Objects.isNull(debit) || Objects.isNull(credit)) {
			throw new ExceptionHandler("Preencha todos os campos obrigatorios");
		}
		debit.debit(amount);
		credit.credit(amount);
	}
}
