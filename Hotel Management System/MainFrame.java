import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box.Filler;
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

		UIManager.put("Label.font", new Font("Tahoma", Font.PLAIN, 18));
		UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 18));
		UIManager.put("TextField.font", new Font("Tahoma", Font.PLAIN, 18));
		UIManager.put("TextArea.font", new Font("Tahoma", Font.PLAIN, 18));

		checkIn = new JButton[Model.NUMBER_OF_ROOMS];
		checkOut = new JButton[Model.NUMBER_OF_ROOMS];
		clients = new JTextArea[Model.NUMBER_OF_ROOMS];

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

			JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(2, 1, 2, 1);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridy = 0;
			gbc.gridx = 0;
			panel.add(new JLabel("Room " + (i + 1)), gbc);

			gbc.gridy = 1;
			gbc.gridx = 0;
			panel.add(new JLabel("Max Person: " + Model.NUMBER_OF_PERSON_PER_ROOM[i]), gbc);

			gbc.gridy = 2;
			gbc.gridx = 0;
			panel.add(new JLabel("Price: $" + Model.PRICES[i]), gbc);

			gbc.gridy = 0;
			gbc.gridx = 1;
			gbc.gridheight = 3;
			panel.add(new JLabel(new ImageIcon("img\\" + (i + 1) + ".jpg"), SwingConstants.LEFT), gbc);

			{
				JPanel buttons = new JPanel();
				buttons.add(checkIn[i]);
				buttons.add(checkOut[i]);
				gbc.anchor = GridBagConstraints.EAST;
				gbc.gridheight = 1;
				gbc.gridy = 0;
				gbc.gridx = 2;
				panel.add(buttons, gbc);
			}

			if (model.get(i) == null) {
				checkOut[i].setEnabled(false);
				clients[i].setText("Available");
			} else {
				checkIn[i].setEnabled(false);
				clients[i].setText("Check-in: \n");

				for (Person person : model.get(i).getPersons()) {
					clients[i].append("\t"+person.getLastName() + ", " + person.getFirstName() + " " + person.getMiddleName() + "\n");
				}

			}

			gbc.gridy = 1;
			panel.add(new JScrollPane(clients[i]), gbc);

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
