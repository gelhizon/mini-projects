import java.awt.Color;
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
import javax.swing.JTextField;

public class NodeDialog extends JDialog implements ActionListener {
	private Tree tree;
	private JTextField tfLeft, tfRight;
	private JButton btnOK;
	private ArrayList<Tree> temp;

	NodeDialog(JFrame frame, Tree tree, ArrayList<Tree> temp) {
		super(frame, true);
		setTitle("Fill the following form.");
		setLayout(new GridBagLayout());
		setSize(300, 150);
		setLocationRelativeTo(null);

		this.tree = tree;
		this.temp = temp;

		tfLeft = new JTextField(5);
		tfRight = new JTextField(5);

		btnOK = new JButton("OK");

		tfLeft.setHorizontalAlignment(JTextField.CENTER);
		tfRight.setHorizontalAlignment(JTextField.CENTER);

		btnOK.addActionListener(this);

		GridBagConstraints g = new GridBagConstraints();
		g.anchor = GridBagConstraints.CENTER;
		g.weightx = 1;

		g.gridy = 0;
		g.gridx = 0;
		g.gridwidth = 2;
		add(new JLabel("Enter the letter of your left or right tree of [" + tree.getData() + "].", JLabel.CENTER), g);
		g.gridy = 1;
		g.gridwidth = 1;
		add(new JLabel("LEFT", JLabel.CENTER), g);
		g.gridx = 1;
		add(new JLabel("RIGHT", JLabel.CENTER), g);
		g.gridy = 2;
		g.gridx = 0;
		add(tfLeft, g);
		g.gridx = 1;
		add(tfRight, g);
		g.gridy = 3;
		g.gridx = 0;
		g.gridwidth = 2;
		add(btnOK, g);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (!tfLeft.getText().equals("") || !tfLeft.getText().trim().equals("")) {
			Tree tree = new Tree(tfLeft.getText());
			this.tree.setLeft(tree);
			temp.add(tree);
		}
		if (!tfRight.getText().equals("") || !tfRight.getText().trim().equals("")) {
			Tree tree = new Tree(tfRight.getText());
			this.tree.setRight(tree);
			temp.add(tree);
		}
		dispose();

	}

}
