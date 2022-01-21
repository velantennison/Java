package com.database;

import java.util.Date;

import org.bank.Account;
import org.bank.Card;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author velan
 *
 */
public class Database 
{
		private static Database database = new Database();
		
		 public static Database getDatabase()
		 {  
			  return database;  
		 }  
		 boolean set = true;
		 private Connection getConnection() throws SQLException 
		 {
		    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sbiatm","postgres","velan045");
		    if(set)
		    {
		    	this.set = false;
			Timer timer = new Timer ();
			TimerTask task = new TimerTask () {
			    @Override
			    public void run () {
			    	Statement stmt = null;
					try {
						stmt = connection.createStatement();
						stmt.executeUpdate( "update account set withdraw_limit = 20000 where account_type = 'savings';");
						stmt.executeUpdate( "update account set withdraw_limit = 50000 where account_type = 'current';");
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			};
			timer.schedule (task, 0l, 1000*60*15);
		    }
		    return connection;
		  }
		private JSONObject createAccountObject(double withdrawLimit,double accountBalance,long accountNumber, JSONArray transactionDetails)
		{
			JSONObject account= new JSONObject();
			account.put("accountNumber", accountNumber);
			account.put("withdrawLimit", withdrawLimit);
			account.put("accountBalance",accountBalance);
			account.put("transactionDetails", transactionDetails);
			return account;
			
		}
		private JSONObject createCardObject(long accountNumber, String cardHolderName, Date date, boolean cardIsBlocked, int pinNumber, String bankName) 
		{
			JSONObject card = new JSONObject();
			card.put("accountNumber", accountNumber);
			card.put("cardHolderName", cardHolderName);
			card.put("expiryDate", date);
			card.put("cardStatus", cardIsBlocked);
			card.put("pinNumber",pinNumber);
			card.put("bankName",bankName);
			return card;
		}
		private JSONObject createTransactionObject(String transactionId, Date date, String type, double balance, double value)
		{
			JSONObject transaction = new JSONObject();
			transaction.put("TransactionId", transactionId);
			transaction.put("Date", date);
			transaction.put("transactionType", type);
			transaction.put("accountBalance", balance);
			transaction.put("transactionValue", value);
			return transaction;
		}
		public JSONObject getCardDetails(long cardNumber, String bank) throws SQLException 
		{
			Connection connection = getConnection();
			ResultSet rs = null;
			 try {
				 
				Statement stmt = connection.createStatement();
				rs = stmt.executeQuery( "SELECT * FROM card where card_number = "+ cardNumber +";");
				
				 if ( rs.next()) {
					 long accounNumber = rs.getLong("account_number");
					 Date expiryDate = rs.getDate("expiry_date");
					 boolean status =  rs.getBoolean("card_status");
					 int pinNumber = rs.getInt("pin_number");
					 rs = stmt.executeQuery( "SELECT * FROM account where account_number = "+ rs.getLong("account_number")+";");
					 if(rs.next())
					 {
						 String name = rs.getString("account_holder_name");
						 rs = stmt.executeQuery("SELECT bank_name FROM bank where ifsc_code = '"+rs.getString("ifsc_code")+"';");
						 if(rs.next())
						 {
							 if(bank.equals(rs.getString("bank_name")))
							return createCardObject(accounNumber,name,expiryDate ,status, pinNumber,rs.getString("bank_name"));
						 }
					 }
				 }
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		public JSONObject getAccountDetails(long accountNumber,String bank) throws SQLException 
		{
			Connection connection = getConnection();
			ResultSet rs = null;
			double withdrawLimit = 0;
			double accountBalance = 0;
			 try {
				Statement stmt = connection.createStatement();
				rs = stmt.executeQuery( "SELECT * FROM account where account_number = "+ accountNumber +";");
				 if ( rs.next() ) {
					 	withdrawLimit = rs.getDouble("withdraw_limit");
					 	accountBalance = rs.getDouble("account_balance");					 	
					 }
				 rs = stmt.executeQuery( "SELECT * FROM transaction where account_number = "+ accountNumber +"order by date desc limit 5;");
				 JSONArray transactionDetails = new JSONArray();
				 while(rs.next()) {
				 		 String transactionId = rs.getString("transaction_id");
						 String dateStr = rs.getString("date");
						 DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
						 Date date = null;
						try {
							date = (Date)formatter.parse(dateStr);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						 String type = rs.getString("transaction_type");
						 double balance = rs.getDouble("account_balance");
						 double value = rs.getDouble("transaction_value");
						 JSONObject transaction = createTransactionObject(transactionId,date,type,balance,value);
						transactionDetails.put(transaction);
				 	}
				 return createAccountObject(withdrawLimit,accountBalance,accountNumber,transactionDetails);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection.close();
			return null;
		}
		
		public boolean updateBalance(double balance, Account account) throws SQLException 
		{
			Connection connection = getConnection();
			Statement stmt = null;
			stmt = connection.createStatement();
			stmt.executeUpdate( "update account set account_balance ="+balance+" where account_number = "+ account.getAccountNumber() +";");
			return true;
		}
		public void updateLimit(double limit, Account account) throws SQLException 
		{
			Connection connection = getConnection();
			Statement stmt = null;
			stmt = connection.createStatement();
			stmt.executeUpdate( "update account set withdraw_limit ="+limit+" where account_number = "+ account.getAccountNumber() +";");
			connection.close();
		}
		public void updateCard(Card card) throws SQLException 
		{
			Connection connection = getConnection();
			Statement stmt = null;
			stmt = connection.createStatement();
			stmt.executeUpdate( "update card set card_status = false where account_number = "+ card.getCardNumber() +";");
			connection.close();
		}
		public void updateTransaction(JSONObject transaction, Account account) throws SQLException 
		{
			Connection connection = getConnection();
			Statement stmt = null;
		         stmt = connection.createStatement();
		         String sql = "INSERT INTO transaction (transaction_id, date, account_balance, transaction_type, transaction_value, account_number) "
		            + "VALUES ('"+transaction.get("TransactionId")+"','"+transaction.get("Date")+"',"+account.getAccountBalance()+",'"
		        	+transaction.get("transactionType")+"',"+transaction.get("transactionValue")+","+account.getAccountNumber()+" );";
		         stmt.executeUpdate(sql);
		         connection.close();
		}
}
