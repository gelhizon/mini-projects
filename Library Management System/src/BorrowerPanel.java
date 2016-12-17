import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BorrowerPanel extends JPanel {

	private JTextField txtStudNumber, txtName, txtAddress, txtContact, txtBookNumber, txtTitle, txtAuthor;
	private JButton btnStudentsSelection, btnAddStudents, btnBooksSelection, btnAddBooks, btnBorrow;

	public BorrowerPanel() {
		// Setup Panel
		super(new GridBagLayout());

		// Instantiate Components
		JLabel studinfoLabel = new JLabel("Student Information", SwingConstants.CENTER);
		JLabel studnoLabel = new JLabel("Student#:");
		JLabel nameLabel = new JLabel("Name:");
		JLabel addressLabel = new JLabel("Address:");
		JLabel contactLabel = new JLabel("Contact#:");
		JLabel bookinfoLabel = new JLabel("Book Information", SwingConstants.CENTER);
		JLabel booknumberLabel = new JLabel("Book#:");
		JLabel titleLabel = new JLabel("Title");
		JLabel authorLabel = new JLabel("Author");
		JLabel timeBorrowLabel = new JLabel("Date Due:");
		txtStudNumber = new JTextField(2);
		txtName = new JTextField(20);
		txtAddress = new JTextField(20);
		txtContact = new JTextField(20);
		txtBookNumber = new JTextField(20);
		txtTitle = new JTextField(20);
		txtAuthor = new JTextField(20);
		btnStudentsSelection = new JButton("Select Student");
		btnAddStudents = new JButton("+");
		btnBooksSelection = new JButton("Available Books");
		btnAddBooks = new JButton("+");
		btnBorrow = new JButton("Borrow");

		// Set Component Properties
		studinfoLabel.setFont(new Font("Consolas", Font.PLAIN, 23));
		bookinfoLabel.setFont(new Font("Consolas", Font.PLAIN, 23));
		txtName.setEditable(false);
		txtAddress.setEditable(false);
		txtContact.setEditable(false);
		txtTitle.setEditable(false);
		txtAuthor.setEditable(false);

		// Add Components
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 4, 2, 4);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		add(studinfoLabel, gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		{
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			panel.add(btnStudentsSelection);
			panel.add(btnAddStudents);
			add(panel, gbc);
		}
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(studnoLabel, gbc);
		gbc.gridx = 1;
		add(txtStudNumber, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(nameLabel, gbc);
		gbc.gridx = 1;
		add(txtName, gbc);
		gbc.gridy = 4;
		gbc.gridx = 0;
		add(addressLabel, gbc);
		gbc.gridx = 1;
		add(txtAddress, gbc);
		gbc.gridy = 5;
		gbc.gridx = 0;
		add(contactLabel, gbc);
		gbc.gridx = 1;
		add(txtContact, gbc);
		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		add(bookinfoLabel, gbc);
		gbc.gridy = 7;
		{
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			panel.add(btnBooksSelection);
			panel.add(btnAddBooks);
			add(panel, gbc);
		}
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(booknumberLabel, gbc);
		gbc.gridx = 1;
		add(txtBookNumber, gbc);
		gbc.gridy = 9;
		gbc.gridx = 0;
		add(titleLabel, gbc);
		gbc.gridx = 1;
		add(txtTitle, gbc);
		gbc.gridy = 10;
		gbc.gridx = 0;
		add(authorLabel, gbc);
		gbc.gridx = 1;
		add(txtAuthor, gbc);
		gbc.gridy = 11;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		add(btnBorrow, gbc);
	}

	public JTextField getTxtStudentNumber() {
		return txtStudNumber;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public JTextField getTxtContact() {
		return txtContact;
	}

	public JTextField getTxtBookNumber() {
		return txtBookNumber;
	}

	public JTextField getTxtTitle() {
		return txtTitle;
	}

	public JTextField getTxtAuthor() {
		return txtAuthor;
	}

	public JButton getBtnStudentsSelection() {
		return btnStudentsSelection;
	}

	public JButton getBtnAddStudents() {
		return btnAddStudents;
	}

	public JButton getBtnBooksSelection() {
		return btnBooksSelection;
	}

	public JButton getBtnAddBooks() {
		return btnAddBooks;
	}

	public JButton getBtnBorrow() {
		return btnBorrow;
	}

	
}
