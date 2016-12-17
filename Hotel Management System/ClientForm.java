import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientForm extends JDialog {
	private JTextField price;
	private ArrayList<Person> persons;
	private ArrayList<JTextField> names;
	private JComboBox<Integer> numberOfStay;
	private JButton addPerson;
	private JButton book;
	private JPanel stack;

	public ClientForm(final MainFrame frame, final Model model, final int roomNumber) {
		super(frame, true);
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(640, 480));
		setLocationRelativeTo(frame);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		persons = new ArrayList<Person>();
		names = new ArrayList<JTextField>();
		stack = new JPanel();
		stack.setLayout(new BoxLayout(stack, BoxLayout.Y_AXIS));
		numberOfStay = new JComboBox<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 });
		price = new JTextField(20);
		addPerson = new JButton("Add Person");
		book = new JButton("Book");

		addPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.NUMBER_OF_PERSON_PER_ROOM[roomNumber] > names.size()) {
					showForm();
				} else {
					JOptionPane.showMessageDialog(null, "Max Person Limit Reached");
				}
			}
		});
		book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.add(roomNumber, new Client(String.valueOf(roomNumber + 1), model.PRICES[roomNumber], (Integer) numberOfStay.getSelectedItem(), persons));
				frame.getSignIn()[roomNumber].setEnabled(false);
				frame.getSignOut()[roomNumber].setEnabled(true);
				frame.getClients()[roomNumber].setText("Check-in: \n");
				for (Person person : persons) {
					frame.getClients()[roomNumber].append("\t"+person.getLastName() + ", " + person.getFirstName() + " " + person.getMiddleName() + "\n");
				}
				JOptionPane.showMessageDialog(null, "Book Success");
				dispose();
			}
		});
		add(new JLabel("Client Form", SwingConstants.CENTER), BorderLayout.NORTH);

		JPanel centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 2, 4, 2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		centerPanel.add(new JLabel("Price: $" + model.PRICES[roomNumber] + " per night", SwingConstants.LEFT), gbc);

		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.gridx = 0;
		centerPanel.add(new JLabel("Number of Stay:", SwingConstants.LEFT), gbc);
		gbc.gridx = 1;
		centerPanel.add(numberOfStay, gbc);

		gbc.gridwidth = 2;
		gbc.gridy = 2;
		gbc.gridx = 0;
		centerPanel.add(new JLabel("Names:", SwingConstants.LEFT), gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;
		centerPanel.add(stack, gbc);

		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.weighty = 1;
		centerPanel.add(Box.createGlue(), gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.weighty = 0;
		gbc.weightx = 0;
		centerPanel.add(addPerson, gbc);

		gbc.gridy = 6;
		{
			JPanel buttons = new JPanel();
			buttons.add(book);
			centerPanel.add(buttons, gbc);
		}

		add(centerPanel, BorderLayout.CENTER);
		add(Box.createGlue(), BorderLayout.SOUTH);
	}

	private void showForm() {
		new PersonForm(this).setVisible(true);
	}

	public void addName(String name) {
		JTextField tf = new JTextField(name);
		tf.setEditable(false);
		stack.add(tf);
		names.add(tf);
		revalidate();
		repaint();
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

}
