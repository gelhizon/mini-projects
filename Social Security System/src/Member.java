import java.io.Serializable;

public class Member implements Serializable {
	public String SSNumber, firstName, middleName, lastName, sex, civilstatus,address, email, father, mother, spouse, children[];
	public int postalCode, birthdateDay, birthDateMonth, birthDateYear;

	public Member() {
		SSNumber = "";
		firstName = "";
		middleName = "";
		lastName = "";
		sex = "";
		civilstatus = "";
		address = "";
		postalCode = 0;
		email = "";
		birthdateDay = 0;
		birthDateMonth = 0;
		birthDateYear = 0;
		father = "";
		mother = "";
		spouse = "";
		children = null;
	}
	
}
