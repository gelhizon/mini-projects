import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class BankAdminCreatePanel extends JPanel {
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton createButton;
	private JButton cancelButton;
	private JLabel contentTitle;
	private BankFileManager fileManager;

	public BankAdminCreatePanel(final BankFrameListener frameListener) {
		super(new GridBagLayout());
		setBackground(new Color(66, 95, 156));
		

		// COMPONENTS
		contentTitle = new JLabel("Admin Account Creation");
		usernameTextField = new JTextField(20);
		passwordTextField = new JPasswordField(20);
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		fileManager = new BankFileManager("admin.dat");
		
		// LISTENERS
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = usernameTextField.getText();
				String password = passwordTextField.getText();
				fileManager.add(new BankAdminData(username, password));
				frameListener.show(BankFrame.ADMINLOGIN);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameListener.show(BankFrame.ADMINLOGIN);
			}
		});
		
		// LAYOUT
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		add(contentTitle, c);
		c.anchor = GridBagConstraints.WEST;
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		add(new JLabel("Username:"), c);
		c.gridx = 1;
		add(usernameTextField, c);
		c.gridy = 2;
		c.gridx = 0;
		add(new JLabel("Password:"), c);
		c.gridx = 1;
		add(passwordTextField, c);
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		buttonsPanel.add(createButton);
		buttonsPanel.add(cancelButton);
		add(buttonsPanel, c);
		
	}
}
