import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankMemberAddPanel extends JPanel {
	private JTextField accountNumber, firstName, lastName, middleName, address, initialDeposit;
	private JButton addButton, cancelButton;
	private JComboBox<Integer> birthDateDay, birthDateYear;
	private JComboBox<String> birthDateMonth;
	private JComboBox<String> gender;
	private final String ZEROSPADDING = "0000";

	public BankMemberAddPanel(final BankControlListener listener, final BankMembersListListener updater, final BankFileManager membersFileManager) {
		super(new GridBagLayout());
		setOpaque(false);

		// COMPONENTS
		accountNumber = new JTextField(20);
		firstName = new JTextField(20);
		lastName = new JTextField(20);
		middleName = new JTextField(20);
		address = new JTextField(20);
		initialDeposit = new JTextField(20);
		birthDateDay = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 });
		birthDateMonth = new JComboBox<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", });
		birthDateYear = new JComboBox<>(new Integer[] { 2013, 2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001, 2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980, 1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 1971, 1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961, 1960, 1959, 1958, 1957, 1956, 1955, 1954, 1953, 1952, 1951, 1950, 1949, 1948, 1947, 1946, 1945, 1944, 1943, 1942, 1941, 1940, 1939, 1938, 1937, 1936, 1935, 1934, 1933, 1932, 1931, 1930, 1929, 1928, 1927, 1926, 1925, 1924, 1923, 1922, 1921, 1920, 1919, 1918, 1917, 1916, 1915, 1914, 1913, 1912, 1911, 1910, 1909, 1908, 1907, 1906, 1905, 1904, 1903, 1902, 1901, 1900 });
		gender = new JComboBox<>(new String[] { "Male", "Female" });
		addButton = new JButton("Add");
		cancelButton = new JButton("Cancel");

		// SET COMPONENTS PROPERTIES
		accountNumber.setEditable(false);
		ArrayList<BankMemberData> list = membersFileManager.getList();
		if (list.isEmpty())
			accountNumber.setText(new SimpleDateFormat("yyyyMM").format(new Date()) + ZEROSPADDING);
		else {
			accountNumber.setText(String.valueOf(list.get(list.size() - 1).getAccountNumber() + 1));
		}

		// LISTENERS
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BankMemberData memberData = new BankMemberData(Integer.parseInt(accountNumber.getText()), firstName.getText(), lastName.getText(), middleName.getText(), (String) gender.getSelectedItem(), address.getText(), (Integer) birthDateDay.getSelectedItem(), (String) birthDateMonth.getSelectedItem(), (Integer) birthDateYear.getSelectedItem(), Double.parseDouble(initialDeposit.getText()));
				membersFileManager.add(memberData);
				JOptionPane.showMessageDialog(null, "Add Succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				updater.updateTable(memberData);
				accountNumber.setText(String.valueOf(Integer.parseInt(accountNumber.getText()) + 1));
				resetFields();

				listener.removeMemberAddPanel();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				resetFields();
				listener.removeMemberAddPanel();
			}
		});

		// BirthDay Validation
		birthDateMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				validateDay();
			}
		});
		birthDateYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				validateDay();
			}
		});

		// PROPERTIES
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		JLabel title = new JLabel("Add a Member");
		title.setFont(new Font("Impact", Font.PLAIN, 25));
		add(title, c);
		c.gridy++;
		c.weighty = .3;
		add(Box.createGlue(), c);
		c.weighty = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 1;
		add(new JLabel("Acc. Number:"), c);
		c.gridx = 1;
		add(accountNumber, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("First Name:"), c);
		c.gridx = 1;
		add(firstName, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Last Name:"), c);
		c.gridx = 1;
		add(lastName, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Middle Name:"), c);
		c.gridx = 1;
		add(middleName, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Date of Birth:"), c);
		c.gridx = 1;
		JPanel bdayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		bdayPanel.add(birthDateMonth);
		bdayPanel.add(birthDateDay);
		bdayPanel.add(birthDateYear);
		add(bdayPanel, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Gender:"), c);
		c.gridx = 1;
		add(gender, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Address:"), c);
		c.gridx = 1;
		add(address, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Initial Deposit"), c);
		c.gridx = 1;
		add(initialDeposit, c);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		buttonsPanel.add(addButton);
		buttonsPanel.add(cancelButton);
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridy++;
		c.gridx = 0;
		add(buttonsPanel, c);
		c.gridy++;
		c.weighty = 1;
		add(Box.createGlue(), c);

		setVisible(true);
	}

	public void resetFields() {
		firstName.setText("");
		lastName.setText("");
		middleName.setText("");
		address.setText("");
		initialDeposit.setText("");
		birthDateDay.setSelectedIndex(0);
		birthDateMonth.setSelectedIndex(0);
		birthDateYear.setSelectedIndex(0);
		gender.setSelectedIndex(0);
	}

	private void validateDay() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.MONTH, birthDateMonth.getSelectedIndex());
		cal.set(Calendar.YEAR, 2013 - birthDateYear.getSelectedIndex());
		int numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		while (numDays < birthDateDay.getItemCount()) {
			birthDateDay.removeItemAt(birthDateDay.getItemCount() - 1);
			System.out.println("REMOVE DAY");
		}

		while (numDays > birthDateDay.getItemCount()) {
			birthDateDay.addItem(birthDateDay.getItemCount() + 1);
			System.out.println("ADD DAY");
		}
	}
}
