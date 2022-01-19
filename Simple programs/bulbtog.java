import java.util.ArrayList;
public class bulbtog {
    public static void main(String args[])
    {
        int n = 100;
        ArrayList<Integer> a = new ArrayList<Integer>(n);
        for (int i = 1; i <= n; i++)
            a.add(0);
        for(int i=1;i<n;i++)
        {
            int m = i-1;
            for(int j=m;j<n;j = j + i)
            {
                int s = a.get(j);
                if(s == 0){
                    a.set(j,1);
                }
                else if(s == 1){
                    a.set(j,0);
                }
            }
        }
        int ans = 0;
        System.out.print("glowing bulbs are : ");
        for (int i = 0; i < n; i++)
        {
            int s = a.get(i);
            ans++;
            if(s == 1)
            {
                 System.out.print(ans+" ");
            }
        }
        System.out.println();
         System.out.println(a);
        
    }
}