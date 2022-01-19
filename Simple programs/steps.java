import java.util.Scanner;
public class steps
{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
    System.out.print("Enter Number: ");
    int start = input.nextInt();
    for(int i=1;i<=start;i++)
    {
        for(int j=i;j>=1;j--)
        System.out.print(j);
        System.out.println();
    }
    for(int i=start;i>=0;i--)
       {
        for(int j=i-1;j>=1;j--)
        System.out.print(j);
        start =start-1;
        System.out.println();
       }
    }
}