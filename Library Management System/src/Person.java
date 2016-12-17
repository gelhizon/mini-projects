import java.io.Serializable;

public class Person implements Serializable{
	private String firstName;
	private String lastName;
	private String middleName;
	private String gender;
	private String dateOfBirth;
	private String address;
	private String contactNumber;

	public Person(String firstName, String lastName, String middleName, String gender, String dateOfBirth, String address, String contactNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.contactNumber = contactNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDataOfBirth() {
		return dateOfBirth;
	}

	public void setDataOfBirth(String dataOfBirth) {
		this.dateOfBirth = dataOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
