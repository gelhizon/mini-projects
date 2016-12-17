package gui;

public class SortFormatter {
	private boolean symbolized;
	
	public String formatArray(int[] numbers) {
		StringBuilder myFormat = new StringBuilder();
		
		for (int i = 0; i < numbers.length; i++) {
			myFormat.append(numbers[i] + ",\t");
		}
		
		
		if(symbolized){
			for (int i = 0; i < numbers.length; i++){
				myFormat.append("\n");
				for (int j = 0; j < numbers[i]; j++){
					myFormat.append("*");
				}
			}
		}
		
		return myFormat.toString();
	}
	
	public void setSymbolized(boolean symbolized){
		this.symbolized = symbolized;
	}
	
}