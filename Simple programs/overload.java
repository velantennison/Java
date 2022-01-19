public class overload
{
    public Long add(Long l1,Long l2)
    {
        System.out.println("Long");
        return l1+l2;
    }
    public long add(long l1,long l2)
    {
        System.out.println("primitive Long");
        return l1+l2;
    }
    public Integer add(Integer i1,Integer i2)
    {
        System.out.println("Integer");
        return i1+i2;
    }
     public int add(Short s1,Short s2)
    {
        System.out.println("Short");
        return s1+s2;
     }
     public int add(int k1,int k2)
    {
        System.out.println("int");
        return k1+k2;
     }
    public static void main(String args[]){
        System.out.println("hai");
       overload obj = new overload();
        System.out.println(obj.add(1,3));
    }
    public static void main(String args){
        System.out.println("haii");
       overload obj = new overload();
        System.out.println(obj.add(1,3));
    }
    public static void main(String args1,String args2){
        System.out.println("haiii");
       overload obj = new overload();
        System.out.println(obj.add(1,3));
    }
}
