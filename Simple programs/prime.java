import java.util.Scanner;  
import java.util.ArrayList;  
public class prime {  
       public boolean isPrime(int a) {
       for (int i = 2; i < a; i++) {  
           if (a % i == 0) {  
               return false;  
           }  
       }  
       return true;  
   }  
   public static void main(String[] args) {  
       Scanner s = new Scanner(System.in);  
       System.out.print("Enter a number : ");  
       int n = s.nextInt();  
       int q = 0;
       ArrayList<Integer> arr = new ArrayList<Integer>(q);
       prime obj=new prime();
       for(int i=1;i<=n;i++)
       {
       if (obj.isPrime(i)) {    
           int rev = 0;
        int j=i;
           while(i != 0)   
            {  
            int r = i % 10;  
            rev = rev * 10 + r;  
            i = i/10;
            }
        if (obj.isPrime(rev)) {  
            if (!arr.contains(j)) {
                arr.add(j);
            } 
            if (!arr.contains(rev)) {
                arr.add(rev);
            } 
        } 
        i=j;
       } 
       }
       System.out.println("Reverse of prime numbers is prime numbers");
       System.out.println(arr);
   }  
}  