public class BusinessLoan extends Loans {

	BusinessLoan(int current_prime_interest_rate, int loan_number,
			int loan_amount, String term, String customer_last_name) {
		super(loan_number, loan_amount, term, customer_last_name);
		interest_rate = current_prime_interest_rate
				+ (current_prime_interest_rate * 0.01);
	}

}
