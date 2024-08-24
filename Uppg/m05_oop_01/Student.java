package m05_oop_01;

public class Student extends Person {
	private String education;
	private int grade;
	
	public Student(String name, int age, String education, int grade) {
		super(name, age);
		this.education = education;
		this.grade = grade;
	}
	
	public String getEducation() {
		return education;
	}

	public int getGrade() {
		return grade;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void printInfo() {
		super.printInfo();
 	    System.out.println("Education: " + education);
 	    System.out.println("Grade: " + grade);
	}
	
}
