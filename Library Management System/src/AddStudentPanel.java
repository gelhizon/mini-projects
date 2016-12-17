import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddStudentPanel extends JDialog {
	private JTextField txtStudentNumber;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtMiddleName;
	private JTextField txtGender;
	private JTextField txtDateOfBirth;
	private JTextField txtAddress;
	private JTextField txtContactNumber;
	private JButton btnAddStudent;

	public AddStudentPanel(JFrame c) {
		super(c, true);
		setLayout(new GridBagLayout());
		setSize(800, 600);
		setLocationRelativeTo(c);

		// Instantiate Components
		txtStudentNumber = new JTextField(20);
		txtFirstName = new JTextField(20);
		txtLastName = new JTextField(20);
		txtMiddleName = new JTextField(20);
		txtGender = new JTextField(20);
		txtDateOfBirth = new JTextField(20);
		txtAddress = new JTextField(20);
		txtContactNumber = new JTextField(20);
		btnAddStudent = new JButton("Add Student");

		// Add Components
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 4, 2, 4);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		JLabel lblAddStudents = new JLabel("Add Students", SwingConstants.CENTER);
	//	lblAddStudents.setFont(new Font("Consolas", Font.PLAIN, 23));
		add(lblAddStudents, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(new JLabel("Student Number:"), gbc);
		gbc.gridx = 1;
		add(txtStudentNumber, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		add(new JLabel("First Name:"), gbc);
		gbc.gridx = 1;
		add(txtFirstName, gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;
		add(new JLabel("Last Name:"), gbc);
		gbc.gridx = 1;
		add(txtLastName, gbc);

		gbc.gridy = 4;
		gbc.gridx = 0;
		add(new JLabel("Middle Name:"), gbc);
		gbc.gridx = 1;
		add(txtMiddleName, gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		add(new JLabel("Gender:"), gbc);
		gbc.gridx = 1;
		add(txtGender, gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		add(new JLabel("Date Of Birth:"), gbc);
		gbc.gridx = 1;
		add(txtDateOfBirth, gbc);

		gbc.gridy = 7;
		gbc.gridx = 0;
		add(new JLabel("Address:"), gbc);
		gbc.gridx = 1;
		add(txtAddress, gbc);

		gbc.gridy = 8;
		gbc.gridx = 0;
		add(new JLabel("Contact Number:"), gbc);
		gbc.gridx = 1;
		add(txtContactNumber, gbc);

		gbc.gridy = 9;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		JPanel buttons = new JPanel();
		buttons.add(btnAddStudent);
		add(buttons, gbc);
	}

	public JTextField getTxtStudentNumber() {
		return txtStudentNumber;
	}

	public JTextField getTxtFirstName() {
		return txtFirstName;
	}

	public JTextField getTxtLastName() {
		return txtLastName;
	}

	public JTextField getTxtMiddleName() {
		return txtMiddleName;
	}

	public JTextField getTxtGender() {
		return txtGender;
	}

	public JTextField getTxtDateOfBirth() {
		return txtDateOfBirth;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public JTextField getTxtContactNumber() {
		return txtContactNumber;
	}

	public JButton getBtnAddStudent() {
		return btnAddStudent;
	}

}
