package sistema.hexagonal.domain.exceptions;

public class ExceptionHandler {
	
	public static void requiredLabel(String name) {
		throw new BusinessException(name + " eh obrigatorio");
	}
	
	public static void unexists(String name) {
		throw new BusinessException(name + " eh inexistente");
	}
	
	public static void noBalance() {
		throw new BusinessException("Saldo insuficiente");
	}

	public static void sameAccount() {
		throw new BusinessException("Conta debito e credito devem ser diferentes");
	}
}
