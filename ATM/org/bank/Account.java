package org.bank;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

import com.database.Database;
/**
 * 
 * @author velan
 *
 */
public class Account 
{
	private long accountNumber;
	private double accountBalance;
	private double withdrawLimit;
	private String bankName;
	private JSONArray transactionDetails = new JSONArray();

	public double getWithdrawLimit() 
	{
		return withdrawLimit;
	}

	public long getAccountNumber() 
	{
		return accountNumber;
	}

	public double getAccountBalance() 
	{
		return accountBalance;
	}
	public String getBankName() 
	{
		return bankName;
	}
	public JSONArray getTransactionDetails() 
	{
		return transactionDetails;
	}
	protected void setAccountNumber(long accountNumber) 
	{
		this.accountNumber = accountNumber;	
	}
	protected void setAccountBalance(double accountBalance) 
	{
		this.accountBalance = accountBalance;
	}
	protected void setWithdrawLimit(double withdrawLimit) 
	{
		this.withdrawLimit =  withdrawLimit;
	}
	protected void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}
	protected void setTransactionDetails(JSONArray transaction) 
	{
		this.transactionDetails = transaction;
	}
/**
 * update deposit record
 * @param value deposit amount
 * @param account current account detail
 * @return transaction detail
 * @throws SQLException 
 */
	protected JSONObject updateDepositRecord(double value, Account account )
	{
		Database database = Database.getDatabase();
		double balance = account.getAccountBalance();
		balance = balance + value ;
		boolean isUpdate;
		try {
			isUpdate = database.updateBalance( balance, account);
			if(isUpdate)
			{
				JSONObject transaction = new JSONObject();
				Date date = new Date();
				String transactionId = UUID.randomUUID().toString().replaceAll("-", "").substring(0,20);
				transaction.put("TransactionId", transactionId);
				transaction.put("Date", date);
				transaction.put("transactionType","deposit");
				transaction.put("accountBalance", balance);
				transaction.put("transactionValue", value);
				database.updateTransaction(transaction,account);
				return transaction;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			balance = balance - value;
			return null;
		}
		return null;
	}
	/**
	 * update withdrawal record
	 * @param value withdraw amount
	 * @param account current account detail
	 * @return transaction detail
	 */
	protected JSONObject updateWithdrawalRecord(double value, Account account )
	{
		Database database = Database.getDatabase();
		double balance = account.getAccountBalance();
		balance = balance - value ;
		try {
			boolean isUpdate = database.updateBalance( balance, account);
			if(isUpdate)
			{
				double limit = account.getWithdrawLimit();
				limit = (int) (limit - value);
				database.updateLimit(limit, account);
		
				JSONObject transaction = new JSONObject();
				Date date = new Date();
				String transactionId = UUID.randomUUID().toString().replaceAll("-", "").substring(0,20);
				transaction.put("TransactionId", transactionId);
				transaction.put("Date", date);
				transaction.put("transactionType","withdraw");
				transaction.put("accountBalance", balance);
				transaction.put("transactionValue", value);
				database.updateTransaction(transaction,account);
				return transaction;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			balance = balance + value;
		}
		return null;
	}
}
