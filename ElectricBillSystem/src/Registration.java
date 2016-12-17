import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Registration extends JDialog implements ActionListener {
	private JTextField txtLastName, txtFirstName, txtMiddleName, txtHomeAddress, txtAccNo, txtCellNumber, txtLandLine, txtEmailAddress, txtMeterNo;
	private JButton btnOk, btnCancel;
	private boolean isCancelPressed;

	public Registration(JFrame parent) {
		super(parent, "Add Contact", true);
		setSize(500, 700);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(setUpGui());
	}

	private Container setUpGui() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(createNorthPanel(), BorderLayout.NORTH);
		c.add(createSouthPanel(), BorderLayout.SOUTH);
		c.add(createCenterPanel(), BorderLayout.CENTER);
		return c;
	}

	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Registration");
		label.setFont(new Font("Impact", Font.PLAIN, 21));
		panel.add(label);
		return panel;
	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gs = new GridBagConstraints();
		gs.insets = new Insets(5, 5, 5, 5);
		gs.gridy = 0;
		gs.gridx = 0;
		panel.add(new JLabel("Lastname*:", SwingConstants.LEFT), gs);
		txtLastName = new JTextField(10);
		gs.gridx = 1;
		panel.add(txtLastName, gs);
		gs.gridy = 1;
		gs.gridx = 0;
		panel.add(new JLabel("Firstname*:", SwingConstants.LEFT), gs);
		txtFirstName = new JTextField(10);
		gs.gridx = 1;
		panel.add(txtFirstName, gs);
		gs.gridy = 2;
		gs.gridx = 0;
		panel.add(new JLabel("Middlename*:", SwingConstants.LEFT), gs);
		txtMiddleName = new JTextField(10);
		gs.gridx = 1;
		panel.add(txtMiddleName, gs);
		gs.gridy = 3;
		gs.gridx = 0;
		panel.add(new JLabel("HomeAddress:", SwingConstants.LEFT), gs);
		txtHomeAddress = new JTextField(10);
		gs.gridx = 1;
		panel.add(txtHomeAddress, gs);
		gs.gridy = 4;
		gs.gridx = 0;
		panel.add(new JLabel("Account No:", SwingConstants.LEFT), gs);
		txtAccNo = new JTextField(10);
		txtAccNo.setText(new SimpleDateFormat("yyyyMMHHmmss").format(new Date()));
		txtAccNo.setEditable(false);
		gs.gridx = 1;
		panel.add(txtAccNo, gs);
		gs.gridy = 5;
		gs.gridx = 0;
		panel.add(new JLabel("Cell Number:", SwingConstants.LEFT), gs);
		txtCellNumber = new JTextField(10);
		gs.gridx = 1;
		panel.add(txtCellNumber, gs);
		gs.gridy = 6;
		gs.gridx = 0;
		panel.add(new JLabel("Landline:", SwingConstants.LEFT), gs);
		txtLandLine = new JTextField(10);
		gs.gridx = 1;
		panel.add(txtLandLine, gs);
		gs.gridy = 7;
		gs.gridx = 0;
		panel.add(new JLabel("Email Address", SwingConstants.LEFT), gs);
		txtEmailAddress = new JTextField(10);
		gs.gridx = 1;
		panel.add(txtEmailAddress, gs);
		gs.gridy = 8;
		gs.gridx = 0;
		panel.add(new JLabel("Meter No:", SwingConstants.LEFT), gs);
		txtMeterNo = new JTextField(10);
		txtMeterNo.setText(new SimpleDateFormat("yyyyMMHHmmssS").format(new Date()));
		txtMeterNo.setEditable(false);
		gs.gridx = 1;
		panel.add(txtMeterNo, gs);
		return panel;
	}

	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		btnOk = new JButton("Add");
		btnOk.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		panel.add(btnOk);
		panel.add(btnCancel);

		return panel;
	}

	public void actionPerformed(ActionEvent e) {
		String caption = e.getActionCommand();
		if (caption.equals("Add")) {
			onAdd();
		} else {
			onCancel();
		}
	}

	public void onAdd() {
		if (txtFirstName.getText().equals("") || txtMiddleName.getText().equals("") || txtMiddleName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Lastname, Middlename,Firstname are required fields", "System Message", JOptionPane.ERROR_MESSAGE);
			
		} else {
			int i = JOptionPane.showConfirmDialog(this, "Do you want to save?", "System Message", JOptionPane.YES_NO_OPTION);
			if (i == 0) {
				ArrayList<UserData> list = new ArrayList();
				try {
					ObjectInputStream input = new ObjectInputStream(new FileInputStream("userlist.dat"));
					list = ((ArrayList<UserData>) input.readObject());
					input.close();
				} catch (Exception e) {
					
				} finally { 
					list.add(new UserData(txtLastName.getText(), txtFirstName.getText(), txtMiddleName.getText(), txtHomeAddress.getText(), txtAccNo.getText(), txtCellNumber.getText(), txtLandLine.getText(), txtEmailAddress.getText(), txtMeterNo.getText()));
					try{ 
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("userlist.dat"));
						output.writeObject(list);
						output.close();
					} catch( Exception e) {
						
					}
				}
				isCancelPressed = false;
				setVisible(false);
			}
			JOptionPane.showMessageDialog(this, "SAVE", "System Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void onCancel() {
		isCancelPressed = true;
		setVisible(false);
	}

	public boolean getIsCancelPressed() {
		return this.isCancelPressed;
	}

	public static void main(String[] args) {
		new Registration(new JFrame()).setVisible(true);
	}
}
