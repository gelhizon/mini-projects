import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LibraryBookAdder extends JDialog {
	private JTextField author, title, bookNumber;
	private JButton submit, close;

	public LibraryBookAdder(Component parent) {
		// Setup JFrame
		super((JFrame) SwingUtilities.getWindowAncestor(parent), true);
		setSize(640, 480);
		setLayout(new GridBagLayout());
		setUndecorated(true);
		setLocationRelativeTo(null);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Instantiate Components
		author = new JTextField(10);
		title = new JTextField(10);
		bookNumber = new JTextField(10);
		submit = new JButton("Add");
		close = new JButton("Close");

		// Set Component Properties
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> list = new ArrayList();
				try {
					ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.dat"));
					list.addAll((ArrayList<String[]>) input.readObject());
					input.close();
				} catch (Exception e1) {

				}
				list.add(new String[] { bookNumber.getText(), title.getText(), author.getText() });
				try {
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("books.dat"));
					output.writeObject(list);
					output.close();
				} catch (Exception ex) {

				}
				JOptionPane.showMessageDialog(null, "Book Add Sucesful");
			}
		});
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		// Add Components
		GridBagConstraints gs = new GridBagConstraints();
		gs.insets = new Insets(3, 6, 3, 6);
		gs.gridy = 0;
		gs.gridx = 0;
		gs.gridwidth = 2;
		JLabel label = new JLabel("Add A Book");
		label.setFont(new Font("Impact", Font.PLAIN, 21));
		add(label, gs);
		gs.gridy = 1;
		gs.gridx = 0;
		gs.gridwidth = 1;
		add(new JLabel("Title of the Book:"), gs);
		gs.gridx = 1;
		add(title, gs);
		gs.gridy = 2;
		gs.gridx = 0;
		add(new JLabel("Author of the Book:"), gs);
		gs.gridx = 1;
		add(author, gs);
		gs.gridy = 3;
		gs.gridx = 0;
		add(new JLabel("Book Number:"), gs);
		gs.gridx = 1;
		add(bookNumber, gs);
		gs.gridy = 4;
		gs.gridx = 0;
		gs.gridwidth = 2;
		JPanel buttons = new JPanel();
		buttons.add(submit);
		buttons.add(close);
		add(buttons, gs);

	}
}
