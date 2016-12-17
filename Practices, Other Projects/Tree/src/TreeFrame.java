import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TreeFrame extends JFrame implements ActionListener {
	private Tree root;
	private JButton btnPreorder, btnInorder, btnPostorder;
	private JButton btnStart;
	private JPanel north, center, south;
	private TreeDrawPanel drawPanel;
	private JLabel answer;

	public TreeFrame() {
		super("Trees");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		btnStart = new JButton("Start");
		btnPreorder = new JButton("Preorder");
		btnInorder = new JButton("Inorder");
		btnPostorder = new JButton("Postorder");

		north = new JPanel(new FlowLayout());
		center = new JPanel(new BorderLayout());
		south = new JPanel(new FlowLayout());

		answer = new JLabel("XXXX");

		drawPanel = new TreeDrawPanel();

		center.setBorder(BorderFactory.createLineBorder(Color.gray));

		north.add(btnStart);
		north.add(btnPreorder);
		north.add(btnInorder);
		north.add(btnPostorder);
		center.add(drawPanel, BorderLayout.CENTER);
		south.add(answer);
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);

		btnStart.addActionListener(this);
		btnPreorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answer.setText("Preorder is " + TreeHelper.preorder(root));
			}
		});
		btnInorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answer.setText("Preorder is " + TreeHelper.inorder(root));
			}
		});
		btnPostorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answer.setText("Preorder is " + TreeHelper.postorder(root));
			}
		});

		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		String input = JOptionPane.showInputDialog(null, "Enter letter of your root: ", "Entry", JOptionPane.QUESTION_MESSAGE);
		root = new Tree(input);
		drawPanel.setRoot(root);
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
		
		drawPanel.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TreeFrame();
			}
		});
	}
}
