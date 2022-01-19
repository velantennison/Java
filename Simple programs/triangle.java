import java.util.Scanner;
public class triangle
{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
    System.out.print("Enter Number: ");
    int a = input.nextInt();
    for(int i=1;i<=a;i++)
    {
        for(int j=a-i;j>=1;j--)                 //if without j forloop  output                                   
            System.out.print(" ");              // *                      
        for(int k=1;k<=i;k++)                   // * * 
         System.out.print(" *");                // * * *
         System.out.println();
    }
    }
}