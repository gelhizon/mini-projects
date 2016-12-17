public class UseTaxPayer2{
	public static void main(String[]args){
		Taxpayer[] tp = new Taxpayer[10]; 
		for(int i = 0; i < tp.length; i++){
			tp[i] = new Taxpayer(10000 * (i + 1), i + 1);
		}

		for(int i = 0; i < tp.length; i++){
			System.out.println(tp[i].getSSNumber() + " " + tp[i].getYearlyGross()); 
		}
	}
}