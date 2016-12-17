import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainForm extends JFrame implements ActionListener {
	private JTextField txtSystemName;
	private JButton btnUserData, btnExit, btnPayment, btnRegistration;

	public MainForm() {
		super("Electric Billing System");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(setUpGui());

	}

	private Container setUpGui() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(createSouthPanel(), BorderLayout.SOUTH);
		c.add(createCenterPanel(), BorderLayout.CENTER);
		c.add(new JPanel(), BorderLayout.EAST);
		c.add(new JPanel(), BorderLayout.WEST);
		c.add(new JPanel(), BorderLayout.NORTH);
		return c;
	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2, 3, 3));
		panel.add(new JLabel("Billing System", SwingConstants.CENTER));

		return panel;
	}

	private JPanel createSouthPanel() {

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 2, 3, 3));
		panel1.setLayout(new FlowLayout());
		btnUserData = new JButton("User Data");
		btnUserData.addActionListener(this);
		btnRegistration = new JButton("Registration");
		btnRegistration.addActionListener(this);
		btnPayment = new JButton("Payment");
		btnPayment.addActionListener(this);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		panel1.add(btnUserData);
		panel1.add(btnRegistration);
		panel1.add(btnPayment);
		panel1.add(btnExit);
		return panel1;
	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == btnRegistration) {
			new Registration(this).setVisible(true);
		} else if (source == btnUserData) {
			String accNumber = JOptionPane.showInputDialog(null, "Enter Account Number:");
			
			ArrayList<UserData> list = new ArrayList();
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream("userlist.dat"));
				list = ((ArrayList<UserData>) input.readObject());
				input.close();
			} catch (Exception ex) {}

			for(int i = 0; i < list.size(); i++) {
				UserData data = list.get(i);
				if(data.getAccNo().equals(accNumber)) {
					Double kwUsed = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Kilowat Consumed:"));
					data.setKWattUsed(kwUsed);
					new DataFrame(data);
				}
			}
			
		} else if (source == btnExit) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new MainForm().setVisible(true);
	}
}