 public class test{
    
    public static void main(String[] args){
StringBuilder sb=new StringBuilder(args[0]);  
sb.reverse();  
System.out.println(sb);//prints olleH  
String a = args[0];
for(int i=a.length()-1;i>=0;i--)
{
   System.out.print(a.charAt(i));
}

}  
    }