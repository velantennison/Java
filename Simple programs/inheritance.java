

public class inheritance extends sec
{
    public int j = 2;
    public void inheritance()
    {
        System.out.println(j);
    }

    public static void main(String[] args){
        System.out.println("haii");
       inheritance obj = new inheritance();
        System.out.println(obj.i);
        obj.inheritance();
    }
}
class sec{
    public int i = 1;
    public void sec()
    {
        System.out.println(i);
    }
}