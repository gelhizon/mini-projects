package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LibraryBorrowersPanel extends JPanel {
	private JTextField studnoTextField, firstNameTextField, lastNameTextField, middleNameTextField, addressTextField, contactTextField, bookNumberTextField, titleTextField, authorTextField;
	private JComboBox timeBorrowDropDown;
	private JButton studentsSelectionButton, addStudentsButton, booksSelectionButton, addBooksButton, submitButton;

	public LibraryBorrowersPanel() {
		super(new GridBagLayout());

		// Instantiate Components
		studnoTextField = new JTextField(10);
		firstNameTextField = new JTextField(10);
		addressTextField = new JTextField(10);
		contactTextField = new JTextField(10);
		bookNumberTextField = new JTextField(10);
		titleTextField = new JTextField(10);
		authorTextField = new JTextField(10);
		timeBorrowDropDown = new JComboBox(new String[] { "1 Hour", "2 Hours", "3 Hours", "4 Hours", "5 Hours", "6 Hours", "7 Hours", "8 Hours", "9 Hours", "10 Hours", "11 Hours", "12 Hours", "13 Hours", "14 Hours", "15 Hours", "16 Hours", "17 Hours", "18 Hours", "19 Hours", "20 Hours", "21 Hours", "22 Hours", "23 Hours", "24 Hours" });
		studentsSelectionButton = new JButton("Select Student");
		addStudentsButton = new JButton("+");
		booksSelectionButton = new JButton("Available Books");
		addBooksButton = new JButton("+");
		submitButton = new JButton("Borrow");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 4, 2, 4);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		JLabel studentInfoLabel = new JLabel("Student Information", SwingConstants.CENTER);
		studentInfoLabel.setFont(new Font("Consolas", Font.PLAIN, 23));
		add(studentInfoLabel, gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		JPanel studentsSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		studentsSelectionPanel.add(studentsSelectionButton);
		studentsSelectionPanel.add(addStudentsButton);
		add(studentsSelectionPanel, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(new JLabel("Student#:"), gbc);
		gbc.gridx = 1;
		add(studnoTextField, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(new JLabel("First Name:"), gbc);
		gbc.gridx = 1;
		add(firstNameTextField, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(new JLabel("Last Name:"), gbc);
		gbc.gridx = 1;
		add(lastNameTextField, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(new JLabel("Middle Name:"), gbc);
		gbc.gridx = 1;
		add(middleNameTextField, gbc);
		gbc.gridy = 6;
		gbc.gridx = 0;
		add(new JLabel("Address:"), gbc);
		gbc.gridx = 1;
		add(addressTextField, gbc);
		gbc.gridy = 7;
		gbc.gridx = 0;
		add(new JLabel("Contact#:"), gbc);
		gbc.gridx = 1;
		add(contactTextField, gbc);
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		JLabel bookInfoLabel = new JLabel("Book Information", SwingConstants.CENTER);
		bookInfoLabel.setFont(new Font("Consolas", Font.PLAIN, 23));
		add(bookInfoLabel, gbc);
		gbc.gridy = 9;
		JPanel booksSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		booksSelectionPanel.add(booksSelectionButton);
		booksSelectionPanel.add(addBooksButton);
		add(booksSelectionPanel, gbc);
		gbc.gridy = 10;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(new JLabel("Book#:"), gbc);
		gbc.gridx = 1;
		add(bookNumberTextField, gbc);
		gbc.gridy = 11;
		gbc.gridx = 0;
		add(new JLabel("Title"), gbc);
		gbc.gridx = 1;
		add(titleTextField, gbc);
		gbc.gridy = 12;
		gbc.gridx = 0;
		add(new JLabel("Author"), gbc);
		gbc.gridx = 1;
		add(authorTextField, gbc);
		gbc.gridy = 13;
		gbc.gridx = 0;
		add(new JLabel("Time Expired:"), gbc);
		gbc.gridx = 1;
		add(timeBorrowDropDown, gbc);
		gbc.gridy = 14;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		add(submitButton, gbc);
	}

	public JTextField getStudnoTextField() {
		return studnoTextField;
	}

	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public JTextField getLastNameTextField() {
		return lastNameTextField;
	}

	public JTextField getMiddleNameTextField() {
		return middleNameTextField;
	}

	public JTextField getAddressTextField() {
		return addressTextField;
	}

	public JTextField getContactTextField() {
		return contactTextField;
	}

	public JTextField getBookNumberTextField() {
		return bookNumberTextField;
	}

	public JTextField getTitleTextField() {
		return titleTextField;
	}

	public JTextField getAuthorTextField() {
		return authorTextField;
	}

	public JComboBox getTimeBorrowDropDown() {
		return timeBorrowDropDown;
	}

	public JButton getStudentsSelectionButton() {
		return studentsSelectionButton;
	}

	public JButton getAddStudentsButton() {
		return addStudentsButton;
	}

	public JButton getBooksSelectionButton() {
		return booksSelectionButton;
	}

	public JButton getAddBooksButton() {
		return addBooksButton;
	}

	public JButton getSubmitButton() {
		return submitButton;
	}
	
}