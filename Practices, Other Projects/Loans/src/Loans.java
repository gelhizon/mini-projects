public  class Loans implements LoansConstants {
	private int loan_number;
	private int loan_amount;
	public double interest_rate;
	private int term;
	private String customer_last_name;

	Loans(int loan_number, int loan_amount, String term,
			String customer_last_name) {
		this.loan_number = loan_number;
		this.customer_last_name = customer_last_name;

		if (term.equalsIgnoreCase("shortterm")
				|| term.equalsIgnoreCase("short term")) {
			this.term = SHORT_TERM;
		} else if (term.equalsIgnoreCase("mediumterm")
				|| term.equalsIgnoreCase("medium term")) {
			this.term = MEDIUM_TERM;
		} else if (term.equalsIgnoreCase("longterm")
				|| term.equalsIgnoreCase("long term")) {
			this.term = LONG_TERM;
		} else {
			this.term = SHORT_TERM;
		}

		if (loan_amount > 100000) {
			this.loan_amount = 100000;
		} else {
			this.loan_amount = loan_amount;
		}
	}

	public String toString() {
		return COMPANY + "\n" + customer_last_name + "\nLoan Number: "
				+ loan_number + "\nLoad Amount: " + loan_amount
				+ "\nLoan Interest: " + interest_rate + " Percent\nTerm: "
				+ term + " year";
	}
}
