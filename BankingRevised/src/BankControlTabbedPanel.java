import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class BankControlTabbedPanel extends JPanel implements BankControlListener {
	private JTabbedPane tabbedPane;
	private BankFileManager membersFileManager;
	private BankMembersListPanel membersListPanel;
	private BankHomePanel home;
	private BankMemberAddPanel memberAddPanel;
	private BankMemberTransactionPanel memberTransactionPanel;

	// ACTIVE TAB LISTS
	private ArrayList<BankMemberTransactionPanel> activeMemberTransList;

	public BankControlTabbedPanel() {
		super(new BorderLayout());
		// FONTS FONTS
		// UIManager.put("Label.foreground", new Color(51, 51, 51));
		UIManager.put("TabbedPane.contentAreaColor", new Color(66, 95, 156));
		UIManager.put("TabbedPane.selected", new Color(66, 95, 156));

		// COMPONENT
		tabbedPane = new JTabbedPane();
		membersFileManager = new BankFileManager("bankmembers.dat");
		membersListPanel = new BankMembersListPanel(this, membersFileManager);
		memberAddPanel = new BankMemberAddPanel(this, membersListPanel, membersFileManager);
		home = new BankHomePanel(this);
		activeMemberTransList = new ArrayList();

		// PROPERTIES

		// LAYOUT
		tabbedPane.addTab("Home", home);
		tabbedPane.addTab("Members", membersListPanel);
		add(tabbedPane);

	}

	public void addTab(String title, final Component c) {
		tabbedPane.add(c);
		int index = tabbedPane.indexOfComponent(c);
		System.out.println(index);
		// component
		JLabel titleLabel = new JLabel(title, JLabel.LEFT);
		titleLabel.setForeground(new Color(55, 49, 69));
		JButton closeButton = new JButton("X");
		closeButton.setMargin(new Insets(-1, -1, -1, -1));
		closeButton.setFont(new Font("Arial Black", Font.BOLD, 9));
		closeButton.setFocusPainted(false);
		closeButton.setForeground(Color.WHITE);
		closeButton.setBackground(new Color(224, 67, 67));
		// listener
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				tabbedPane.remove(c);
				if (c instanceof BankMemberAddPanel) {
					((BankMemberAddPanel) c).resetFields();
				} else if (c instanceof BankMemberTransactionPanel) {
					activeMemberTransList.remove(c);
				}
			}
		});
		// layout
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		titlePanel.setOpaque(false);
		titlePanel.add(titleLabel);
		titlePanel.add(closeButton);
		tabbedPane.setTabComponentAt(index, titlePanel);
	}

	public void showMemberAddTab() {
		if (tabbedPane.indexOfComponent(memberAddPanel) < 0)
			addTab("Add a Member", memberAddPanel);
		tabbedPane.setSelectedComponent(memberAddPanel);
	}

	public void removeMemberAddPanel() {
		tabbedPane.remove(memberAddPanel);
		showHomeTab();
	}

	public void showHomeTab() {
		tabbedPane.setSelectedComponent(home);
	}

	public void showMemberListTab() {
		tabbedPane.setSelectedComponent(membersListPanel);
	}

	public void inputMemberAccountNumberTransaction() {
		String accountString = JOptionPane.showInputDialog(null, "Enter Account Number", "Account", JOptionPane.OK_CANCEL_OPTION);
		Integer accountInteger = Integer.parseInt(accountString);

		ArrayList<BankMemberData> list = membersFileManager.getList();
		BankMemberData memberData = null;
		for (int i = 0; i < list.size(); i++) {
			if (accountInteger.equals((list.get(i)).getAccountNumber())) {
				memberData = list.get(i);
				break;
			}
		}

		if (memberData == null) {
			JOptionPane.showMessageDialog(null, "Invalid Account Number:", "Account", JOptionPane.ERROR_MESSAGE);
		} else {
			showTransactionTab(memberData, membersFileManager);
		}
	}

	public void inputMemberIndexTransaction(int selectedRow) {
		BankMemberData memberData = (BankMemberData) membersFileManager.getList().get(selectedRow);
		showTransactionTab(memberData, membersFileManager);
	}

	private void showTransactionTab(BankMemberData memberData, BankFileManager membersFileManager) {
		memberTransactionPanel = new BankMemberTransactionPanel(memberData, membersFileManager);

		// Check for duplicate Transaction Panel
		boolean found = false;
		for (int i = 0; i < activeMemberTransList.size(); i++) {
			// If found
			if (activeMemberTransList.get(i).getMemberData().equals(memberData)) {
				memberTransactionPanel = activeMemberTransList.get(i);
				found = true;
				break;
			}
		}

		if (!found) {
			activeMemberTransList.add(memberTransactionPanel);
			addTab(memberData.getLastName() + ", " + memberData.getFirstName(), memberTransactionPanel);
		}

		// SELECT THE TAB
		tabbedPane.setSelectedComponent(memberTransactionPanel);
	}

	public void napolesStyle() {
		ArrayList<BankMemberData> list = membersFileManager.getList();
		Double piggy = 0.0;
		for (int i = 0; i < list.size(); i++) {
			BankMemberData memberData = list.get(i);
			Double money = memberData.getBalanceList().get(memberData.getHistoryCounter() - 1);
			System.out.println(money);
			memberData.getDateList().add(new Date());
			memberData.setBalanceList(new ArrayList());
			memberData.getBalanceList().add(0.0);
			memberData.setWithdrawList(new ArrayList());
			memberData.getWithdrawList().add(0.0);
			memberData.setDepositList(new ArrayList());
			memberData.getDepositList().add(0.0);
			memberData.resetHistoryCounter();
			piggy += money;
		}
		membersFileManager.save();
		JOptionPane.showMessageDialog(null, "Napoles used her piggy magic and got P" + piggy + " and run away :D");
	}
}
