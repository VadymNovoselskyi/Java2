package m7_oop;

public class Person {
	private String name;
	private String personalNumber;
	
	public Person(String name, String personalNumber) {
		this.name = name;
		this.personalNumber = personalNumber;
	}

	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

}
