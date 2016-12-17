public class Taxpayer{

	int yearlyGross;
	int ssNumber;

	public Taxpayer(int yearlyGross, int ssNumber){
		this.yearlyGross = yearlyGross;
		this.ssNumber = ssNumber;
	}

	public int getYearlyGross(){
		return yearlyGross;
	}

	public int getSSNumber(){
		return ssNumber;
	}
}