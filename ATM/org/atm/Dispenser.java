package org.atm;

/**
 * 
 * @author velan
 *
 */
public class Dispenser 
{
	/**
	 * calculate user input cash value
	 * @param rs2000 user input two thousand rupees count
	 * @param rs500 user input five hundred rupees count
	 * @param rs200 user input two hundred rupees count
	 * @param rs100 user input hundred rupees count
	 * @return total cash value
	 */
		protected static double calculateCashValue(byte rs2000, byte rs500, byte rs200, byte rs100) 
		{
		return ((rs2000*2000)+(rs500*500)+(rs200*200)+(rs100*100));
		}
		/**
		 * this method user to dispence cash
		 */
		protected static void dispenceCash()
		{
			System.out.println("Please collect your Cash");
		}

}
