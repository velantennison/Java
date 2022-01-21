package org.atm;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bank.Account;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 
 * @author velan
 *
 */
public class Printer 
{
	protected static int ink = 100;		//ml
	protected static int paper = 500;	//cm
	protected static void receipt(JSONObject transaction) 
	{
		if(ink > 0 & paper > 0 )
		{
			System.out.println("\n Receipt");
			Date dateObject = (Date) transaction.get("Date");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			String date = dateFormat.format(dateObject);
			String time = timeFormat.format(dateObject);
			System.out.println("\nTransaction Id\t:"+transaction.get("TransactionId")+
					"\nDate  :"+date+"\tTime  :"+time+"\n"+
					transaction.get("transactionType")+" Amount\t: Rs." + transaction.get("transactionValue")+
					"\nAccount Balance\t:"+transaction.get("accountBalance"));
			
			ink -= 1;
			paper -= 5;
		}
	}
	protected static void inquiryReceipt(Account account)
	{
		if(ink > 0 & paper > 0 )
		{
			System.out.println("\n Receipt");
			Date dateObject = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			String date = dateFormat.format(dateObject);
			String time = timeFormat.format(dateObject);
			System.out.println("\nDate  :"+date+"\tTime  :"+time+"\nAccount Number: " +
			account.getAccountNumber()+ "\nAccount Balance\t Rs.:" + account.getAccountBalance());
			ink -= 1;
			paper -= 5;
		}
	}
	protected static void miniStatementReceipt(JSONArray transactionDetails, double accountBalance)
	{
		if(ink > 0 & paper > 0 )
		{
			System.out.println("\n Receipt");
			for(int i = 0; i < transactionDetails.length();i++)
			{
				JSONObject transaction = transactionDetails.getJSONObject(i);
				Date dateObject = (Date) transaction.get("Date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
				SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
				String date = dateFormat.format(dateObject);
				String time = timeFormat.format(dateObject);
				System.out.println("\nDate :"+date+"\tTime :"+time+"\t "+
						transaction.get("transactionType")+": Rs." + transaction.get("transactionValue"));
						
			}
			System.out.println("\nAccount Balance\t: Rs."+accountBalance);
			ink -= 2;
			paper -= 10;
		}
	}
	protected static boolean isPrinterReady() 
	{
		if(ink == 0 | paper <= 5)
		{
			return true;
		}
		return false;
	}
	protected static void errorReceipt(int errorCode)
	{
		if(ink > 0 & paper > 0)
		{
			System.out.println("\nReceipt");
			Date dateObject = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			String date = dateFormat.format(dateObject);
			String time = timeFormat.format(dateObject);
			System.out.println("\nDate  :"+date+"\tTime  :"+time+"\nError Code : "+errorCode);
		}
		ink -= 1;
		paper -= 5;
	}
}
