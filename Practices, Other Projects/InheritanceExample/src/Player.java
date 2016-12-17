public class Player{
	
	String fname, lname, gender;
	int playerID, age;
	
	public void setPlayerID(int playerID){
		this.playerID = playerID;
	}
	public void setFName(String fname){
		this.fname = fname;
	}
	public void setLName(String lname){
		this.lname = lname;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
	public String getName(){
		return fname + " " + lname;
	}
	public String getFName(){
		return fname;
	}
	public String getLName(){
		return lname;
	}
	public String getGender(){
		return gender;
	}
	public int getAge(){
		return age;
	}
}