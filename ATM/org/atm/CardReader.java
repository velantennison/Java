package org.atm;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.bank.Card;
/**
 * 
 * @author velan
 *
 */
public class CardReader
{
	/**
	 * 
	 * @return card object
	 * @throws InputMismatchException for input match
	 */
	protected Card read() throws InputMismatchException 
	{
		@SuppressWarnings("resource")
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter Card Number");
		long cardNumber = scanner.nextLong();
		scanner.nextLine();
		String inputCardNumber = Long.toString(cardNumber);	//scanner for get cardNumber
		boolean numberIsMatched = inputCardNumber.matches("(?:[0-9]{16})");
		if(numberIsMatched)
		{
			System.out.println("Enter Bank Name");
			String inputBankName = scanner.nextLine().toUpperCase();
			cardNumber = Long.parseLong(inputCardNumber); 
			Card card = new Card();
				card.setBankName(inputBankName);
				card.setCardNumber(cardNumber);
			return card;
		}
		else 
		{
			throw new InputMismatchException();
		}
	}
	/**
	 * this method for eject card
	 */
	protected void ejectCard() 
	{
		System.out.println("\n Take your card\n Thank You");
	}
	
}
