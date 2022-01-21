package org.bank;

import java.sql.SQLException;
import java.util.Date;
import org.json.JSONObject;
import com.database.Database;
/**
 * 
 * @author velan
 *
 */
public class IOB implements Bank 
{
	@Override
	/**
	 * used to get account details
	 * @return account object
	 */
	public Account getAccountDetail(long accountNumber) 
	{
		Database database = Database.getDatabase();
		JSONObject accountDetail;
		try {
			accountDetail = database.getAccountDetails(accountNumber,"IOB");
			Account account = new Account();
			account.setAccountNumber((long) accountDetail.get("accountNumber"));
			account.setAccountBalance((double) accountDetail.get("accountBalance"));
			account.setWithdrawLimit((double)accountDetail.get("withdrawLimit"));
			account.setTransactionDetails(accountDetail.getJSONArray("transactionDetails"));
			account.setBankName("IOB");
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	/**
	 * used to get card details
	 * @return card object
	 */
	public Card getCardDetail(long cardNumber) throws SQLException 
	{
		Database database = Database.getDatabase();
		JSONObject cardDetail = database.getCardDetails(cardNumber,"IOB");
		Card card = new Card();
		card.setCardNumber(cardNumber);
		card.setAccountNumber((long)cardDetail.get("accountNumber"));
		card.setExpiryDate((Date)cardDetail.get("expiryDate"));
		card.setCardHolderName((String) cardDetail.get("cardHolderName"));
		card.setBankName((String)cardDetail.get("bankName"));
		card.setPinNumber((int)cardDetail.get("pinNumber"));
		card.setCardStatus((boolean)cardDetail.get("cardStatus"));
		return card;
	}

	@Override
	/**
	 * update deposit record 
	 * @return transaction detail
	 */
	public Object depositProcess(double amount, Account account)
	{
		Object transaction = account.updateDepositRecord(amount, account);
		if(transaction != null) 
		{
			System.out.println("\nAmount Deposited \nRs:"+ amount);
			return transaction;
		}
		else 
		{
			depositProcess(amount,account);
			System.err.println("deposit error");
		}
		return null;
	}

	@Override
	/**
	 * update withdraw record 
	 * @return transaction detail
	 */
	public Object withdrawalProcess(double amount, Account account)
	{
		Object transaction = account.updateWithdrawalRecord(amount, account);
		if(transaction != null) 
		{
			System.out.println("\nAmount Withdrew \nRs:"+ amount);
			return transaction;
		}
		else 
		{
			depositProcess(amount,account);
			System.err.println("withdraw error");
		}
		return null;
	}

	@Override
	/**
	 * validate card
	 */
	public byte isValidCard(Card card)
	{
		Date date = card.getExpiryDate();
		boolean isCardExpired = date.before(new Date());
		if (isCardExpired)
			{
				return 1;
			}
		else if(card.isCardStatus() != true) 
			{
				return 2;
			}
		return 0;
	}
	@Override
	/**
	 * validate pin number 
	 */
	public boolean isValidPinNumber(String inputPinNumber,Card card) 		//pin validation process
	{
		int pinNumber = card.getPinNumber();
		boolean isPinMatched = inputPinNumber.matches( "(?:[0-9]{4})" );
		if(isPinMatched)
		{
			short isPinNumber = Short.parseShort(inputPinNumber);
			if(isPinNumber == pinNumber)
			{
				return true;
			}
			else{				
				return false;
			}
		}
		return false;
	}
	/**
	 * block card
	 * @param card
	 */
		public void cardBlocking(Card card) 
		{
			card.setCardStatus(false);
			Database database = Database.getDatabase();
			try {
				database.updateCard(card);
			} catch (SQLException e) {
				e.printStackTrace();
				cardBlocking(card);
			}
			System.out.println("your card is blocked");
		}
}
