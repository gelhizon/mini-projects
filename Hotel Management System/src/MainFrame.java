import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MainFrame extends JFrame {
	private JTextArea[] clients;
	private JButton[] checkIn;
	private JButton[] checkOut;

	public MainFrame(final Model model) {
		super("Simple Hotel Management");
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		UIManager.put("Label.font", new Font("Tahoma", Font.PLAIN, 14));
		UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 14));
		UIManager.put("TextField.font", new Font("Tahoma", Font.PLAIN, 14));
		UIManager.put("TextArea.font", new Font("Tahoma", Font.PLAIN, 14));

		// Instantiate
		checkIn = new JButton[Model.NUMBER_OF_ROOMS];
		checkOut = new JButton[Model.NUMBER_OF_ROOMS];
		clients = new JTextArea[Model.NUMBER_OF_ROOMS];

		// Set Properties
		JLabel header = new JLabel("Simple Hotel Management System", SwingConstants.CENTER);
		header.setFont(new Font("Impact", Font.PLAIN, 21));
		add(header, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < Model.NUMBER_OF_ROOMS; i++) {
			final int icopy = i;
			final MainFrame frame = this;
			checkIn[i] = new JButton("Check-in");
			checkOut[i] = new JButton("Check-out");
			clients[i] = new JTextArea(5, 20);
			clients[i].setTabSize(1);

			clients[i].setEditable(false);
			if (model.get(i) == null) {
				checkOut[i].setEnabled(false);
				clients[i].setText("Available");
			} else {
				checkIn[i].setEnabled(false);
				clients[i].setText("Check-in: \n");

				for (Person person : model.get(i).getPersons()) {
					clients[i].append("\t" + person.getLastName() + ", " + person.getFirstName() + " " + person.getMiddleName() + "\n");
				}

			}

			checkIn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ClientForm(frame, model, icopy).setVisible(true);
				}
			});
			checkOut[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.remove(icopy);
					checkIn[icopy].setEnabled(true);
					checkOut[icopy].setEnabled(false);
					clients[icopy].setText("Available");
				}
			});

			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(2, 1, 2, 1);
			gbc.anchor = GridBagConstraints.WEST;


			JPanel middle = new JPanel();
			middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
			middle.add(new JLabel("Room " + (i + 1)));
			middle.add(new JLabel("Max Person: " + Model.NUMBER_OF_PERSON_PER_ROOM[i]));
			middle.add(new JLabel("Price: $" + Model.PRICES[i]));
			panel.add(middle);

			JPanel buttons = new JPanel();
			buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
			buttons.add(checkIn[i]);
			buttons.add(checkOut[i]);
			buttons.add(new JScrollPane(clients[i]));
			panel.add(buttons);

			panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
			centerPanel.add(panel);

		}

		add(new JScrollPane(centerPanel), BorderLayout.CENTER);
		setVisible(true);
	}

	public JButton[] getSignIn() {
		return checkIn;
	}

	public JButton[] getSignOut() {
		return checkOut;
	}

	public JTextArea[] getClients() {
		return clients;
	}

	public static void main(String[] args) {
		Model model = new Model();
		new MainFrame(model);
	}
}
