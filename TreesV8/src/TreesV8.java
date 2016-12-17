import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TreesV8 extends JFrame implements ActionListener {
	private Tree root;
	private JButton btnPreorder, btnInorder, btnPostorder;
	private JButton btnStart;
	private JPanel north, center, south;
	private JLabel ans;

	public TreesV8() {
		super("Trees");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnStart = new JButton("Start");
		btnPreorder = new JButton("Preorder");
		btnInorder = new JButton("Inorder");
		btnPostorder = new JButton("Postorder");
		north = new JPanel(new FlowLayout());
		center = new JPanel(null);
		south = new JPanel(new FlowLayout());
		ans = new JLabel("");

		north.add(btnStart);
		north.add(btnPreorder);
		north.add(btnInorder);
		north.add(btnPostorder);
		center.setBorder(BorderFactory.createLineBorder(Color.gray));
		south.add(ans);

		btnStart.addActionListener(this);

		btnPreorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ans.setText("Preorder is " + Tree.preorder(root));
			}
		});
		btnInorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ans.setText("Preorder is " + Tree.inorder(root));
			}
		});
		btnPostorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ans.setText("Preorder is " + Tree.postorder(root));
			}
		});
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

		setVisible(true);
	}

	public void show(Tree n, int x, int y, int d) {
		if (n != null) {
			JLabel l = new JLabel(n.data, JLabel.CENTER);
			l.setLayout(new BoxLayout(l, BoxLayout.Y_AXIS));
			l.setBorder(BorderFactory.createLineBorder(Color.gray));
			l.setBounds(x - 25, y, 50, 50);
			System.out.println(l.getWidth());
			center.add(l);
			show(n.left, x - d, y + 80, d / 2);
			show(n.right, x + d, y + 80, d / 2);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String input = JOptionPane.showInputDialog(null, "Enter letter of your root: ", "Entry", JOptionPane.QUESTION_MESSAGE);
		root = new Tree(input);

		ArrayList<Tree> list = new ArrayList<Tree>();
		ArrayList<Tree> temp = new ArrayList<Tree>();
		list.add(root);
		
		do {
			for (Tree n : list) {
				new NodeDialog(this, n, temp);
			}
			list.clear();
			list.addAll(temp);
			temp.clear();
		} while (!list.isEmpty());

		int x = Math.round(getContentPane().getWidth() / 2);
		int y = 20;

		show(root, x, y, x / 2);
		center.repaint();

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TreesV8();
			}
		});
	}
}
