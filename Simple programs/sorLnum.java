 public class sorLnum{
    
    public static void main(String[] args){
        int a[]={5,4,3,2,1};
        //  int[] a = new int[2] ;
        // a[0] = 100;
        // a[1] = 20;
        int b = a[0];
         int c = a[0];
        for (int i = 0; i < a.length; i++)
        {
            if(b < a[i])
            b = a[i];
            if(c > a[i])
            c = a[i];
        }
        System.out.println("largest number : " + b);
        System.out.println("smallest number : " + c);
    }
 }