package account.adapter.controller;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import account.hexagonal.controller.TransferPort;
import account.hexagonal.entities.exceptions.ExceptionHandler;

@ContextConfiguration(classes = TransferPortImplTest.class) // para pegar as configurações da classe TransferPortImplTest para injetar dependências
@ExtendWith(SpringExtension.class) // integra o JUnit e SpringBoot
public class TransferPortTest {
	
	@Inject // injetar dependências para criar os objetos
	TransferPort transferPort;
	
	@Test
	void nullAccountId_ShouldThrowsExceptionHandler_WhenMethodGetAccountByIdIsCalled() {
		// Arrange
		BigDecimal balance1 = new BigDecimal(100);
		BigDecimal balance2 = new BigDecimal(500);
		
		Integer originAccountId = 1;
		Integer destinityAccountId = 2;
		Integer unexistsAccountId = 30;
		Integer nullAccountId = null;
		
		// Assert
		ExceptionHandler message = Assertions.assertThrows(ExceptionHandler.class, () -> transferPort.getAccountById(nullAccountId), "Revise os parametros e a logica de processamento");
		Assertions.assertEquals("Informe o ID da conta", message.getMessage());
	}
	
	@Test
	void getAccountById_ShouldReturnAccount() {
		
	}
}