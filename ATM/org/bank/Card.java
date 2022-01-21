package org.bank;

import java.util.Date;
public class Card 
{
	
	private long cardNumber;
	private long accountNumber;
	private Date expiryDate;
	private String cardHolderName;
	private String bankName;
	private int pinNumber;
	private boolean cardStatus;
	private enum Banks 
	{
        SBI,
        IOB;
	}
	public long getCardNumber() 
	{
		return cardNumber;
	}

	public String getCardHolderName() 
	{
		return cardHolderName;
	}
	public long getAccountNumber() 
	{
		return accountNumber;
	}

	public Date getExpiryDate() 
	{
		return expiryDate;
	}

	public void setCardNumber(long cardNumber) 
	{
		this.cardNumber = cardNumber;
	}

	protected void setAccountNumber(long accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	protected void setExpiryDate(Date expiryDate) 
	{
		this.expiryDate = expiryDate;
	}

	protected void setCardHolderName(String cardHolderName) 
	{
		this.cardHolderName = cardHolderName;
	}

	public String getBankName() 
	{
		return bankName;
	}

	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}
/**
 * used to get bank instance
 * @param bankName user input bank name
 * @return bank object
 */
	public Bank getBankInstance(String bankName) 
	{
		Banks bank = Banks.valueOf(bankName);
		switch(bank) 
		{
			case IOB:
		            return new IOB();
		 	case SBI:
		 			return new SBI();
	        default:
	    		return null;
		}
	}

	public int getPinNumber() 
	{
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) 
	{
		this.pinNumber = pinNumber;
	}

	public boolean isCardStatus() 
	{
		return cardStatus;
	}

	public void setCardStatus(boolean cardStatus) 
	{
		this.cardStatus = cardStatus;
	}
}
