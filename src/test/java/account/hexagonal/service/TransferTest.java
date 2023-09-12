package account.hexagonal.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import account.hexagonal.entities.Account;
import account.hexagonal.entities.exceptions.BusinessException;
import account.hexagonal.entities.exceptions.ExceptionHandler;
import account.hexagonal.service.Transfer;

public class TransferTest {

	/* Tests:
		1) Happy path: debit of account A
		2) Happy path: credit to account B
		3) Exception: null account A of origin
		4) Exception: null account B of destination
	*/
	
	@Test
	void debitAccountOrigin_ShouldDebitBalance() throws ExceptionHandler, BusinessException {
		// Arrange
		BigDecimal balance1 = new BigDecimal(100);
		BigDecimal balance2 = new BigDecimal(500);
		
		Account originAccount = new Account(1, balance1, "Igor");
		Account destinityAccount = new Account(2, balance2, "Roberto");
		BigDecimal valueTransfer = new BigDecimal(100);
		
		// Act
		Transfer.initiateTransfer(valueTransfer, originAccount, destinityAccount);
		
		// Assert
		Assertions.assertEquals(new BigDecimal(0), originAccount.getBalance());
	}
	
	@Test
	void creditAccountDestinity_ShouldCreditBalance() throws ExceptionHandler, BusinessException {
		// Arrange
		BigDecimal balance1 = new BigDecimal(100);
		BigDecimal balance2 = new BigDecimal(500);
		
		Account originAccount = new Account(1, balance1, "Igor");
		Account destinityAccount = new Account(2, balance2, "Roberto");
		BigDecimal valueTransfer = new BigDecimal(100);
		
		// Act
		Transfer.initiateTransfer(valueTransfer, originAccount, destinityAccount);
		
		// Assert
		Assertions.assertEquals(new BigDecimal(600), destinityAccount.getBalance());
	}
	
	@Test
	void nullAccountOrigin_ShouldThrowsExceptionHandler_WhenCallMethodInitiateTransfer() {
		// Arrange
		BigDecimal balance2 = new BigDecimal(500);
		
		Account originAccount = null;
		Account destinityAccount = new Account(2, balance2, "Roberto");
		BigDecimal valueTransfer = new BigDecimal(100);
		
		// Assert
		ExceptionHandler message = Assertions.assertThrows(ExceptionHandler.class, () -> Transfer.initiateTransfer(valueTransfer, originAccount, destinityAccount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Preencha todos os campos obrigatorios", message.getMessage());
	}
	
	@Test
	void nullAccountDestinity_ShouldThrowsExceptionHandler_WhenCallMethodInitiateTransfer() {
		// Arrange
		BigDecimal balance1 = new BigDecimal(100);
		
		Account originAccount = new Account(1, balance1, "Igor");
		Account destinityAccount = null;
		BigDecimal valueTransfer = new BigDecimal(100);
		
		// Assert
		ExceptionHandler message = Assertions.assertThrows(ExceptionHandler.class, () -> Transfer.initiateTransfer(valueTransfer, originAccount, destinityAccount), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Preencha todos os campos obrigatorios", message.getMessage());
	}
}
