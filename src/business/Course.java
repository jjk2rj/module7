package business;

public class Course {
	
	public String name;
	public boolean isSelected = false;
	
	public String getName() {
		return this.name;
	}
	public boolean getIsSelected() {
		return this.isSelected;
	}
	public String toString() {
		return this.name;
	}
}
