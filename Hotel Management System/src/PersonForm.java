import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PersonForm extends JDialog {
	private JTextField firstName, lastName, middleName, address;
	private JComboBox<String> gender;
	private DatePicker dateOfBirth;
	private JButton add;

	public PersonForm(final ClientForm clientForm) {
		super(clientForm, true);
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(640, 480));
		setLocationRelativeTo(clientForm);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// instantiate components
		firstName = new JTextField(20);
		lastName = new JTextField(20);
		middleName = new JTextField(20);
		gender = new JComboBox<String>(new String[] { "Male", "Female" });
		address = new JTextField(20);
		dateOfBirth = new DatePicker();
		add = new JButton("Add Person");
		
		// add listeners
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientForm.getPersons().add(getPerson());
				clientForm.addName(firstName.getText() + " " + lastName.getText());
				JOptionPane.showMessageDialog(null, "Person Added");
				dispose();
			}
		});
		
		// perform layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 2, 4, 2);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridwidth = 2;
		gbc.gridy = 0;
		gbc.gridx = 0;
		add(new JLabel("Add Person", SwingConstants.CENTER), gbc);

		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.gridx = 0;
		add(new JLabel("First Name:"), gbc);
		gbc.gridx = 1;
		add(firstName, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		add(new JLabel("Last Name:"), gbc);
		gbc.gridx = 1;
		add(lastName, gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;
		add(new JLabel("Middle Name:"), gbc);
		gbc.gridx = 1;
		add(middleName, gbc);

		gbc.gridy = 4;
		gbc.gridx = 0;
		add(new JLabel("Gender:"), gbc);
		gbc.gridx = 1;
		add(gender, gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		add(new JLabel("Adress:"), gbc);
		gbc.gridx = 1;
		add(address, gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 0;
		add(new JLabel("Date Of Birth:"), gbc);
		gbc.gridx = 1;
		add(dateOfBirth, gbc);

		gbc.gridwidth = 2;
		gbc.gridy = 7;
		gbc.gridx = 0;
		{
			JPanel buttons = new JPanel();
			buttons.add(add);
			add(buttons, gbc);
		}
	}

	public Person getPerson() {
		return new Person(firstName.getText(), lastName.getText(), middleName.getText(), (String) gender.getSelectedItem(), address.getText(), dateOfBirth.getCalendar());
	}

	public class DatePicker extends JPanel {
		private Calendar c;
		private JComboBox<Object> month;
		private JComboBox<Object> day;
		private JComboBox<Object> year;

		public DatePicker() {
			super(new FlowLayout(FlowLayout.LEFT, 0, 0));

			c = GregorianCalendar.getInstance();

			year = new JComboBox<>();
			year.addItem("Year");
			// 100 years old below only
			for (int i = c.get(Calendar.YEAR); i > c.get(Calendar.YEAR) - 100; i--)

				year.addItem(Integer.valueOf(i));

			day = new JComboBox<>(new Object[] { "Day", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 });

			month = new JComboBox<>(new Object[] { "Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" });
			day.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					c.set(Calendar.DAY_OF_MONTH, day.getSelectedIndex());
				}
			});
			year.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.set(Calendar.YEAR, (int) year.getSelectedItem());
					validateDay();
				}
			});
			month.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.set(Calendar.MONTH, month.getSelectedIndex() - 1);
					validateDay();
				}
			});

			add(month);
			add(day);
			add(year);
		}

		public void validateDay() {
			int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
			while (day.getItemCount() > maxDay) {
				day.removeItemAt(day.getItemCount() - 1);
			}
			while (day.getItemCount() < maxDay) {
				day.addItem(day.getItemCount());
			}
		}

		public void clearSelection() {
			day.setSelectedIndex(0);
			month.setSelectedIndex(0);
			year.setSelectedIndex(0);
		}

		public Calendar getCalendar() {
			return c;
		}
	}

}
