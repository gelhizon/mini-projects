import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BankHomePanel extends JPanel {
	private JButton memberAddButton;
	private JButton membersListButton;
	private JButton membersTransactionButton;
	private JButton about;
	
	// NAPOLES STYLE
	private JButton napolesButton;	

	public BankHomePanel(final BankControlListener listener) {
		super(new BorderLayout());
		setOpaque(false);
		// COMPONENTS
		memberAddButton = new JButton("Add a Member");
		membersListButton = new JButton("Members");
		membersTransactionButton = new JButton("Transactions");
		about = new JButton("ABOUT");
		napolesButton = new JButton("NAPOLES STYLE");
		napolesButton.setFont(new Font("Calibri", Font.PLAIN, 9));

		// LISTENERS
		memberAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				listener.showMemberAddTab();
			}
		});
		membersListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				listener.showMemberListTab();
			}
		});
		membersTransactionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				listener.inputMemberAccountNumberTransaction();
			}
		});
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Very Simple Bank System :D\nAuthors: Angelito Hizon\nRuth Berlie Tagoon\nJoshua Policarpio");
			}
		});
		napolesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				listener.napolesStyle();
			}
		});

		// LAYOUT
		// NORTH PANEL
		JPanel northPanel = new JPanel();
		northPanel.setOpaque(false);
		JLabel logo = new JLabel(new ImageIcon("banklogo.png"));
		JLabel title = new JLabel(new ImageIcon("banktitle.png"));
		northPanel.add(logo);
		northPanel.add(title);
		add(northPanel, BorderLayout.NORTH);

		// CENTER PANEL
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 0;
		c.weighty = 0.5;
		centerPanel.add(Box.createGlue(), c);
		c.gridy++;
		c.gridx = 0;
		c.weighty = 0;
		centerPanel.add(memberAddButton, c);
		c.gridy++;
		centerPanel.add(membersListButton, c);
		c.gridy++;
		centerPanel.add(membersTransactionButton, c);
		c.gridy++;
		centerPanel.add(about, c);
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		centerPanel.add(napolesButton, c);
		c.gridy++;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;	
		centerPanel.add(Box.createGlue(), c);
		add(centerPanel, BorderLayout.CENTER);
	}
}
