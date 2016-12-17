public class IllicitCarDealers{
	private int carModel;
	private int rewardMoney;
	private double salesAmount;

	public IllicitCarDealers(){
		carModel = 0;
		rewardMoney = 0;
		salesAmount = 0;
	}

	public void setModel(int carModel){
		this.carModel = carModel;
	}

	public void setSalesAmount(double salesAmount){
		this.salesAmount = salesAmount;
	}

	public double getReward(){
		double salesAmountPercent;
		switch (carModel){
			case 1:
				salesAmountPercent = salesAmount * .10;
				if(salesAmountPercent > 20000){
					return salesAmountPercent;
				}else{
					return 20000;
				}
			case 2:
				salesAmountPercent = salesAmount * .12;
				if(salesAmountPercent < 15000){
					return salesAmountPercent;
				}else{
					return 15000;
				}
			case 3:
				return salesAmount * .30;
			case 4:
				return 50000;
			case 5:
				return 10000;
			default:
				return 0;
		}
	}
}