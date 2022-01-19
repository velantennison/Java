import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class palindrome
{
    public static void palin(String str, int first, int last, List<String> set)
    {
        while (first >= 0 && last < str.length() && str.charAt(first) == str.charAt(last))
        {
            set.add(str.substring(first, last + 1));
            first--;
            last++;
        }
    }
    public static void find(String str)
    {
        if (str == null) {
            return;
        }
        List<String> set = new ArrayList<>();
        int n = str.length();
        for (int i = 0; i < n; i++)
        {
            palin(str, i, i, set);
            palin(str, i, i + 1, set);
        }

        String largest = set.get(0);
        int ans = 0;
        for(int i = 0; i < set.size(); i++)
        {
            if(set.get(i).length() > largest.length())
            {
                largest = set.get(i);
                ans = largest.length();;
            }
        } 
        for(int i = 0; i < set.size(); i++)
        {
            if(set.get(i).length() == ans)
            {
                System.out.println(set.get(i));
            }
        }

    }
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in); 
        System.out.print("Enter a string: ");  
        String str1= sc.nextLine();
        String str = str1.toLowerCase();
        // String str = "aabbccdd";
        find(str);
    }
}