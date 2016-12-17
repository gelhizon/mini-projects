import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BankMemberTransactionPanel extends JPanel {
	private JTextField dateJoinedTextField, firstNameTextField, lastNameTextField, balanceTextField;
	private JButton withdrawButton, depositButton;
	private final String[] columnNames = { "Date", "Withdraw", "Deposit", "Balance" };
	private Object[][] data;
	private DefaultTableModel tableModel;
	private JTable historyTable;
	private JScrollPane scrollPane;
	private BankMemberData memberData;

	public BankMemberTransactionPanel(final BankMemberData memberData, final BankFileManager membersFileManager) {
		super(new GridBagLayout());
		setOpaque(false);
		this.memberData = memberData;

		data = new Object[memberData.getHistoryCounter()][];
		for (int i = 0; i < memberData.getHistoryCounter(); i++) {
			data[i] = new Object[] { new SimpleDateFormat("EEE, d MMM yyyy h:mm a").format(memberData.getDateList().get(i)), memberData.getWithdrawList().get(i), memberData.getDepositList().get(i), memberData.getBalanceList().get(i) };
		}

		// INITIALIZE COMPONENTS
		dateJoinedTextField = new JTextField(new SimpleDateFormat("MMMMMMMMM d, yyyy").format(memberData.getDateList().get(0)));
		firstNameTextField = new JTextField(memberData.getFirstName(), 20);
		lastNameTextField = new JTextField(memberData.getLastName(), 20);
		balanceTextField = new JTextField(memberData.getBalanceList().get(memberData.getBalanceList().size() - 1).toString(), 20);
		withdrawButton = new JButton("Withdraw");
		depositButton = new JButton("Deposit");
		tableModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		historyTable = new JTable(tableModel);
		scrollPane = new JScrollPane(historyTable);

		// SET COMPONENT PROPERTIES
		dateJoinedTextField.setEditable(false);
		firstNameTextField.setEditable(false);
		lastNameTextField.setEditable(false);
		balanceTextField.setEditable(false);

		// LISTENERS
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String amountString = JOptionPane.showInputDialog(null, "Amount", "Withdraw", JOptionPane.OK_CANCEL_OPTION);
				Double amountDouble = Double.parseDouble(amountString);
				if (memberData.getBalanceList().get(memberData.getHistoryCounter() - 1) - amountDouble >= 0) {
					Double balance = memberData.getBalanceList().get(memberData.getHistoryCounter() - 1) - amountDouble;
					memberData.getWithdrawList().add(amountDouble);
					memberData.getBalanceList().add(balance);
					memberData.getDepositList().add(0.0);
					memberData.getDateList().add(new Date());
					memberData.addHistoryCounter();
					membersFileManager.save();
					// Update Table
					int row = memberData.getHistoryCounter() - 1;
					tableModel.addRow(new Object[] { new SimpleDateFormat("EEE, d MMM yyyy h:mm a").format(memberData.getDateList().get(row)), memberData.getWithdrawList().get(row), memberData.getDepositList().get(row), memberData.getBalanceList().get(row) });
					balanceTextField.setText(String.valueOf(memberData.getBalanceList().get(row)));
				} else {
					JOptionPane.showMessageDialog(null, "Insufficient Balance");
				}
			}
		});
		depositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String amountString = JOptionPane.showInputDialog(null, "Amount", "Deposit", JOptionPane.OK_CANCEL_OPTION);
				Double amountDouble = Double.parseDouble(amountString);
				Double balance = memberData.getBalanceList().get(memberData.getHistoryCounter() - 1) + amountDouble;
				memberData.getWithdrawList().add(0.0);
				memberData.getBalanceList().add(balance);
				memberData.getDepositList().add(amountDouble);
				memberData.getDateList().add(new Date());
				memberData.addHistoryCounter();
				membersFileManager.save();
				// Update Table
				int row = memberData.getHistoryCounter() - 1;
				tableModel.addRow(new Object[] { new SimpleDateFormat("EEE, d MMM yyyy h:mm a").format(memberData.getDateList().get(row)), memberData.getWithdrawList().get(row), memberData.getDepositList().get(row), memberData.getBalanceList().get(row) });
				balanceTextField.setText(String.valueOf(memberData.getBalanceList().get(row)));
			}
		});

		// LAYOUT
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);

		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		JLabel title = new JLabel("Transactions");
		title.setFont(new Font("Impact", Font.PLAIN, 25));
		add(title, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 1;
		add(new JLabel("Date Joined:"), c);
		c.gridx = 1;
		add(dateJoinedTextField, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Last Name:"), c);
		c.gridx = 1;
		add(lastNameTextField, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("First Name:"), c);
		c.gridx = 1;
		add(firstNameTextField, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Balance:"), c);
		c.gridx = 1;
		add(balanceTextField, c);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		add(scrollPane, c);
		c.gridy++;
		c.gridx = 0;
		c.weighty = 0;
		c.weightx = 0;
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.add(withdrawButton);
		buttons.add(depositButton);
		add(buttons, c);

	}

	public BankMemberData getMemberData() {
		return memberData;
	}
}
