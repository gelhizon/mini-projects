import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JPizza extends JFrame implements ActionListener{
	JComboBox<String> sizes = new JComboBox<String>(new String[]{"Small - $7", "Medium - $9", "Large - $11", "Extra Large - $14"});
	JComboBox<String> toppings = new JComboBox<String>(new String[]{"Cheese - $0", "Peperroni - $1", "Bellpepper - $1", "Onion - $1", "Olives - $1"});
	JLabel label = new JLabel("Price: $7");
	int price = 0;
	public JPizza(){
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		sizes.addActionListener(this);
		toppings.addActionListener(this);
		add(label);
		add(sizes);
		add(toppings);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		price = 0;
		if(sizes.getSelectedIndex() == 0)
			price += 7;
		if(sizes.getSelectedIndex() == 1)
			price += 9;
		if(sizes.getSelectedIndex() == 2)
			price += 11;
		if(sizes.getSelectedIndex() == 3)
			price += 14;
		if(toppings.getSelectedIndex() != 0)
			price += 1;
		label.setText("Price: $" + price);
		pack();
	}
	public static void main(String[]args){
		JPizza pizza = new JPizza();
	}
}