public class CollegeCourse{
	private String courseID;
	private int creditHours;
	private char letterGrade;

	public void setCourseID(String courseID){
		this.courseID = courseID;
	}

	public int getCourseID(){
		return courseID;
	}

	public void setCreditHours(int creditHours){
		this.creditHours = creditHours;
	}

	public int getCreditHours(){
		return credithours;
	}

	public void setLetterGrade(char letterGrade){
		this.letterGrade = letterGrade;
	}

	public char getLetterGrade(){
		return letterGrade;
	}
}