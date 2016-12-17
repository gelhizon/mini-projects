import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LibrarySystemMainFrame extends JFrame {
	private JTextField name, address, contact, studno, author, title, booknumber;
	private JButton submit, list, bookslist, addABook, addAStudents, studentspopup, bookspopup;
	private int bookSelected = -1;

	public LibrarySystemMainFrame() {
		super("Library Management System");
		setSize(800, 600);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		studno = new JTextField();
		name = new JTextField(20);
		name.setEditable(false);
		address = new JTextField(20);
		contact = new JTextField(20);
		author = new JTextField(20);
		title = new JTextField(20);
		booknumber = new JTextField(20);
		booknumber.setEditable(false);
		submit = new JButton("Borrow");
		list = new JButton("Borrowers List");
		bookslist = new JButton("Available Books");
		addABook = new JButton("Add Books");
		addAStudents = new JButton("Add Students");
		studentspopup = new JButton("Students");
		studentspopup.setFont(new Font("Arial Narrow", Font.PLAIN, 9));
		studentspopup.setMargin(new Insets(0, 0, 0, 0));
		bookspopup = new JButton("Select Book");
		studentspopup.setFont(new Font("Arial Narrow", Font.PLAIN, 9));
		studentspopup.setMargin(new Insets(0, 0, 0, 0));

		title.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				validateBook();
			}

		});
		author.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				validateBook();
			}

		});
		studno.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				if (!studno.getText().isEmpty()) {
					ArrayList<Object[]> studentsList = new ArrayList();
					try {
						ObjectInputStream input = new ObjectInputStream(new FileInputStream("students.dat"));
						studentsList.addAll((ArrayList) input.readObject());
						input.close();
					} catch (Exception e1) {
					}

					boolean found = false;
					for (int i = 0; i < studentsList.size(); i++) {
						String studNumber = (String) studentsList.get(i)[0];
						if (studNumber.equals(studno.getText())) {
							name.setText((String) studentsList.get(i)[2] + ", " + studentsList.get(i)[1] + " " + studentsList.get(i)[3]);
							found = true;
						}
					}
					if (!found) {
						JOptionPane.showMessageDialog(null, "Invalid Student Number");
						studno.setText("");
						name.setText("");
						studno.grabFocus();
					}
				}
			}
		});
		addABook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookFrame bookFrame = new BookFrame();
			}
		});
		bookslist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BooksList bookFrame = new BooksList();
			}
		});
		addAStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentCreatorFrame studCreate = new StudentCreatorFrame();
			}
		});

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 6, 3, 6);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		JLabel titlela = new JLabel("Library Management System", SwingConstants.CENTER);
		titlela.setFont(new Font("Palatino Linotype", Font.BOLD, 42));
		add(titlela, c);
		c.gridy++;
		c.gridx = 0;
		JLabel label = new JLabel("Borrowers Information", SwingConstants.CENTER);
		label.setFont(new Font("Impact", Font.PLAIN, 21));
		add(label, c);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		add(new JLabel("Student No:"), c);
		JPanel studpanel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.weightx = 1;
		d.gridy = 0;
		d.gridx = 0;
		studpanel.add(studno, d);
		d.weightx = 0;
		d.gridx = 1;
		studpanel.add(studentspopup, d);
		c.gridx = 1;
		add(studpanel, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Name:"), c);
		c.gridx = 1;
		add(name, c);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		JLabel label2 = new JLabel("Book", SwingConstants.CENTER);
		label2.setFont(new Font("Impact", Font.PLAIN, 21));
		c.anchor = GridBagConstraints.CENTER;
		add(label2, c);
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		add(bookspopup, c);
		add(title, c);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Title:"), c);
		c.gridx = 1;
		add(title, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Author:"), c);
		c.gridx = 1;
		add(author, c);
		c.gridy++;
		c.gridx = 0;
		add(new JLabel("Book Number:"), c);
		c.gridx = 1;
		add(booknumber, c);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		JPanel buttons = new JPanel();
		buttons.add(submit);
		buttons.add(list);
		buttons.add(bookslist);
		add(buttons, c);
		c.gridy++;
		c.gridx = 0;
		JPanel extended = new JPanel();
		extended.add(addABook);
		extended.add(addAStudents);
		add(extended, c);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!studno.getText().isEmpty() && !booknumber.getText().isEmpty()) {
					ArrayList list = new ArrayList();
					try {
						ObjectInputStream input = new ObjectInputStream(new FileInputStream("borrow.dat"));
						list.addAll((ArrayList) input.readObject());
						input.close();
					} catch (Exception e) {

					}
					list.add(new Object[] { new SimpleDateFormat("MMM dd, yyyy").format(new Date()), studno.getText(), name.getText(), address.getText(), contact.getText(), booknumber.getText(), title.getText(), author.getText() });
					try {
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("borrow.dat"));
						output.writeObject(list);
						output.close();
					} catch (Exception ex) {

					}
					ArrayList booklist = new ArrayList();
					try {
						ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.dat"));
						booklist.addAll((ArrayList) input.readObject());
						input.close();
					} catch (Exception e1) {

					}
					booklist.remove(bookSelected);
					try {
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("books.dat"));
						output.writeObject(booklist);
						output.close();
					} catch (Exception ex) {

					}

					name.setText("");
					address.setText("");
					contact.setText("");
					studno.setText("");
					author.setText("");
					title.setText("");
					booknumber.setText("");
					JOptionPane.showMessageDialog(null, "Borrowed success");
				}
			}
		});

		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BorrowersFrame frame = new BorrowersFrame();
			}
		});
		studentspopup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog studentsPopup = new JDialog();
				studentsPopup.setSize(400, 400);
				studentsPopup.setLocationRelativeTo(studentspopup);
				studentsPopup.addWindowFocusListener(new WindowFocusListener() {
					public void windowGainedFocus(WindowEvent arg0) {
					}

					public void windowLostFocus(WindowEvent e) {
						studentsPopup.dispose();
					}

				});
				final ArrayList<Object[]> studentsList = new ArrayList();
				DefaultListModel listModel = new DefaultListModel();
				final JList jlist = new JList(listModel);
				JScrollPane pane = new JScrollPane(jlist);
				try {
					ObjectInputStream input = new ObjectInputStream(new FileInputStream("students.dat"));
					studentsList.addAll((ArrayList) input.readObject());
					input.close();
				} catch (Exception e1) {
				}

				for (int i = 0; i < studentsList.size(); i++) {
					listModel.addElement(studentsList.get(i)[0] + " - " + studentsList.get(i)[2] + ", " + studentsList.get(i)[1] + " " + studentsList.get(i)[3]);
				}
				jlist.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent ae) {
						studno.setText((String) studentsList.get(jlist.getSelectedIndex())[0]);
						name.setText(studentsList.get(jlist.getSelectedIndex())[2] + ", " + studentsList.get(jlist.getSelectedIndex())[1] + " " + studentsList.get(jlist.getSelectedIndex())[3]);
						studentsPopup.dispose();
					}

				});
				studentsPopup.add(pane);
				studentsPopup.setVisible(true);
			}

		});
		bookspopup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JDialog booksPopup = new JDialog();
				booksPopup.setSize(400, 400);
				booksPopup.setLocationRelativeTo(bookspopup);
				booksPopup.addWindowFocusListener(new WindowFocusListener() {
					public void windowGainedFocus(WindowEvent arg0) {
					}

					public void windowLostFocus(WindowEvent e) {
						booksPopup.dispose();
					}

				});
				final ArrayList<Object[]> booksList = new ArrayList();
				DefaultListModel listModel = new DefaultListModel();
				final JList jlist = new JList(listModel);
				JScrollPane pane = new JScrollPane(jlist);
				try {
					ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.dat"));
					booksList.addAll((ArrayList) input.readObject());
					input.close();
				} catch (Exception e1) {
				}

				for (int i = 0; i < booksList.size(); i++) {
					listModel.addElement(booksList.get(i)[0] + " - " + booksList.get(i)[1] + " - " + booksList.get(i)[2]);
				}
				jlist.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent ae) {
						title.setText((String) booksList.get(jlist.getSelectedIndex())[1]);
						author.setText((String) booksList.get(jlist.getSelectedIndex())[2]);
						booknumber.setText((String) booksList.get(jlist.getSelectedIndex())[0]);
						bookSelected = jlist.getSelectedIndex();
						booksPopup.dispose();
					}

				});
				booksPopup.add(pane);
				booksPopup.setVisible(true);
			}

		});

		setVisible(true);
	}

	private void validateBook() {
		if (!title.getText().isEmpty() && !author.getText().isEmpty()) {

			ArrayList<Object[]> list = new ArrayList();
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.dat"));
				list.addAll((ArrayList) input.readObject());
				input.close();
			} catch (Exception e1) {
			}

			boolean found = false;
			for (int i = 0; i < list.size(); i++) {
				String atitle = (String) list.get(i)[1];
				String aauthor = (String) list.get(i)[2];
				if (atitle.equalsIgnoreCase(title.getText()) && aauthor.equalsIgnoreCase(author.getText())) {
					booknumber.setText((String) list.get(i)[0]);
					found = true;
					bookSelected = i;
				}
			}

			if (!found) {
				JOptionPane.showMessageDialog(null, "Book Not Found");
				title.setText("");
				author.setText("");
				booknumber.setText("");
				title.grabFocus();
				bookSelected = -1;
			}
		}
	}

	public static void main(String[] args) {
		new LibrarySystemMainFrame();
	}
}
