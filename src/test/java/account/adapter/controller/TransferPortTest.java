package account.adapter.controller;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import account.hexagonal.controller.TransferPort;
import account.hexagonal.entities.Account;
import account.hexagonal.entities.exceptions.BusinessException;
import account.hexagonal.entities.exceptions.ExceptionHandler;

@ContextConfiguration(classes = TransferPortImplTest.class) // para pegar as configurações da classe TransferPortImplTest para injetar dependências
@ExtendWith(SpringExtension.class) // integra o JUnit e SpringBoot
public class TransferPortTest {
	
	@Inject // injetar dependências para criar os objetos
	TransferPort transferPort;
	
	@Test
	void nullAccountId_ShouldThrowsExceptionHandler_WhenMethodGetAccountByIdIsCalled() {
		// Arrange
		Integer nullAccountId = null;
		
		// Assert
		ExceptionHandler message = Assertions.assertThrows(ExceptionHandler.class, () -> transferPort.getAccountById(nullAccountId), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Informe o ID da conta", message.getMessage());
	}
	
	@Test
	void unexistsAccountId_ShouldThrowsExceptionHandler_WhenMethodGetAccountByIdIsCalled() {
		// Arrange
		BigDecimal balance1 = new BigDecimal(100);
		BigDecimal balance2 = new BigDecimal(500);
		
		Integer originAccountId = 1;
		Integer destinityAccountId = 2;
		Integer unexistsAccountId = 30;
		Integer nullAccountId = null;
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> transferPort.getAccountById(unexistsAccountId), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals(unexistsAccountId + " eh inexistente", message.getMessage());
	}
	
	@Test
	void getAccountById_ShouldReturnAccount() throws ExceptionHandler, BusinessException {
		// Arrange
		BigDecimal balance1 = new BigDecimal(100);
		BigDecimal balance2 = new BigDecimal(500);
		
		Integer originAccountId = 1;
		Account originAccount = new Account(1, new BigDecimal(100), "Igor");
		
		Integer destinityAccountId = 2;
		Account destinityAccount = new Account(2, new BigDecimal(500), "Roberto");
		
		Integer unexistsAccountId = 30;
		Integer nullAccountId = null;
		
		// Act
		Account account = transferPort.getAccountById(originAccountId);
		
		// Assert
		Assertions.assertEquals(originAccount, account);
	}
}
