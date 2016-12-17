import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudentCreatorFrame extends JFrame {
	public JTextField studno, firstName, lastName, middleName, address, contactNumber;
	public JComboBox gender;
	public JButton submit, studentsList, close;

	StudentCreatorFrame() {
		super("Add Students");
		setSize(800, 600);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		String counter = "0001";
		try {
			ArrayList<Object[]> list = new ArrayList();
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("students.dat"));
			list.addAll((ArrayList) input.readObject());
			if (!list.isEmpty()) {
				String studno = (String) list.get(list.size() - 1)[0];
				String counterString = studno.substring(6);
				counter = String.valueOf(Integer.parseInt(counterString) + 1);
				while (counter.length() < 4) {
					counter = "0" + counter;
				}
			}
			input.close();
		} catch (Exception e) {

		}

		studno = new JTextField(10);
		studno.setEditable(false);
		studno.setText(new SimpleDateFormat("yyyyMM").format(new Date()) + counter);
		firstName = new JTextField(10);
		lastName = new JTextField(10);
		middleName = new JTextField(10);
		address = new JTextField(10);
		contactNumber = new JTextField(10);
		gender = new JComboBox(new String[] { "Male", "Female" });
		submit = new JButton("Add Student");
		studentsList = new JButton("Students List");
		close = new JButton("Close");

		GridBagConstraints gs = new GridBagConstraints();
		gs.insets = new Insets(3, 6, 3, 6);
		gs.gridy = 0;
		gs.gridx = 0;
		gs.gridwidth = 2;
		JLabel label = new JLabel("Students Registration");
		label.setFont(new Font("Impact", Font.PLAIN, 21));
		add(label, gs);
		gs.gridwidth = 1;
		gs.gridy = 1;
		gs.gridx = 0;
		add(new JLabel("Student Number:"), gs);
		gs.gridx = 1;
		add(studno, gs);

		gs.gridy = 2;
		gs.gridx = 0;
		add(new JLabel("First Name:"), gs);
		gs.gridx = 1;
		add(firstName, gs);

		gs.gridy = 3;
		gs.gridx = 0;
		add(new JLabel("Last Name:"), gs);
		gs.gridx = 1;
		add(lastName, gs);

		gs.gridy = 4;
		gs.gridx = 0;
		add(new JLabel("Middle Name:"), gs);
		gs.gridx = 1;
		add(middleName, gs);

		gs.gridy = 5;
		gs.gridx = 0;
		add(new JLabel("Gender:"), gs);
		gs.gridx = 1;
		add(gender, gs);

		gs.gridy = 6;
		gs.gridx = 0;
		add(new JLabel("Address:"), gs);
		gs.gridx = 1;
		add(address, gs);

		gs.gridy = 7;
		gs.gridx = 0;
		add(new JLabel("Contact Number:"), gs);
		gs.gridx = 1;
		add(contactNumber, gs);

		gs.gridy = 8;
		gs.gridx = 0;
		gs.gridwidth = 2;
		JPanel buttons = new JPanel();
		buttons.add(submit);
		buttons.add(studentsList);
		add(buttons, gs);
		gs.gridy = 9;
		add(close, gs);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList list = new ArrayList();
				try {
					ObjectInputStream input = new ObjectInputStream(new FileInputStream("students.dat"));
					list.addAll((ArrayList) input.readObject());
					input.close();
				} catch (Exception e1) {

				}
				list.add(new Object[] { studno.getText(), firstName.getText(), lastName.getText(), middleName.getText(), gender.getSelectedItem(), address.getText(), contactNumber.getText() });
				try {
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("students.dat"));
					output.writeObject(list);
					output.close();
				} catch (Exception ex) {

				}
				JOptionPane.showMessageDialog(null, "Registration Succesful");
			}
		});
		studentsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentsListFrame list = new StudentsListFrame();
			}
		});
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);

	}

	public static void main(String[] args) {
		new StudentCreatorFrame();
	}
}
