package org.bank;

import java.sql.SQLException;

/**
 * 
 * @author velan
 *
 */
public interface Bank 
{
	
	public abstract Account getAccountDetail(long accountNumber);
	
	public abstract Card getCardDetail(long cardNumber) throws SQLException;

	public abstract Object depositProcess(double amount, Account accountDetail);

	public abstract Object withdrawalProcess(double amount, Account accountDetail);

	public abstract byte isValidCard(Card card);

	public abstract boolean isValidPinNumber(String inputPinNumber, Card card);

	public abstract void cardBlocking(Card card);

}
