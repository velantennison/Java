public class nonrepeat {
 public static void main(String[] args) {
		String a = "qwertyQWERTYiopdfghop";
		a = a.toLowerCase();
		int b[]=new int[500];
	    for(int i=0;i<a.length();i++)
	    {
	        if(a.charAt(i) != ' ')
			{
	           b[a.charAt(i)]++;
			}
	    }
	   for(int i=0;i<a.length();i++)
	    {
	        if(b[a.charAt(i)] == 1){
	           char c = a.charAt(i);
	            System.out.print(c + " ");
	        }
	    }
	}
}