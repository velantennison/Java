package com.database;

import java.util.Scanner;

public class Denomination {

	public static void main(String[] args) {
		while(true)
		{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter amount");
		int withdraw_amt = scanner.nextInt();
		int[] money = {2000,500,200,100};
		int r20 = 10;
		int r5 = 23;
		int r2 = 10;
		int r1 = 10;
		
		int[] count = {r20,r5,r2,r1};
		int[] arr = new int[4];
		int i = 0;
		int j = 0;
		String s = "";
			try {
				int r;
		while(withdraw_amt > 0 )
		   {
		   if(money[i] == 2000 & withdraw_amt >= 2000 & r5 >= 3 & r2 >= 2 & r1 > 0 )
			{
			   r = (withdraw_amt/money[i]) - 1;
			}
		   else if(money[i] == 2000 & withdraw_amt <= 2000 & r5 >= 3 & r2 >= 2 & r1 > 0 )
		   {
			   r = 0;
		   }
		   else if(money[i] == 500 & withdraw_amt >= 500 & r2 >= 2 & r1 > 0)
			{
				r = (withdraw_amt/money[i]) - 1; // Withdrawl amount devided by each money sets on above
			}
		   else if(money[i] == 500 & withdraw_amt <= 500 & r2 >= 2 & r1 > 0)
		   {
			   r=0;
		   }
			else
				r = (withdraw_amt/money[i]);
			if(count[i] > r)
		        {
					withdraw_amt= withdraw_amt-(r*money[i]); //a reminder will set again withdrawal amount.
					s = s+" "+r;
					System.out.println(r);
					arr[j++] = r;
		        }
		        else
		        {
		        	withdraw_amt= withdraw_amt-(count[i]*money[i]);
		        	s = s+" "+count[i];
		        	System.out.println(count[i]);
		        	arr[j++]= 0;
		        }
			
		        i++;
		   }
		System.out.println("count: "+s);
        System.out.println(withdraw_amt);
			}
			catch(Exception e) {
				System.out.println("sorry no money in ATM");
			}
	}
	}
}
