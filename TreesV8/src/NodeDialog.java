import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NodeDialog extends JDialog {
		private Tree tree;
		private JTextField tfLeft, tfRight;
		private JButton btnOK;

		NodeDialog(JFrame frame, final Tree tree, final ArrayList<Tree> temp) {
			super(frame, true);
			setTitle("Fill the following form.");
			setLayout(new GridBagLayout());
			setSize(400, 300);
			tfLeft = new JTextField(5);
			tfRight = new JTextField(5);
			btnOK = new JButton("OK");
			

			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!tfLeft.getText().equals("") || !tfLeft.getText().trim().equals("")) {
						tree.left = new Tree(tfLeft.getText());
						temp.add(tree.left);

					}
					if (!tfRight.getText().equals("") || !tfRight.getText().trim().equals("")) {
						tree.right = new Tree(tfRight.getText());
						temp.add(tree.right);
					}
					setVisible(false);

				}
			});

			GridBagConstraints g = new GridBagConstraints();
			g.anchor = GridBagConstraints.EAST;
			g.gridy = 0;
			g.gridx = 0;
			g.gridwidth = 2;
			add(new JLabel("Enter the letter of your left or right tree of [" + tree.data + "]."), g);
			g.gridy = 1;
			g.gridwidth = 1;
			add(tfLeft, g);
			g.gridx = 1;
			add(tfRight, g);
			g.gridy = 2;
			g.gridx = 0;
			g.gridwidth = 2;
			add(btnOK, g);
			setLocationRelativeTo(null);
			setVisible(true);
		}
	}
