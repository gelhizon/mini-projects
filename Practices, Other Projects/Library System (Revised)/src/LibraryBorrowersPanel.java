import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LibraryBorrowersPanel extends JPanel {
	private JLabel studinfoLabel, studnoLabel, nameLabel, addressLabel, contactLabel, bookinfoLabel, booknumberLabel, titleLabel, authorLabel, timeBorrowLabel;
	public static JTextField studnoTextField, nameTextField, addressTextField, contactTextField, booknumberTextField, titleTextField, authorTextField;
	private static JComboBox timeBorrowDropDown;
	private JButton studentsSelectionButton, addStudentsButton, booksSelectionButton, addBooksButton, submitButton;
	private BookSelectionDialog bookDialog;

	public LibraryBorrowersPanel() {
		// Setup Panel
		super(new GridBagLayout());

		// Instantiate Components
		studinfoLabel = new JLabel("Student Information", SwingConstants.CENTER);
		studnoLabel = new JLabel("Student#:");
		nameLabel = new JLabel("Name:");
		addressLabel = new JLabel("Address:");
		contactLabel = new JLabel("Contact#:");
		bookinfoLabel = new JLabel("Book Information", SwingConstants.CENTER);
		booknumberLabel = new JLabel("Book#:");
		titleLabel = new JLabel("Title");
		authorLabel = new JLabel("Author");
		timeBorrowLabel = new JLabel("Time Expired:");
		studnoTextField = new JTextField(10);
		nameTextField = new JTextField(10);
		addressTextField = new JTextField(10);
		contactTextField = new JTextField(10);
		booknumberTextField = new JTextField(10);
		titleTextField = new JTextField(10);
		authorTextField = new JTextField(10);
		timeBorrowDropDown = new JComboBox(new String[] { "1 Hour", "2 Hours", "3 Hours", "4 Hours", "5 Hours", "6 Hours", "7 Hours", "8 Hours", "9 Hours", "10 Hours", "11 Hours", "12 Hours", "13 Hours", "14 Hours", "15 Hours", "16 Hours", "17 Hours", "18 Hours", "19 Hours", "20 Hours", "21 Hours", "22 Hours", "23 Hours", "24 Hours" });
		studentsSelectionButton = new JButton("Select Student");
		addStudentsButton = new JButton("+");
		booksSelectionButton = new JButton("Available Books");
		addBooksButton = new JButton("+");
		submitButton = new JButton("Borrow");
		bookDialog = new BookSelectionDialog(SwingUtilities.getWindowAncestor(this), "Select Book", "books.dat");

		// Set Component Properties
		// fonts
		studinfoLabel.setFont(new Font("Consolas", Font.PLAIN, 23));
		bookinfoLabel.setFont(new Font("Consolas", Font.PLAIN, 23));
		// listeners
		studentsSelectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				studentsSelectionDialog();
			}
		});
		booksSelectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				booksSelectionDialog();
			}
		});
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				submit();
			}
		});
		addStudentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// studentAdd
			}
		});
		addBooksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addABook();
			}
		});

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
		JPanel studentsSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		studentsSelectionPanel.add(studentsSelectionButton);
		studentsSelectionPanel.add(addStudentsButton);
		add(studentsSelectionPanel, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(studnoLabel, gbc);
		gbc.gridx = 1;
		add(studnoTextField, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(nameLabel, gbc);
		gbc.gridx = 1;
		add(nameTextField, gbc);
		gbc.gridy = 4;
		gbc.gridx = 0;
		add(addressLabel, gbc);
		gbc.gridx = 1;
		add(addressTextField, gbc);
		gbc.gridy = 5;
		gbc.gridx = 0;
		add(contactLabel, gbc);
		gbc.gridx = 1;
		add(contactTextField, gbc);
		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		add(bookinfoLabel, gbc);
		gbc.gridy = 7;
		JPanel booksSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		booksSelectionPanel.add(booksSelectionButton);
		booksSelectionPanel.add(addBooksButton);
		add(booksSelectionPanel, gbc);
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		add(booknumberLabel, gbc);
		gbc.gridx = 1;
		add(booknumberTextField, gbc);
		gbc.gridy = 9;
		gbc.gridx = 0;
		add(titleLabel, gbc);
		gbc.gridx = 1;
		add(titleTextField, gbc);
		gbc.gridy = 10;
		gbc.gridx = 0;
		add(authorLabel, gbc);
		gbc.gridx = 1;
		add(authorTextField, gbc);
		gbc.gridy = 11;
		gbc.gridx = 0;
		add(timeBorrowLabel, gbc);
		gbc.gridx = 1;
		add(timeBorrowDropDown, gbc);
		gbc.gridy = 12;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		add(submitButton, gbc);
	}

	public void addABook() {
		new LibraryBookAdder(this).setVisible(true);
	}

	public void studentsSelectionDialog() {
		final JDialog studentsSelectDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this));
		studentsSelectDialog.setTitle("Select Student");
		studentsSelectDialog.setSize(300, 400);
		studentsSelectDialog.setLocationRelativeTo(studentsSelectionButton);
		studentsSelectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		studentsSelectDialog.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent event) {
			}

			public void windowLostFocus(WindowEvent event) {
				studentsSelectDialog.dispose();
			}
		});
		studentsSelectDialog.setVisible(true);
	}

	public void booksSelectionDialog() {
		bookDialog.setVisible(true);
	}

	public void submit() {
		if (!studnoTextField.getText().isEmpty() && !nameTextField.getText().isEmpty() && !addressTextField.getText().isEmpty() && !contactTextField.getText().isEmpty() && !booknumberTextField.getText().isEmpty() && !titleTextField.getText().isEmpty() && !authorTextField.getText().isEmpty()) {
			addAndSaveBorrower();
			removeAndSaveBook();
			resetTextFields();
			JOptionPane.showMessageDialog(null, "Borrowed Success", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Borrowed Failed", "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void addAndSaveBorrower() {
		ArrayList list = new ArrayList();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("borrow.dat"));
			list.addAll((ArrayList) input.readObject());
			input.close();
		} catch (Exception e) {
		}
		list.add(new String[] { studnoTextField.getText(), nameTextField.getText(), addressTextField.getText(), contactTextField.getText(), booknumberTextField.getText(), titleTextField.getText(), authorTextField.getText(), (String) timeBorrowDropDown.getSelectedItem() });
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("borrow.dat"));
			output.writeObject(list);
			output.close();
		} catch (Exception e) {
		}
	}

	public void removeAndSaveBook() {
		ArrayList booklist = new ArrayList();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.dat"));
			booklist.addAll((ArrayList) input.readObject());
			input.close();
		} catch (Exception e1) {

		}
		booklist.remove(bookDialog.getSelectedIndex());
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("books.dat"));
			output.writeObject(booklist);
			output.close();
		} catch (Exception ex) {

		}
	}

	public void resetTextFields() {
		studnoTextField.setText("");
		nameTextField.setText("");
		addressTextField.setText("");
		contactTextField.setText("");
		booknumberTextField.setText("");
		titleTextField.setText("");
		authorTextField.setText("");
	}
}
