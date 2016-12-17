import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CarsWithFrame extends Thread implements ActionListener{
	IllicitCarDealers cardealer;
	JFrame frame;
	JLabel label1;
	JLabel label2;
	JTextField textfield1;
	JComboBox<String> combo;
	JButton button;

	public void run(){
		CarsWithFrame cars = new CarsWithFrame();
		cardealer = new IllicitCarDealers();
		frame = new JFrame("Illegal Car Dealers Inc.");
		label1 = new JLabel("Sales Amount");
		label2 = new JLabel("Select Car Model");
		String[] carsbrand = { "1 - Bmw", "2 - Rav", "3 - Honda Civic", "4 - Pajero", "5 - Other" };
		combo = new JComboBox<String>(carsbrand);
		button = new JButton("Get Reward");
		textfield1 = new JTextField(10);

		button.addActionListener(this);
	    combo.setSelectedIndex(0);
	    frame.setLayout(new FlowLayout());
	    frame.add(label1);
	    frame.add(textfield1);
	    frame.add(label2);
	    frame.add(combo);
	    frame.add(button);

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.pack();
	    frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
    	cardealer.setModel(Character.getNumericValue(((String)combo.getSelectedItem()).charAt(0)));
    	cardealer.setSalesAmount(Double.parseDouble(textfield1.getText()));
    	JOptionPane.showMessageDialog(null,"Your reward is " +cardealer.getReward());
    }

   	public static void main(String[]args){
		(new CarsWithFrame()).start();
	}
}