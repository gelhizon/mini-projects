import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class BankFrame extends JFrame implements BankFrameListener {
	private BankAdminLoginPanel adminLoginPanel;
	private BankAdminCreatePanel adminAddPanel;
	private BankControlTabbedPanel adminControlPanel;
	private CardLayout card;

	// CARD NAMES
	public static final String ADMINLOGIN = "ADMINLOGIN";
	public static final String ADMINADD = "ADMINADD";
	public static final String CONTROL = "CONTROL";

	public BankFrame() {
		// SETUP FRAME
		super("My Bank");
		setLayout(card);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// SET DEFAULT LOOK
		Font font = new Font("Tahoma", Font.PLAIN, 14);
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("PasswordField.font", font);
		UIManager.put("TabbedPane.font", font);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Label.foreground", Color.WHITE);
		UIManager.put("Button.background", new Color(96, 121, 171));

		// COMPONENTS
		card = new CardLayout();
		setLayout(card);
		adminLoginPanel = new BankAdminLoginPanel(this);
		adminAddPanel = new BankAdminCreatePanel(this);
		adminControlPanel = new BankControlTabbedPanel();

		// ADD COMPONENTS
		add(adminLoginPanel, ADMINLOGIN);
		add(adminAddPanel, ADMINADD);
		add(adminControlPanel, CONTROL);

		setVisible(true);
	}

	public void show(String cardName) {
		card.show(getContentPane(), cardName);
	}

	public static void main(String[] args) {
		new BankFrame();
	}

}