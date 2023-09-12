package account.adapter.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = TransferPortImplTest.class) // para pegar as configurações da classe TransferPortImplTest para injetar dependências
@ExtendWith(SpringExtension.class) // integra o JUnit e SpringBoot
public class TransferPortTest {
	
	
	@Test
	void nullAccountId_ShouldThrowsExceptionHandler_WhenMethodGetAccountByIdIsCalled() {
		
	}
	
	@Test
	void getAccountById_ShouldReturnAccount() {
		
	}
}
