import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class BankAdminLoginPanel extends JPanel {
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton, createButton;
	private JLabel contentTitle;
	private BankFrameListener frameListener;
	
	public BankAdminLoginPanel(final BankFrameListener frameListener) {
		super(new BorderLayout());
		setBackground(new Color(66, 95, 156));
		this.frameListener = frameListener;
		
		// COMPONENTS
		contentTitle = new JLabel("Policarpio's Bank");
		usernameTextField = new JTextField(20);
		passwordTextField = new JPasswordField(20);
		loginButton = new JButton("Login");
		createButton = new JButton("Create");
		contentTitle = new JLabel("Log in");
		
		
		// LISTENERS
		passwordTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				login();
			}
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				login();
			}
		});
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameListener.show(BankFrame.ADMINADD);
			}
		});
		
		// LAYOUT LAYOUT
		// NORTH PANEL
		JPanel northPanel = new JPanel();
		northPanel.setOpaque(false);
		JLabel title = new JLabel(new ImageIcon("polybank.png"));
		northPanel.add(title);
		add(northPanel, BorderLayout.NORTH);
		
		// CENTER PANEL
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		centerPanel.add(contentTitle, c);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		centerPanel.add(new JLabel("Username:"), c);
		c.gridx = 1;
		centerPanel.add(usernameTextField, c);
		c.gridy = 2;
		c.gridx = 0;
		centerPanel.add(new JLabel("Password:"), c);
		c.gridx = 1;
		centerPanel.add(passwordTextField, c);
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 2;
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		buttonsPanel.add(loginButton);
		buttonsPanel.add(createButton);
		centerPanel.add(buttonsPanel, c);
		add(centerPanel, BorderLayout.CENTER);

	}
	
	private void login() {
		ArrayList<BankAdminData> adminList;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("admin.dat"));
			adminList = (ArrayList<BankAdminData>) input.readObject();
			input.close();
			for (int i = 0; i < adminList.size(); i++) {
				BankAdminData admin = adminList.get(i);
				if (usernameTextField.getText().equals(admin.getUsername()) && passwordTextField.getText().equals(admin.getPassword())) {
					System.out.println("Sucessfully Login.");
					// Tell to Change View
					frameListener.show(BankFrame.CONTROL);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("No such file exists.");
		}
	}

}