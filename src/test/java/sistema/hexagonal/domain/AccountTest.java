package sistema.hexagonal.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sistema.hexagonal.domain.exceptions.BusinessException;

public class AccountTest {
	/* Tests:
		1) Happy path: credit amount
		2) Exception: negative credit
		3) Exception: null credit
		4) Exception: zero amount of credit
		
		5) Happy path: debit amount
		6) Exception: negative debit
		7) Exception: null debit
		8) Exception: zero amount of debit
		9) Exception: insuficient balance to debit
	*/

	@Test
	void positiveCredit_ShouldCreditBalance() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = new BigDecimal(1);
		Account account = new Account(1, balance, "Igor");
		
		// Act
		account.credit(amount);
		
		// Assert
		Assertions.assertEquals(new BigDecimal(101), account.getBalance());
	}
	
	@Test
	void negativeCredit_ShouldThrowsBusinessException_whenMethodCreditIsCalled() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = new BigDecimal(-1);
		Account account = new Account(1, balance, "Igor");
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> account.credit(amount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Informe um valor maior que zero a ser creditado", message.getMessage());
	}

	@Test
	void nullCredit_ShouldThrowsBusinessException_whenMethodCreditIsCalled() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = null;
		Account account = new Account(1, balance, "Igor");
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> account.credit(amount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Informe o valor a ser creditado", message.getMessage());
	}
	
	@Test
	void zeroAmountOfCredit_ShouldThrowsBusinessException_whenMethodCreditIsCalled() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = BigDecimal.ZERO;
		Account account = new Account(1, balance, "Igor");
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> account.credit(amount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Informe um valor maior que zero a ser creditado", message.getMessage());
	}
	
	
	@Test
	void positiveDebit_ShouldDebitBalance() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = new BigDecimal(100);
		Account account = new Account(1, balance, "Igor");
		
		// Act
		account.debit(amount);
		
		// Assert
		Assertions.assertEquals(new BigDecimal(0), account.getBalance());
	}
	
	@Test
	void negativeDebit_ShouldThrowsBusinessException_whenMethodDebitIsCalled() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = new BigDecimal(-1);
		Account account = new Account(1, balance, "Igor");
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> account.debit(amount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Informe um valor maior que zero a ser debitado", message.getMessage());
	}

	@Test
	void nullDebit_ShouldThrowsBusinessException_whenMethodDebitIsCalled() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = null;
		Account account = new Account(1, balance, "Igor");
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> account.debit(amount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Informe o valor a ser debitado", message.getMessage());
	}
	
	@Test
	void zeroAmountOfDebit_ShouldThrowsBusinessException_whenMethodDebitIsCalled() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = BigDecimal.ZERO;
		Account account = new Account(1, balance, "Igor");
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> account.debit(amount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Informe um valor maior que zero a ser debitado", message.getMessage());
	}
	
	@Test
	void debitAmountGreatherThanBalance_ShouldThrowsBusinessException_whenMethodDebitIsCalled() {
		// Arrange
		final BigDecimal balance = new BigDecimal(100);
		final BigDecimal amount = new BigDecimal(101);
		Account account = new Account(1, balance, "Igor");
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> account.debit(amount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Saldo insuficiente", message.getMessage());
	}
}
