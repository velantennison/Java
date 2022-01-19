class Methodoverride{  
public static void main(String args[]){  
SBI s=new SBI();  
ICICI i=new Bank();  
AXIS a=new AXIS();  
Bank b = new SBI();
System.out.println("SBI Rate of Interest: "+s.getRateOfInterest());  
System.out.println("ICICI Rate of Interest: "+i.getRateOfInterest());  
System.out.println("AXIS Rate of Interest: "+a.getRateOfInterest()); 
System.out.println("Bank Rate of Interest: "+b.getRateOfInterest()); 
}  
}  
class Bank{  
int getRateOfInterest(){return 0;}  
}  
//Creating child classes.  
class SBI extends Bank{  
int getRateOfInterest(){return 1;}  
}  
  
class ICICI extends Bank{  
int getRateOfInterest(){return 2;}  
}  
class AXIS extends Bank{  
int getRateOfInterest(){return 3;}  
}  
//Test class to create objects and call the methods  