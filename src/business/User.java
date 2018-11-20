package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class User implements Serializable {

	private String name;
	private String email;
	private String employmentStatus;
	private ArrayList<Course> courses;
	private String[] extras;


	private int courseFee;

	private int totalCost = 0;
	public User() {
		
	}

	public User(String name, String email, ArrayList<Course> courses, String employmentStatus, String[] extras) {
		this.name = name;
		this.email = email;
		this.courses = courses;
		this.employmentStatus = employmentStatus;
		if(extras == null) {
			this.extras = new String[0];
		} else { this.extras = extras; }
		for(String x : this.extras) {
			
			System.out.println(x);
		}

		calcExtras();
		computeCost();
		System.out.println("constructor");
		System.out.println(this.courseFee);
		System.out.println(this.totalCost);

	}

	public User(String name, String email, ArrayList<Course> courses, String employmentStatus) {
		this.name = name;
		this.email = email;
		this.courses = courses;
		this.employmentStatus = employmentStatus;
		
		computeCost();
		System.out.println("constructor");
		System.out.println(this.courseFee);
	}
	
	public void computeCost(){
		computeCoursePrice();
		for(Course course: this.courses) {
			if(course.getIsSelected())
			{
				this.totalCost += this.courseFee;
			}
		}
	}
	
	public void computeCoursePrice(){
		
		System.out.println(this.employmentStatus);
		if(this.employmentStatus.equals("JHU Employee"))
		{
			this.courseFee = 850;
		}
		if(this.employmentStatus.equals("JHU Student"))
		{
			this.courseFee = 1000;
		}
		if(this.employmentStatus.equals("Speaker"))
		{
			this.courseFee = 0;
		}
		if(this.employmentStatus.equals("Other"))
		{
			this.courseFee = 1350;
		}
	}
	
	public void calcExtras(){
		for(String extra : this.extras){
			System.out.println(extra + " hi");
			if(extra.equals("Hotel Accommodation")){
				this.totalCost += 185;
			}
			if(extra.equals("Parking Permit")){
				this.totalCost += 10;
			}
		}
	}
	public int getExtraFee(String extra) {
		if(extra.equals("Hotel Accommodation")){
			return 185;
		}
		if(extra.equals("Parking Permit")){
			return 10;
		}
		return 0;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	public String getCoursesString() {
		String coursesString = "";
		for(Course course: this.courses) {
			coursesString += course.name;
			coursesString += ",";
		}
		return coursesString;
		
	}
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	public int getTotalCost() {
		this.totalCost = 0;
		computeCost();
		return totalCost;
	}

	public int getCourseFee() {
		return courseFee;
	}
	public String[] getExtras() {
		return extras;
	}
	public String getExtrasString() {
		String extrasString = "";
		for(String extra: this.extras) {
			extrasString += extra;
			extrasString += ",";
		}
		return extrasString;
	}

	public void setExtras(String[] extras) {
		this.extras = extras;
	}

	public void removeCourse(String courseToBeRemoved) {
		// TODO Auto-generated method stub
		for(Course course : courses) {
			if(course.getName().equals(courseToBeRemoved)) {
				course.isSelected = false;
			}
		}
		
	}
	public boolean hasHotel() {
		for(String x : this.extras)
		{
			if(x.equals("Hotel Accommodation"))
				return true;
		}
		return false;
	}
	public boolean hasParking() {
		for(String x : this.extras)
		{
			if(x.equals("Parking Permit"))
				return true;
		}
		return false;
	}
	public String printSelectedCourses() {
		String htmlCode = "";
		for(Course course : this.courses) {
			
		}
		return htmlCode;
	}
	public static ArrayList<String> allCourses()
	{
		ArrayList<String> arraylist = new ArrayList<>(Arrays.asList(
				"A0 - Web Services" , 
				"A1 -J2EE Design Patterns",
				"A2 - Service Oriented Architecture",
				"A3 - Enterprise Service Bus",
				"A4 - Secure Messaging",
				"A5 - Web Services Security"));
		return arraylist;
	}
} 
