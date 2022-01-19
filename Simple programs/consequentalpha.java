 public class consequentalpha{
    public static void main(String[] args){
        String a= args[0];
        String b = "abcdefghijklmnopqrstuvwxyz";
        int count =0;
        for(int i=0; i < a.length() - 1; i++)
        {
            String d =a.substring(i,i+2);
            for(int j=0;j<25;j++)
            {
                String c = b.substring(j,j+2);
                if(c.equals(d)){
                    count++;
                }  
            }
        }
        System.out.print(a + " - " + count);
        }  
    }