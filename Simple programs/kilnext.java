import java.util.ArrayList;
public class kilnext {
    public ArrayList<Integer> find(ArrayList<Integer> a)
            {
            
                if(a.size() == 1){
                    return a;
                }
                else if(a.size()%2 == 0)
                {
                for(int j=1;j<a.size();j=j+1)
                {
                            a.remove(j);
                }
                // System.out.println(a);
                find(a);
                }
                else// if(a.size()%2 == 1)
                {
                    for(int j=1;j<a.size();j=j+1)
                {
                            a.remove(j);
                }
                a.remove(0);
                // System.out.println(a);
                find(a);
                }
                return a;
            }

    public static void main(String args[])
    {
        int n = 100;
        ArrayList<Integer> a = new ArrayList<Integer>(n);
        ArrayList<Integer> b = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++)
            a.add(i);
            kilnext obj = new kilnext();
            b = obj.find(a);
            System.out.println(b);
            
    }
}