import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

public class VLSMFrame implements Runnable {
	JFrame frame;
	JTextField tf;
	JButton bt;
	JTable table;
	DefaultTableModel model;
	JTableHeader th;
	JPanel row1, row2, row3;


	public VLSMFrame() {
		model = new DefaultTableModel();
		table = new JTable(model);
		frame = new JFrame("VLSM Calculator --- BSCS - 2A");
		tf = new JTextField(25);
		bt = new JButton("Calculate!");
		row1 = new JPanel();
		row2 = new JPanel();
		row3 = new JPanel();

		model.addColumn("Test1");
		model.addColumn("Test2");
		model.addColumn("Test3");
		th = table.getTableHeader();
		th.setBackground(Color.BLUE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		row1.setLayout(new FlowLayout());
		row1.add(tf);
		row1.add(bt);
		frame.add(row1);
		frame.add(new JScrollPane(table));

		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void run() {

	}

	public static void main(String[] args) {
		new Thread(new VLSMFrame()).start();
	}
}
