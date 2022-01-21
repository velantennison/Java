package org.atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.bank.Account;
import org.bank.Bank;
import org.bank.Card;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
/**
 * @author velan
 */
public class ATM 
{
	private int count = 0;
	/**
	 * ATM transaction process are starts in this method
	 * @param card have current card details from account
	 */
	private void transactionProcess(Card card) 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try
		{
		System.out.println("Enter Pin Number");			//user input pin Number
		int pin = scanner.nextInt();
		String pinNumber = Integer.toString(pin);
		if(card.getBankName().equalsIgnoreCase("sbi"))	//checking bank for further process
		{
			System.out.println("\nselect Transaction option\n1.Deposit (Press 1 for Deposit)"
				+ "\n2.Withdraw (Press 2 for withdraw)\n3.Balance Inquiry (Press 3 for Balance Enquiry)"
				+ "\n4.Fast Cash (Press 4 for fast cash)"	
				+ "\n5.Mini Statement (Press 5 for mini statement)\n6.Cancel (Press any other key to cancel)");
			byte option = scanner.nextByte();
			if(Printer.isPrinterReady()) 				//without ink or paper
			{
				System.out.println("process continue without receipt\n are you ok");
				System.out.println("1.ok  (press 1 for continue)\n2.cancel   (press 2 for cancel)");
				byte ok = scanner.nextByte();
				if(ok == 1) 
				{
					try
					{
						switch(option)
						{
							case 1:
								deposit(card,pinNumber);
								break;
							case 2:
								withdrawal(card,pinNumber);
								break;
							case 3:
								balanceInquiry(card,pinNumber);
								break;
							case 4:
								fastCash(card,pinNumber);
								break;
							case 5:
								System.out.println("temporarily mini statement unavailable");
								System.out.println("\nTransaction cancelled\nTake your card\nThank you");
								break;
							default:
								count = 0;
								System.out.println("\nTransaction cancelled\nTake your card\nThank you");
						}
					}
					catch(InputMismatchException e )
					{
						System.out.println("\nTransaction cancelled\nTake your card\nThank you");
					}
				}
				else 
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
			else 						//with ink & paper
			{
				try
				{
					switch(option)
					{
						case 1:
							deposit(card,pinNumber);
							break;
						case 2:
							withdrawal(card,pinNumber);
							break;
						case 3:
							balanceInquiry(card,pinNumber);
							break;	
						case 4:
							fastCash(card,pinNumber);
							break;
						case 5:
							miniStatement(card,pinNumber);
							break;
						default:
							count = 0;
							System.out.println("\nTransaction cancelled\nTake your card");
					}
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
		}
		else {
			System.out.println("\nselect Transaction option\n1.Deposit (Press 1 for Deposit)"
					+ "\n2.Withdraw (Press 2 for withdraw)\n3.Balance Inquiry (Press 3 for Balance Enquiry)"
					+"\n4.Cancel (Press any key to cancel)");
			byte option = scanner.nextByte();
			if(Printer.isPrinterReady()) 				//without ink or paper
			{
			System.out.println("process continue without receipt\n are you ok");
			System.out.println("1.ok  (press 1 for continue)\n2.cancel   (press 2 for cancel)");
			byte ok = scanner.nextByte();
			if(ok == 1) 
			{
				try
				{
					switch(option)
					{
						case 1:
							deposit(card,pinNumber);
							break;
						case 2:
							withdrawal(card,pinNumber);
							break;
						case 3:
							balanceInquiry(card,pinNumber);
							break;
						default:
							count = 0;
							System.out.println("\nTransaction cancelled\nTake your card\nThank you");
					}
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
			else 
			{
				System.out.println("\nTransaction cancelled\nTake your card\nThank you");
			}
		}
		else 						//with ink & paper
		{
			try
			{
			switch(option)
				{
					case 1:
						deposit(card,pinNumber);
						break;
					case 2:
						withdrawal(card,pinNumber);
						break;
					case 3:
						balanceInquiry(card,pinNumber);
						break;	
					default:
						count = 0;
						System.out.println("\nTransaction cancelled\nTake your card");
				}
			}
			catch(InputMismatchException e )
			{
				System.out.println("\nTransaction cancelled\nTake your card\nThank you");
			}
		}
		}
		}
		catch(InputMismatchException e )
		{
			System.err.println("Invalid Pin Number");
			transactionProcess(card);
		}
	}
/**
 * Set atm cash count
 * @param rs2000 two thousand rupees count
 * @param rs500 five hundred rupees count
 * @param rs200 two hundred rupees count
 * @param rs100 one hundred rupees count
 */
	protected void setATMCash(int rs2000, int rs500, int rs200, int rs100) 
	{
		int two_thousands = Cash.rupees2000 + rs2000;
		int five_hundreds = Cash.rupees500 + rs500;
		int two_hundreds = Cash.rupees200 + rs200;
		int hundreds = Cash.rupees100 + rs100;
		Connection connection = null;
	      Statement stmt = null; 
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sbiatm","postgres","velan045");
			stmt = connection.createStatement();
			stmt.executeUpdate( "update atm set two_thousands ="+two_thousands+", five_hundreds ="+five_hundreds+", two_hundreds ="
			+two_hundreds+", hundreds ="+hundreds+" where atm_id = 'sbi-atm-0001';");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     
	}
	/**
	 * subtract atm cash count
	 * @param rs2000 two thousand rupees count
	 * @param rs500 five hundred rupees count
	 * @param rs200 two hundred rupees count
	 * @param rs100 one hundred rupees count
	 */
	protected void subtractATMCash(int rs2000, int rs500, int rs200, int rs100) 
	{
		int two_thousands = Cash.rupees2000 - rs2000;
		int five_hundreds = Cash.rupees500 - rs500;
		int two_hundreds = Cash.rupees200 - rs200;
		int hundreds = Cash.rupees100 - rs100;
		Connection connection = null;
	      Statement stmt = null; 
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sbiatm","postgres","velan045");
			stmt = connection.createStatement();
			stmt.executeUpdate( "update atm set two_thousands ="+two_thousands+", five_hundreds ="+five_hundreds+", two_hundreds ="
			+two_hundreds+", hundreds ="+hundreds+" where atm_id = 'sbi-atm-0001';");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private CardReader cardReader = new CardReader();
	
	/**
	 * deposit initialization
	 * @param card user input card 
	 * @param pinNumber user input pin number
	 */
	private void deposit(Card card, String pinNumber) 
	{
		System.out.println("\nEnter Deposit Amount");
		try
		{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			double amount = scanner.nextLong();
			byte isValidAmount = (byte) (amount % 100);
			if( isValidAmount == 0 & amount >= 100 )
			{
				depositProcess(amount,card,pinNumber);
			}
			else
			{
				try
				{
					System.err.println("please enter valid amount");
					System.out.println("\nyou want to continue\n1.ok\n2.cancel");
					byte continued = scanner.nextByte();
					if(continued == 1)
						deposit(card,pinNumber);
					else
						System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
		}
		catch(InputMismatchException e )
		{
			System.err.println("Invalid amount");
			deposit(card,pinNumber);
		}
	}

	/**
	 * deposit process
	 * @param amount user input amount
	 * @param card user input card
	 * @param inputPinNumber user input pin number
	 */
	private void depositProcess(double amount, Card card, String inputPinNumber) 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try
		{
			System.out.println("\nEnter Denominations\nRs.2000 = ");
			byte rs2000 = 0;
				rs2000 = scanner.nextByte();
			System.out.println("Rs.500 = ");
			byte rs500 = 0;
				rs500 = scanner.nextByte();
			System.out.println("Rs.200 = ");
			byte rs200 = 0;
				rs200 = scanner.nextByte();
			System.out.println("Rs.100 = ");
			byte rs100 = 0;
				rs100 = scanner.nextByte();
	
			double value = Dispenser.calculateCashValue(rs2000,rs500,rs200,rs100);
			if(amount == value) 				// denomination check
			{
				if(100-Cash.rupees2000 >= rs2000 & 100-Cash.rupees500 >= rs500 & 100-Cash.rupees200 >= rs200 & 100-Cash.rupees100 >= rs100)
				{
					Bank bank = card.getBankInstance(card.getBankName());
					if(bank.isValidPinNumber(inputPinNumber,card))
					{
						setATMCash(rs2000, rs500, rs200, rs100);
						JSONObject transaction = (JSONObject)bank.depositProcess(amount, bank.getAccountDetail(card.getAccountNumber()));
						Printer.receipt(transaction);
						cardReader.ejectCard();
					}
					else 
					{
						count++;
						System.err.println("Invalid Pin Number");
						
						if(count == 2)			// card blocking after 3 attempt
						{
							bank.cardBlocking(card);
							cardReader.ejectCard();
						}
						else
						{
							System.out.println("\nyou want to continue\n1.ok\n2.cancel");
							byte continued = scanner.nextByte();
							if(continued == 1)
								transactionProcess(card);
							else
								System.out.println("\nTransaction cancelled\nTake your card\nThank you");
						}
					}
				}
				else 
				{
					System.out.println("Something went wrong\n Please collect your cash\\nTransaction cancelled\\nTake your card\\nThank you");
				}
			}
			else
			{
				try
				{
					System.err.println("wrong denomination");
					Dispenser.dispenceCash();
					System.out.println("\nyou want to continue\n1.ok\n2.cancel");
					byte continued = scanner.nextByte();
					if(continued == 1)
						deposit(card,inputPinNumber);
					else
						System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
		}
		catch(InputMismatchException e )
		{
			System.err.println("wrong denomination");
			Dispenser.dispenceCash();
		}
	}
	
/**
 * withdraw initialization
 * @param card user input card
 * @param pinNumber user input pin number
 */
	private void withdrawal(Card card, String pinNumber) 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try
		{
			System.out.println("\nEnter Withdrawal Amount");
			double amount = scanner.nextLong();
			byte isValidAmount = (byte) (amount % 100);
			if( isValidAmount == 0 & amount > 99)		// validate input amount
			{
				withdrawalProcess(amount,card,pinNumber);
			}
			else
			{
				try
				{
					System.err.println("Invalid amount");
					System.out.println("\nyou want to continue\n1.ok\n2.cancel");
					byte continued = scanner.nextByte();
					if(continued == 1)
						withdrawal(card,pinNumber);
					else
						System.out.println("\nTransaction cancelled\nTake your card\nThank you");
					}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
		}
		catch(InputMismatchException e )
		{
			System.err.println("Invalid amount");
			withdrawal(card,pinNumber);
		}
	}
	/**
	 * fast cash initialization
	 * @param card user input card
	 * @param pinNumber user input pin number
	 */
	private void fastCash(Card card, String pinNumber) 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try
		{
			System.out.println("\nSelect Withdrawal Amount");
			System.out.println("\n1.10000\n2.5000\n3.2000\n4.1000\n5.Cancel (Press any other key to cancel)");
			double amount = 0;
			byte option = scanner.nextByte();
			switch(option)
			{
				case 1:
					amount = 10000;
					break;
				case 2:
					amount = 5000;
					break;
				case 3:
					amount = 2000;
					break;
				case 4:
					amount = 1000;
					break;
				default:
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
			}
			byte isValidAmount = (byte) (amount % 100);
			if( isValidAmount == 0 & amount > 99 )			// validate input amount
			{
				withdrawalProcess(amount,card,pinNumber);
			}
			else
			{
				try
				{
					System.err.println("Invalid amount");
					System.out.println("\nyou want to continue\n1.ok\n2.cancel");
					byte continued = scanner.nextByte();
					if(continued == 1)
						withdrawal(card,pinNumber);
					else
						System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
		}
		catch(InputMismatchException e )
		{
			System.out.println("\nTransaction cancelled\nTake your card\nThank you");
		}
	}
	/**
	 * withdrawal process
	 * @param amount user input amount 
	 * @param card user input card
	 * @param inputPinNumber user input pin number
	 */
	private void withdrawalProcess(double amount, Card card, String inputPinNumber) 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int cashCount[] = new int[4];
		Bank bank = card.getBankInstance(card.getBankName());
		if(bank.isValidPinNumber(inputPinNumber,card))
		{
			try 
			{
				cashCount = Cash.Denomination(amount);		//get denomination from Cash
				Account account = bank.getAccountDetail(card.getAccountNumber());
				if(account.getAccountBalance() > amount & account.getWithdrawLimit() >= amount)
				{
					JSONObject transaction = (JSONObject) bank.withdrawalProcess(amount, bank.getAccountDetail(card.getAccountNumber()));
					subtractATMCash(cashCount[0], cashCount[1], cashCount[2], cashCount[3]);
					Dispenser.dispenceCash();
					Printer.receipt(transaction);
					cardReader.ejectCard();
				}
				else 
				{
					System.out.println("you Reached your Daily Limit");
					cardReader.ejectCard();
				}
			}
			catch(ArrayIndexOutOfBoundsException e) 
			{
				System.err.println("sorry no Available money in ATM");
				cardReader.ejectCard();
			}
		}
		else 
		{
			count++;
			System.err.println("Invalid Pin Number");
			if(count == 2)			// card blocking after 3 attempt
			{
				bank.cardBlocking(card);
				cardReader.ejectCard();
			}
			else
			{
				try
				{
					System.out.println("\nyou want to continue\n1.ok\n2.cancel");
					byte continued = scanner.nextByte();
					if(continued == 1)
						transactionProcess(card);
					else
						System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
		}
	}
	/**
	 * balance inquiry process
	 * @param card user input card
	 * @param inputPinNumber user input pin number
	 */
	private void balanceInquiry(Card card, String inputPinNumber) 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Bank bank = card.getBankInstance(card.getBankName());
		if(bank.isValidPinNumber(inputPinNumber,card))
		{
				Account account = bank.getAccountDetail(card.getAccountNumber());
				System.out.println("\nAccount Balance : "+ account.getAccountBalance());
				Printer.inquiryReceipt(account);
				cardReader.ejectCard();
		}
		else 
		{
			count++;
			System.out.println("Invalid Pin Number");
			if(count == 2)			// card blocking after 3 attempt
			{
				bank.cardBlocking(card);
				cardReader.ejectCard();
			}
			else
			{
				try
				{
					System.out.println("\nyou want to continue\n1.ok\n2.cancel");
					byte continued = scanner.nextByte();
					if(continued == 1)
						transactionProcess(card);
					else
						System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
		}
	}
	/**
	 * mini statement process
	 * @param card user input card
	 * @param inputPinNumber user input pin number
	 */
	private void miniStatement(Card card, String inputPinNumber) 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Bank bank = card.getBankInstance(card.getBankName());
		if(bank.isValidPinNumber(inputPinNumber,card))
		{
			Account account = bank.getAccountDetail(card.getAccountNumber());
			JSONArray transactionDetails = account.getTransactionDetails();
			double balance = account.getAccountBalance();
			Printer.miniStatementReceipt(transactionDetails,balance);
		}
		else
		{
			count++;
			System.err.println("Invalid Pin Number");
			if(count == 2)			// card blocking after 3 attempt
			{
				bank.cardBlocking(card);
				cardReader.ejectCard();
			}
			else
			{
				try
				{
					System.out.println("\nyou want to continue\n1.ok\n2.cancel");
					byte continued = scanner.nextByte();
					if(continued == 1)
						transactionProcess(card);
					else
						System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nTransaction cancelled\nTake your card\nThank you");
				}
			}
		}
	}
	/**
	 * This is the initialization of atm
	 */

	private static void initialize() 			//ATM initialization
	{
		boolean atmHasInternet = true;
		while(atmHasInternet)
		{
			Connection connection = null;
		      Statement stmt = null; 
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sbiatm","postgres","velan045");
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
		     try {
				 stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM atm where atm_id = 'sbi-atm-0001';");
				if(rs.next())
				{
					Cash.rupees2000 = rs.getInt("two_thousands") ;
					Cash.rupees500 = rs.getInt("five_hundreds") ;
					Cash.rupees200 = rs.getInt("two_hundreds") ;
					Cash.rupees100 = rs.getInt("hundreds") ;
				}
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			CardReader cardReader = new CardReader();
			if(atmHasInternet)
			{
				System.out.println("\nWelcome To SBI ATM");	 
				try 
				{
					Card card = cardReader.read();		//to read cardnumber and bankName
					Bank bank = card.getBankInstance(card.getBankName());
					card = bank.getCardDetail(card.getCardNumber());
					byte isValidCard = bank.isValidCard(card);
					if (isValidCard == 1)
						{ 
							System.out.println( "\n your card has already expired :-(\n"
									+ "Take your card\nThank You");
						}
					else if(isValidCard == 2)
						{
							System.out.println("Your card is Blocked\nContact your branch"
									+ "\nTake your card\nThank You");
						}
					else
							{
								System.out.println( "welcome "+ card.getCardHolderName() );
								try 
								{
									ATM atm = new ATM();
									atm.transactionProcess(card);
								}
								catch(JSONException e) 
								{
									System.out.println("\nInvalid card\ncontact your branch ");
									Printer.errorReceipt(107);
									cardReader.ejectCard();
								}
							}
				}
				catch(JSONException e) 
				{
					System.out.println("\nInvalid card\ncontact your branch ");
					Printer.errorReceipt(101);
					cardReader.ejectCard();
				}
				catch(NullPointerException e )
				{
					System.out.println("\nInvalid card\ncontact your branch ");
					Printer.errorReceipt(102);
					cardReader.ejectCard();
				}
				catch(InputMismatchException e )
				{
					System.out.println("\nInvalid card\ncontact your branch ");
					cardReader.ejectCard();
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("\nInvalid card\ncontact your branch ");
					cardReader.ejectCard();
				}
				catch(ClassCastException e)
				{
					System.out.println("\nInvalid card\ncontact your branch ");
					cardReader.ejectCard();
				}
				catch(Exception e)
				{
					System.out.println("\nSomething went wrong ");
					cardReader.ejectCard();
				}
			}
			else 
			{
				System.out.println("\nSorry!\ntemporarily out of Service");
			}
		}
	}
	public static void main(String[] args) 
	{
		ATM.initialize();
	}
	/**
	 * 
	 * @author velan
	 *
	 */
	private class Cash
	{	
		private static int rupees2000;
		private static int rupees500 ;
		private static int rupees200 ;
		private static int rupees100 ;
		/**
		 * calculating denomination to dispence
		 * @param withdraw_amount
		 * @return cash count array
		 */
		public static int[] Denomination(double withdraw_amount) 		//denomination calculation process
		{
			int[] money = {2000,500,200,100};
			int[] count = {Cash.rupees2000,Cash.rupees500,Cash.rupees200,Cash.rupees100};
			int atmCashCounts[] = new int[4];
			int i = 0;
			int j = 0;
			while(withdraw_amount > 0 )
			   {
					int cashCount;
					if(money[i] == 2000 & withdraw_amount >= 2000 & rupees500 >= 3 & rupees200 >= 2 & rupees100 > 0 )
					{
						cashCount = (int) ((withdraw_amount/money[i]) - 1);
					}
					else if(money[i] == 2000 & withdraw_amount <= 2000 & rupees500 >= 3 & rupees200 >= 2 & rupees100 > 0 )
					{
						cashCount = 0;
					}
					else if(money[i] == 500 & withdraw_amount >= 500 & rupees200 >= 2 & rupees100 > 0)
					{
						cashCount = (int) ((withdraw_amount/money[i]) - 1); 
					}
					else if(money[i] == 500 & withdraw_amount <= 500 & rupees200 >= 2 & rupees100 > 0)
					{
						cashCount = 0;
					}
					else
						cashCount = (int) (withdraw_amount/money[i]);
				   
					if(count[i] > cashCount)
				        {
							withdraw_amount = withdraw_amount - ( cashCount*money[i] );
							atmCashCounts[j++] = cashCount;
				        }
					else
				        {
				        	withdraw_amount = withdraw_amount - ( count[i]*money[i] );
				        	atmCashCounts[j++] = 0;
				        }
				        i++;
			   }
				return atmCashCounts;
		}
	}
}
