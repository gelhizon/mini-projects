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

public class BooksList extends JFrame {
	private final String[] columnNames = { "Book Number", "Book Title", "Book Author" };
	private Object[][] data;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane pane;
	private JButton back, trash;

	public BooksList() {
		super("Borrowers");
		setSize(800, 600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		final ArrayList list = new ArrayList();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("books.dat"));
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
		back = new JButton("Back");
		trash = new JButton("Trash");

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		trash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ListSelectionModel row = table.getSelectionModel();
				if (!row.isSelectionEmpty()) {
					int s = row.getMinSelectionIndex();
					list.remove(s);
					model.removeRow(s);
					try {
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("books.dat"));
						output.writeObject(list);
						output.close();
					} catch (Exception ex) {

					}
				}
			}
		});

		JPanel buttons = new JPanel();
		buttons.add(trash);
		buttons.add(back);
		add(buttons, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);

		setVisible(true);
	}
}
