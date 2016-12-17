import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BankMemberData implements Serializable {
	// History (The Passbook)
	private ArrayList<Double> depositList;
	private ArrayList<Double> withdrawList;
	private ArrayList<Double> balanceList;
	private ArrayList<Date> dateList;
	private Integer historyCounter;

	// Member/Personal Informations
	private Integer accountNumber;
	private String firstName;
	private String lastName;
	private String middleName;
	private String gender;
	private String address;
	private Integer birthDateDay;
	private String birthDateMonth;
	private Integer birthDateYear;

	// Constructor
	public BankMemberData(Integer accountNumber, String firstName, String lastName, String middleName, String gender, String address, Integer birthDateDay, String birthDateMonth, Integer birthDateYear, Double initialDeposit) {
		super();
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.gender = gender;
		this.address = address;
		this.birthDateDay = birthDateDay;
		this.birthDateMonth = birthDateMonth;
		this.birthDateYear = birthDateYear;
		depositList = new ArrayList<>();
		depositList.add(initialDeposit);
		withdrawList = new ArrayList<>();
		withdrawList.add(0.0);
		balanceList = new ArrayList<>();
		balanceList.add(initialDeposit);
		dateList = new ArrayList<>();
		dateList.add(new Date());
		historyCounter = 1;
	}

	public ArrayList<Double> getDepositList() {
		return depositList;
	}

	public void setDepositList(ArrayList<Double> depositList) {
		this.depositList = depositList;
	}

	public ArrayList<Double> getWithdrawList() {
		return withdrawList;
	}

	public void setWithdrawList(ArrayList<Double> withdrawList) {
		this.withdrawList = withdrawList;
	}

	public ArrayList<Double> getBalanceList() {
		return balanceList;
	}

	public void setBalanceList(ArrayList<Double> balanceList) {
		this.balanceList = balanceList;
	}

	public ArrayList<Date> getDateList() {
		return dateList;
	}

	public void setDateList(ArrayList<Date> dateList) {
		this.dateList = dateList;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBirthDateDay() {
		return birthDateDay;
	}

	public void setBirthDateDay(Integer birthDateDay) {
		this.birthDateDay = birthDateDay;
	}

	public String getBirthDateMonth() {
		return birthDateMonth;
	}

	public void setBirthDateMonth(String birthDateMonth) {
		this.birthDateMonth = birthDateMonth;
	}

	public Integer getBirthDateYear() {
		return birthDateYear;
	}

	public void setBirthDateYear(Integer birthDateYear) {
		this.birthDateYear = birthDateYear;
	}

	public Integer getHistoryCounter() {
		return historyCounter;
	}
	
	public void addHistoryCounter(){
		historyCounter++;
	}

	public void resetHistoryCounter() {
		historyCounter = 1;
	}


}