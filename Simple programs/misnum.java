 public class misnum{
    public static void main(String[] args){
        int a[]={3,6,9,20};    // assending sort and desending sort only
        int b = a[0];
        int c = 0;
        int d = a.length - 1;
         if(a[0] < a[d])
         {
             // b=1;     // if the missing number find from 1.
        for (int i = 0; i <= d; i++)
        {
            c = a[i];
            if(b != c)
            {
                for(int j = b;j < c;j++)
                {
                    System.out.print(" " + b );
                    b = b+1;
                }
                b = c + 1;
            }
            else{
                b = c + 1;
            }
        }
         }
         else if(a[0] > a[d]){
             for (int i = 0; i <= d; i++)
        {
            c = a[i];
            if(b != c)
            {
                for(int j = b;j > c;j--)
                {
                        b = b-1;
                        System.out.print(" " + j );
                }
                 b = c - 1;
            }
            else{
                b = c - 1;
            } 
        }
         }
    }
 }
 