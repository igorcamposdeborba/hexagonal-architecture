package sistema.hexagonal.domain.exceptions;

public class BusinessException extends Exception {
	
	public BusinessException (String message) {
		super(message);
	}
}
