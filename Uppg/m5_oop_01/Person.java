package m5_oop_01;

public abstract class Person {
	protected String name;
	protected int age;
	
	public Person(String name, int age){
 	    this.name = name;
 	    this.age = age;
	}
	
	protected void printInfo(){
 	    System.out.println("Name: " + name);
 	    System.out.println("Age: " + age);
	}
}

