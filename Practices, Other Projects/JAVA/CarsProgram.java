public class CarsProgram{
	public static void main(String[] args){
		IllicitCarDealers cardealer = new IllicitCarDealers();
		cardealer.setModel(2);
		cardealer.setSalesAmount(1000);

		System.out.println(cardealer.getReward());
	}
}