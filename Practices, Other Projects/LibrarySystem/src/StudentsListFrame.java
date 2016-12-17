import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentsListFrame extends JFrame {
	private final String[] columnNames = { "Student Number", "First Name", "Last Name", "Middle Name", "Gender", "Address", "Contact Number" };
	private Object[][] data;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane pane;
	private JButton back;

	public StudentsListFrame() {
		super("Borrowers");
		setSize(800, 600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		final ArrayList list = new ArrayList();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("students.dat"));
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

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		JPanel buttons = new JPanel();
		buttons.add(back);
		add(buttons, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		new StudentsListFrame();
	}
}
