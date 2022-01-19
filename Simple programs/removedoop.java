import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class removedoop {
    public static void main(String args[])
    {
        List<String> Collections = Arrays.asList( "alex", "brain", "charles","alex","brain");
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        a.addAll(Collections);
        System.out.println("Initial ArrayList " + a);
        int j;
         for(int i=0;i<a.size();i++)  
           {  
                   if(!b.contains(a.get(i)))
                   {
                       b.add(a.get(i));
                   }
           }  
           a=b;
           System.out.println(a);
    }
}