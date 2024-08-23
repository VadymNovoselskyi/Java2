package m7_oop;

public class Car<T> {
 	private T owner;
 	private String registration;
 	
 	public Car(T owner, String registration){
      	this.owner = owner;
      	this.registration = registration;
 	}
 	
 	public T getOwner(){
      	return owner;
 	}
 	
 	public static void main(String[] args){
      	Car<String> car1 = new Car<String>("Lisa O-son", "REG 123");
        Car<Person> car2 = new Car<Person>(new Person("Kalle J-son", "990402-1111"), "PEG 669");
      	System.out.println(car1.getOwner());
        System.out.println(car2.getOwner());
 	}
} 
