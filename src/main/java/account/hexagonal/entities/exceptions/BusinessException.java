package account.hexagonal.entities.exceptions;

public class BusinessException extends Exception {
	
	public BusinessException (String message) {
		super(message);
	}
}
