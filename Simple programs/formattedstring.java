import java.util.Arrays;
import java.util.List;
class formattedstring {
  static void find(int n, String s, List<String> dict, String ans)
  {
    for(int i = 1; i <= n; i++)
    {
      String prefix=s.substring(0, i);
      if(dict.contains(prefix))
      {
        if(i == n)
        {
          ans += prefix;
          System.out.println(ans);
          return;
        }
        find(n - i, s.substring(i,n), dict, ans+prefix+" ");
      }
    }
  }
  public static void main(String args[])
  {
    String str1 = "likehaii";
    String ans="";
    int n1 = str1.length();                
    List <String> dict= Arrays.asList("hello","like" ,"hai", "sun" ,"day" , "i");         
    find(n1, str1, dict, ans);
  }
}