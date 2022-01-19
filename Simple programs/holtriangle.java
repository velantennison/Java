import java.util.Scanner;
public class holtriangle
{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
    System.out.print("Enter Number: ");
    int a = input.nextInt();
    for(int i=1;i<=a;i++)
    {
        if(i<a)
        {
        for(int j=a-i;j>=1;j--)                                                
            System.out.print(" ");                            
        for(int k=1;k<=i;k++)        
        {           
            if(k==1|k==i)
             System.out.print(" *");   
             else
             System.out.print("  ");  
        }          
         System.out.println();
        }
        else{
            System.out.print(" "); 
            for(int k=1;k<=i-1;k++)                                   
            System.out.print("**");
            System.out.print("*");
        }
    }
    }
}