package account.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import account.hexagonal.controller.TransferPort;
import account.hexagonal.entities.Account;
import account.hexagonal.entities.exceptions.BusinessException;
import account.hexagonal.entities.exceptions.ExceptionHandler;

/* Tests:
   Method getAccountById:
	1) Happy path: getAccountById
	2) Exception: null account id
	3) Exception: unexists account in database
   
   Method transfer:
	1) Exception: same account A and B
	Não vou testar a transferência aqui no Controller porque já foi testado no Service. Se testar duas vezes chamando ambas as classes sem mock, vai debitar e creditar duas vezes (isso seria uma falha do programador).
*/

@ContextConfiguration(classes = TransferPortImplTest.class) // para pegar as configurações da classe TransferPortImplTest para injetar dependências
@ExtendWith(SpringExtension.class) // integra o JUnit e SpringBoot
public class TransferPortTest {
	
	@Inject // injetar dependências para criar os objetos
	TransferPort transferPort;
	
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
		Account account1 = transferPort.getAccountById(originAccountId);
		Account account2 = transferPort.getAccountById(destinityAccountId);
		
		// Assert
		Assertions.assertEquals(originAccount, account1);
		Assertions.assertEquals(destinityAccount, account2);
	}
	
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
		Integer unexistsAccountId = 30;
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> transferPort.getAccountById(unexistsAccountId), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals(unexistsAccountId + " eh inexistente", message.getMessage());
	}

	@Test
	void sameAccountAccountOriginAndDestiny_ShouldThrowsBusinessException_WhenIsCalledMethodTransfer() throws ExceptionHandler, BusinessException {
		// Arrange
		BigDecimal amount1 = new BigDecimal(100);
		BigDecimal amount2 = new BigDecimal(500);
		
		Integer originAccountId = 1;
		Account originAccount = new Account(1, new BigDecimal(100), "Igor");
		
		Integer destinityAccountId = 2;
		Account destinityAccount = new Account(2, new BigDecimal(500), "Roberto");
		
		Integer unexistsAccountId = 30;
		Integer nullAccountId = null;
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> transferPort.transfer(amount1, originAccount, originAccount), "Revise os parametros e a logica de processamento");
		assertEquals("Conta debito e credito devem ser diferentes", message.getMessage());
	}
}
