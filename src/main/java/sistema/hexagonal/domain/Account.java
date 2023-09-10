package sistema.hexagonal.domain;

import java.math.BigDecimal;
import java.util.Objects;

import sistema.hexagonal.domain.exceptions.BusinessException;

public class Account {
	private Integer accountId;
	private BigDecimal balance;
	private String owner;
	
	public Account() {
		accountId = 0;
		balance = BigDecimal.ZERO;
		owner = "Correntista nao informado";
	}
	public Account(Integer accountId, BigDecimal balance, String owner) {
		this.accountId = accountId;
		this.balance = balance;
		this.owner = owner;
	}
	
	// Access methods
	public Integer getAccountId() {
		return accountId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public String getOwner() {
		return owner;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public void credit(BigDecimal amount) throws BusinessException {
		if (Objects.isNull(amount)) { throw new BusinessException("Informe o valor a ser creditado"); }
		else if (amount.compareTo(BigDecimal.ZERO) <= 0) { throw new BusinessException("Informe um valor maior que zero a ser creditado"); }
		
		balance = balance.add(amount);
	}
	public void debit(BigDecimal amount) throws BusinessException {
		if (Objects.isNull(amount)) { throw new BusinessException("Informe o valor a ser debitado"); }
		else if (amount.compareTo(BigDecimal.ZERO) <= 0) { throw new BusinessException("Informe um valor maior que zero a ser debitado"); }
		else if (amount.compareTo(this.balance) > 0) { throw new BusinessException("Saldo insuficiente"); }
		
		this.balance = balance.subtract(amount);
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", owner=" + owner + "]";
	}
}
