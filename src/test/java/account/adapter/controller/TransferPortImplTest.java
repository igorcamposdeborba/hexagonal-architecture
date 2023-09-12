package account.adapter.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
	"account.adapter", // objetos do sistema
	"account.hexagonal" // adapter mock
})
public class TransferPortImplTest {
}
