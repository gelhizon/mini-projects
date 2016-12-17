import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BorrowersFrame extends JFrame {
	private final String[] columnNames = { "Date Borrowed", "Student Number", "Name", "Address", "Contact Number", "Book Number", "Title", "Author" };
	private Object[][] data;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane pane;
	private JButton returned;
	private JButton back;

	public BorrowersFrame() {
		super("Borrowers");
		setSize(800, 600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		final ArrayList list = new ArrayList();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("borrow.dat"));
			list.addAll((ArrayList) input.readObject());
			input.close();
		} catch (Exception e) {

		}

		data = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			data[i] = (Object[]) list.get(i);
		}

		model = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(model);
		pane = new JScrollPane(table);
		returned = new JButton("Return Book");
		back = new JButton("Back");

		returned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ListSelectionModel rowSM = table.getSelectionModel();
				if (!rowSM.isSelectionEmpty()) {
					int s = rowSM.getMinSelectionIndex();
					ArrayList bookList = new ArrayList();
					try {
						ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.dat"));
						bookList.addAll((ArrayList) input.readObject());
						input.close();
					} catch (Exception e1) {

					}
					
					bookList.add(new Object[] { model.getValueAt(s, 5), model.getValueAt(s, 6), model.getValueAt(s, 7) });
					try {
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("books.dat"));
						output.writeObject(bookList);
						output.close();
					} catch (Exception ex) {

					}
					list.remove(s);
					model.removeRow(s);
					try {
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("borrow.dat"));
						output.writeObject(list);
						output.close();
					} catch (Exception ex) {

					}

				}
			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		JPanel buttons = new JPanel();
		buttons.add(returned);
		buttons.add(back);
		add(buttons, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		new BorrowersFrame();
	}
}
