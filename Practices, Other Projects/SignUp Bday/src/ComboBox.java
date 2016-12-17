import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComboBox {
	JComboBox mcombo;
	JComboBox dcombo;
	JComboBox ycombo;
	JTextField txt;
	String mtxt;
	String dtxt;
	String ytxt;

	public static void main(String[] args) {
		ComboBox b = new ComboBox();
	}

	public ComboBox() {
		String mth[] = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		String day[] = new String[31];
		String year[] = new String[42];

		for (int i = 0; i < 31; i++) {
			day[i] = Integer.toString(i + 1);
		}
		for (int i = 0; i < 42; i++) {
			year[i] = Integer.toString(i + 1971);
		}
		JFrame frame = new JFrame("Creating a JComboBox Component");

		JPanel panel = new JPanel();
		mcombo = new JComboBox(mth);
		mcombo.setBackground(Color.gray);
		mcombo.setForeground(Color.red);
		dcombo = new JComboBox(day);
		dcombo.setBackground(Color.gray);
		dcombo.setForeground(Color.red);
		ycombo = new JComboBox(year);
		ycombo.setBackground(Color.gray);
		ycombo.setForeground(Color.red);
		txt = new JTextField(10);
		panel.add(mcombo);
		panel.add(dcombo);
		panel.add(ycombo);
		panel.add(txt);
		frame.add(panel);
		mcombo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent ie) {
				mtxt = (String) mcombo.getSelectedItem();
				txt.setText(mtxt + " " + dtxt + ", " + ytxt);
			}
		});
		dcombo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent ie) {
				dtxt = (String) dcombo.getSelectedItem();
				txt.setText(mtxt + " " + dtxt + ", " + ytxt);
			}
		});
		ycombo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent ie) {
				ytxt = (String) ycombo.getSelectedItem();
				txt.setText(mtxt + " " + dtxt + ", " + ytxt);
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}