import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CreateLoans {
	public static void main(String[] args) {
		ArrayList<Object> loans = new ArrayList<Object>();
		JFrame frame = new JFrame();
		JTextArea ta = new JTextArea(10, 30);
		JScrollPane scroll = new JScrollPane(ta);
		int loan_number;
		int loan_amount;
		String loan_type;
		String term;
		String customer_last_name;
		int cur_prime_rate;

		frame.setTitle("Loan List");
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ta.setEditable(false);
		ta.setFont(new Font("Calibri", Font.PLAIN, 16));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JOptionPane
				.showMessageDialog(
						null,
						"This program will display loan information "
								+ "base on the information you inputted.\nPress OK to Start Entering information.");
		cur_prime_rate = Integer.parseInt(JOptionPane
				.showInputDialog("Enter Current Prime Interest Rate:"));

		for (int i = 0; i < 5; i++) {
			JOptionPane.showMessageDialog(null,
					"Enter Loan Information For Account " + (i + 1)
							+ "\nPress OK to continue.");
			do {
				loan_type = JOptionPane.showInputDialog("Account " + (i + 1)
						+ "\nEnter Loan Type: Business or Personal");
			} while (!(loan_type.equalsIgnoreCase("business")
					|| loan_type.equalsIgnoreCase("businessloan")
					|| loan_type.equalsIgnoreCase("personal") || loan_type
						.equalsIgnoreCase("personalloan")));
			customer_last_name = JOptionPane
					.showInputDialog("Account " + (i + 1)
							+ "\nEnter Last Name:");
			loan_number = Integer.parseInt(JOptionPane
					.showInputDialog("Account " + (i + 1)
							+ "\nEnter Loan Number:"));
			loan_amount = Integer.parseInt(JOptionPane
					.showInputDialog("Account " + (i + 1)
							+ "\nEnter Loan Amount:"));
			term = JOptionPane
					.showInputDialog("Account " + (i + 1)
							+ "\nEnter Short, Medium or Long Term:");

			if (loan_type.equalsIgnoreCase("business")
					|| loan_type.equalsIgnoreCase("businessloan")) {
				loans.add(new BusinessLoan(cur_prime_rate, loan_number,
						loan_amount, term, customer_last_name));
			} else if (loan_type.equalsIgnoreCase("personal")
					|| loan_type.equalsIgnoreCase("personalloan")) {
				loans.add(new PersonalLoan(cur_prime_rate, loan_number,
						loan_amount, term, customer_last_name));
			}

		}

		for (int i = 0; i < 5; i++) {
			ta.append(loans.get(i).toString());
			ta.append("\n--------------------\n");
		}
		frame.add(scroll);
		frame.pack();
		frame.setVisible(true);
	}
}
