package account.hexagonal.entities.exceptions;

public class ExceptionHandler extends BusinessException {
	
	public ExceptionHandler(String message) {
		super(message);
	}

	public static void requiredLabel(String name) throws BusinessException {
		throw new BusinessException(name + " eh obrigatorio");
	}
	
	public static void unexists(String name) throws BusinessException {
		throw new BusinessException(name + " eh inexistente");
	}
	
	public static void noBalance() throws BusinessException {
		throw new BusinessException("Saldo insuficiente");
	}

	public static void sameAccount() throws BusinessException {
		throw new BusinessException("Conta debito e credito devem ser diferentes");
	}
}
