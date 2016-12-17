import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TreeTraversal extends JFrame implements ActionListener {
	private Node rootNode;
	private JTextField levelCount;
	private JButton go, preorder, inorder, postorder;

	public static void main(String[] args) {
		new TreeTraversal();
	}

	public TreeTraversal() {
		super("Tree Traversal");
		setSize(1024, 648);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		levelCount = new JTextField(5);
		go = new JButton("GO");
		preorder = new JButton("PreOrder");
		inorder = new JButton("Inorder");
		postorder = new JButton("Postorder");
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north.setBackground(Color.WHITE);
		north.add(new JLabel("Enter how many level:"));
		north.add(levelCount);
		north.add(go);
		north.add(preorder);
		north.add(inorder);
		north.add(postorder);
		go.addActionListener(this);
		preorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, Node.preorder(rootNode));
			}
		});
		inorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, Node.inorder(rootNode));
			}
		});
		postorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, Node.postorder(rootNode));
			}
		});
		add(north, BorderLayout.NORTH);
		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		int x = Math.round(getWidth() / 2);
		int y = 100;
		g.setFont(new Font("Arial", Font.BOLD, 20));

		printNodes(g, rootNode, x, y, x, y, x / 2);
	}

	public void printNodes(Graphics g, Node n, int x, int y, int px, int py, int d) {
		if (n != null) {
			g.drawString(n.data, x, y);
			g.drawLine(x, y, px, py);
			printNodes(g, n.left, x - d, y + 80, x, y, d / 2);
			printNodes(g, n.right, x + d, y + 80, x, y, d / 2);
		}
	}

	public void actionPerformed(ActionEvent e) {
		ArrayList<Node> list1 = new ArrayList<Node>();
		ArrayList<Node> list2 = new ArrayList<Node>();
		String input = JOptionPane.showInputDialog("Enter Root:");
		rootNode = new Node(input);
		list2.add(rootNode);
		int c = Integer.parseInt(levelCount.getText());
		for (int i = 1; i < c; i++) {
			list1.clear();
			list1.addAll(list2);
			list2.clear();
			for (Node node : list1) {
				input = "";
				input = JOptionPane.showInputDialog("Enter Left:");
				if (!input.equals("")) {
					Node n = new Node(input);
					list2.add(n);
					node.left = n;
					System.out.println("TEST1");
				}
				input = "";
				input = JOptionPane.showInputDialog("Enter Right:");
				if (!input.equals("")) {
					Node n = new Node(input);
					list2.add(n);
					node.right = n;
					System.out.println("TEST2");
				}
			}
		}
		repaint();
	}
}
