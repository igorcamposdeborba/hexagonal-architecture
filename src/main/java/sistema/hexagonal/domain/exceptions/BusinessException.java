package sistema.hexagonal.domain.exceptions;

public class BusinessException extends RuntimeException {
	
	public BusinessException (String message) {
		super(message);
	}
}
