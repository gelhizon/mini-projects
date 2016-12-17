import java.io.Serializable;

public class UserData implements Serializable {
	private String LastName, FirstName, MiddleName, HomeAddress, AccNo, CellNumber, LandLine, EmailAddress, MeterNo;
	private double kWattUsed;

	public UserData(String lastName, String firstName, String middleName, String homeAddress, String accNo, String cellNumber, String landLine, String emailAddress, String meterNo) {
		super();
		LastName = lastName;
		FirstName = firstName;
		MiddleName = middleName;
		HomeAddress = homeAddress;
		AccNo = accNo;
		CellNumber = cellNumber;
		LandLine = landLine;
		EmailAddress = emailAddress;
		MeterNo = meterNo;
		kWattUsed = 0;
	}
	
	public void setKWattUsed(double d) {
		kWattUsed = d;
	}
	
	public double getKWattUsed() {
		return kWattUsed;
	}

	public String getLastName() {
		return LastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public String getHomeAddress() {
		return HomeAddress;
	}

	public String getAccNo() {
		return AccNo;
	}

	public String getCellNumber() {
		return CellNumber;
	}

	public String getLandLine() {
		return LandLine;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public String getMeterNo() {
		return MeterNo;
	}
	
	
	
}
