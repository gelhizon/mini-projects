import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Client implements Serializable {
	private String roomNumber;
	private Integer price;
	private Integer numberOfStay;
	private Calendar dateBooked;
	private ArrayList<Person> persons;

	public Client(String roomNumber, Integer price, Integer numberOfStay,ArrayList<Person> persons) {
		super();
		this.roomNumber = roomNumber;
		this.price = price;
		this.numberOfStay = numberOfStay;
		this.dateBooked = GregorianCalendar.getInstance();
		this.persons = persons;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNumberOfStay() {
		return numberOfStay;
	}

	public void setNumberOfStay(Integer numberOfStay) {
		this.numberOfStay = numberOfStay;
	}

	public Calendar getDateBooked() {
		return dateBooked;
	}

	public void setDateBooked(Calendar dateBooked) {
		this.dateBooked = dateBooked;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

}
