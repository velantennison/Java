public class formatstring {   
        public static void main(String[] args)   
        {   
            String[] arr =new String[] {"Hai", "Hello", "Sun" ,"Day" ,"Like" , "I"};    
            String n = "ilikeapple"; 
            for(int i=0;i<arr.length;i++)
            {
                String word = arr[i].toLowerCase();
        if (n.indexOf(word) != -1) 
        {
            int a = n.indexOf(word);
            int b = n.indexOf(word) + word.length();
            String c = n.substring(b);
            String d =n.substring(0,a);

       
            if( c.charAt(0) == ' ')
            c = c.trim();
           n = d + " " + n.substring(a, b) + " " + c;
        } 
            }
        System.out.println(n);
        }   
    }  