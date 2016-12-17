import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BankMembersListPanel extends JLabel implements BankMembersListListener {
	private final String[] columnNames = { "Acc Number", "First Name", "Last Name", "Middle Name", "Address", "Date of Birth" };
	private Object[][] data;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton deleteButton, transactions;
	private JScrollPane scrollPane;

	public BankMembersListPanel(final BankControlListener listener, final BankFileManager membersFileManager) {
		super(new ImageIcon("polybank.png"));

		// COMPONENTS
		ArrayList<BankMemberData> list = membersFileManager.getList();
		data = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			BankMemberData row = list.get(i);
			data[i] = new Object[] { row.getAccountNumber(), row.getFirstName(), row.getLastName(), row.getMiddleName(), row.getAddress(), row.getBirthDateMonth() + " " + row.getBirthDateDay() + ", " + row.getBirthDateYear() };
		}
		tableModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(table);
		deleteButton = new JButton("Delete Selected");
		transactions = new JButton("Transactions");

		// LISTENERS
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ListSelectionModel rowSM = table.getSelectionModel();
				if (rowSM.isSelectionEmpty()) {
					System.out.println("No rows are selected.");
				} else {
					int selectedRow = rowSM.getMinSelectionIndex();
					int option = JOptionPane.showConfirmDialog(null, "Delete selected member?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (option == JOptionPane.YES_OPTION) {

						membersFileManager.remove(selectedRow);
						tableModel.removeRow(selectedRow);
					}
				}
			}
		});
		transactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ListSelectionModel rowSM = table.getSelectionModel();
				if (rowSM.isSelectionEmpty()) {
					System.out.println("No rows are selected.");
				} else {
					int selectedRow = rowSM.getMinSelectionIndex();
					listener.inputMemberIndexTransaction(selectedRow);
				}
			}
		});

		// LAYOUT
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.add(deleteButton);
		buttons.add(transactions);
		add(buttons, c);

		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		add(scrollPane, c);

	}

	public void updateTable(BankMemberData memberData) {
		tableModel.addRow(new Object[] { memberData.getAccountNumber(), memberData.getFirstName(), memberData.getLastName(), memberData.getMiddleName(), memberData.getAddress(), memberData.getBirthDateMonth() + " " + memberData.getBirthDateDay() + ", " + memberData.getBirthDateYear() });
	}
}