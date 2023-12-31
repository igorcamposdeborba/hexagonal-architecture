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
import account.hexagonal.repositories.AccountRepository;

/* Tests:
   Method getAccountById:
	1) Happy path: getAccountById
	2) Exception: null account id
	3) Exception: unexists account in database
   
   Method transfer:
	4) Happy path: transfer from account a to account B
	5) Exception: same account A and B
	Não vou testar a transferência aqui no Controller porque já foi testado no Service. Se testar duas vezes chamando ambas as classes sem mock, vai debitar e creditar duas vezes (isso seria uma falha do programador).
*/

@ContextConfiguration(classes = TransferPortImplTest.class) // para pegar as configurações da classe TransferPortImplTest para injetar dependências
@ExtendWith(SpringExtension.class) // integra o JUnit e SpringBoot
public class TransferPortTest {
	
	@Inject // injetar dependências para criar os objetos. Se comunica com o Controller
	TransferPort transferPort;
	
	@Inject
	AccountRepository accountRepository; // Se comunica com o repository (banco de dados) para resetar o balance para o inicial após cada teste
	
	@AfterEach
	void resetDatabase() throws ExceptionHandler, BusinessException {
		// Arrange
		Account originAccount = new Account(1, new BigDecimal(100), "Igor");
		Account destinityAccount = new Account(2, new BigDecimal(500), "Roberto");
		
		// Act
		accountRepository.updateAccount(originAccount);
		accountRepository.updateAccount(destinityAccount);
	}
	
	@Test
	void getAccountById_ShouldReturnAccount() throws ExceptionHandler, BusinessException {
		// Arrange
		Integer originAccountId = 1;
		Account originAccount = new Account(1, new BigDecimal(100), "Igor");
		
		Integer destinityAccountId = 2;
		Account destinityAccount = new Account(2, new BigDecimal(500), "Roberto");

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
	void transferMoneyBetweenAccountOriginAndDestiny_ShouldTransferMoney_WhenIsCalledMethodTransfer() throws ExceptionHandler, BusinessException {
		// Arrange
		BigDecimal amount1 = new BigDecimal(50);
		
		Integer originAccountId = 1;
		Account originAccount = new Account(1, new BigDecimal(100), "Igor");
		
		Integer destinityAccountId = 2;
		Account destinityAccount = new Account(2, new BigDecimal(500), "Roberto");
		
		transferPort.transfer(amount1, originAccount, destinityAccount);
		
		// Assert
		Assertions.assertEquals(new BigDecimal(50), transferPort.getAccountById(originAccountId).getBalance());
		Assertions.assertEquals(new BigDecimal(550), transferPort.getAccountById(destinityAccountId).getBalance());
	}
	
	@Test
	void sameAccountAccountOriginAndDestiny_ShouldThrowsBusinessException_WhenIsCalledMethodTransfer() throws ExceptionHandler, BusinessException {
		// Arrange
		BigDecimal amount1 = new BigDecimal(100);

		Account originAccount = new Account(1, new BigDecimal(100), "Igor");
		
		// Assert
		BusinessException message = Assertions.assertThrows(BusinessException.class, () -> transferPort.transfer(amount1, originAccount, originAccount), "Revise os parametros e a logica de processamento");
		assertEquals("Conta debito e credito devem ser diferentes", message.getMessage());
	}
	

}
