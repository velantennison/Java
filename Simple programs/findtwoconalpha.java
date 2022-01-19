 public class findtwoconalpha{
    public static void main(String[] args){
        for(int j=0;j<args.length;j++)
        {
        String a= args[j];
        int count =0;
        for(int i=0; i < a.length() - 1 ; i++)
        {
            String d = a.toLowerCase();
            int b =d.charAt(i);
            int c =d.charAt(i+1) - 1;
                if(b == c){
                    count++;
                }  
            }
        System.out.println(a + " - " + count);
        }  
    }
    }