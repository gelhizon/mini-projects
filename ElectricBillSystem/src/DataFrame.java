import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DataFrame extends JFrame {
	UserData data;
	
	public DataFrame(UserData data) {
		super(data.getLastName() + ", " + data.getFirstName() + " " + data.getMiddleName());
		this.data = data;
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//add(createNorthPanel(), BorderLayout.NORTH);
		//add(createSouthPanel(), BorderLayout.SOUTH);
		add(createCenterPanel(), BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		
	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gs = new GridBagConstraints();
		gs.gridx = 0;
		gs.gridy = 0;
		panel.add(new JLabel("Lastname*:", SwingConstants.LEFT), gs);
		JTextField txtLastName = new JTextField(data.getLastName(), 15);
		gs.gridx = 1;
		panel.add(txtLastName, gs);
		gs.gridy = 1;
		gs.gridx = 0;
		panel.add(new JLabel("Firstname*:", SwingConstants.LEFT), gs);
		JTextField txtFirstName = new JTextField(data.getFirstName(), 15);
		gs.gridx = 1;
		panel.add(txtFirstName, gs);
		gs.gridy = 2;
		gs.gridx = 0;
		panel.add(new JLabel("Middlename*:", SwingConstants.LEFT), gs);
		JTextField txtMiddleName = new JTextField(data.getMiddleName(), 15);
		gs.gridx = 1;
		panel.add(txtMiddleName, gs);
		gs.gridy = 3;
		gs.gridx = 0;
		panel.add(new JLabel("HomeAddress:", SwingConstants.LEFT), gs);
		JTextField txtHomeAddress = new JTextField(data.getHomeAddress(), 15);
		gs.gridx = 1;
		panel.add(txtHomeAddress, gs);
		gs.gridy = 4;
		gs.gridx = 0;
		panel.add(new JLabel("Account No:", SwingConstants.LEFT), gs);
		JTextField txtAccNo = new JTextField(data.getAccNo(), 15);
		gs.gridx = 1;
		panel.add(txtAccNo, gs);
		gs.gridy = 5;
		gs.gridx = 0;
		panel.add(new JLabel("Cell Number:", SwingConstants.LEFT), gs);
		JTextField txtCellNumber = new JTextField(data.getCellNumber(), 15);
		gs.gridx = 1;
		panel.add(txtCellNumber, gs);
		gs.gridy = 6;
		gs.gridx = 0;
		panel.add(new JLabel("Landline:", SwingConstants.LEFT), gs);
		JTextField txtLandLine = new JTextField(data.getLandLine(), 15);
		gs.gridx = 1;
		panel.add(txtLandLine, gs);
		gs.gridy = 7;
		gs.gridx = 0;
		panel.add(new JLabel("Email Address", SwingConstants.LEFT), gs);
		JTextField txtEmailAddress = new JTextField(data.getEmailAddress(), 15);
		gs.gridx = 1;
		panel.add(txtEmailAddress, gs);
		gs.gridy = 8;
		gs.gridx = 0;
		panel.add(new JLabel("Meter No:", SwingConstants.LEFT), gs);
		JTextField txtMeterNo = new JTextField(data.getMeterNo(), 15);
		gs.gridx = 1;
		panel.add(txtMeterNo, gs);
		gs.gridy = 9;
		gs.gridx = 0;
		panel.add(new JLabel("KiloWatt Consumed:", SwingConstants.LEFT), gs);
		JTextField txtKiloWattUsed = new JTextField(String.valueOf(data.getKWattUsed()), 15);
		gs.gridx = 1;
		panel.add(txtKiloWattUsed, gs);
		
		return panel;
		
	}

	private Component createSouthPanel() {
		return null;
	}

	private Component createNorthPanel() {
		return null;
	}
}
