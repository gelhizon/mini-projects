import javax.swing.*;
import java.awt.*;
import java.awt.event.*;	
public class JMyNewHome extends JFrame implements ActionListener{
	JRadioButton[] models = new JRadioButton[4];
	JRadioButton[] bedrooms = new JRadioButton[3];
	JRadioButton[] garagetype = new JRadioButton[4];
	ButtonGroup[] group = new ButtonGroup[3];
	JPanel[] panel = new JPanel[3];
	JLabel[] label = new JLabel[3];
	JTextField field = new JTextField(20);
	JPanel pricepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	int price = 0;

	public JMyNewHome(){
		super("JMyNewHome");
		models[0] = new JRadioButton("Aspen");
		models[1] = new JRadioButton("Brittany");
		models[2] = new JRadioButton("Colonial");
		models[3] = new JRadioButton("Dartmoor");
		bedrooms[0] = new JRadioButton("Two");
		bedrooms[1] = new JRadioButton("Three");
		bedrooms[2] = new JRadioButton("Four");
		garagetype[0] = new JRadioButton("Zero");
		garagetype[1] = new JRadioButton("One");
		garagetype[2] = new JRadioButton("Two");
		garagetype[3] = new JRadioButton("Three");

		setSize(400, 200);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		for(int i = 0; i < group.length; i++){
			group[i] = new ButtonGroup();
			panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			label[i] = new JLabel();
		}

		field.setEditable(false);
		field.setHorizontalAlignment(JTextField.CENTER);
		field.setText("P 0");
		pricepanel.add(new JLabel("PRICE: "));
		pricepanel.add(field);

		panel[0].add(new JLabel("MODELS: "));
		for(int i = 0; i < models.length; i++){
			models[i].addActionListener(this);
			group[0].add(models[i]);
			panel[0].add(models[i]);
		}
		panel[1].add(new JLabel("BEDROOMS: "));
		for(int i = 0; i < bedrooms.length; i++){
			bedrooms[i].addActionListener(this);
			group[1].add(bedrooms[i]);
			panel[1].add(bedrooms[i]);
		}	
		panel[2].add(new JLabel("GARAGETYPE: "));
		for(int i = 0; i < garagetype.length; i++){
			garagetype[i].addActionListener(this);
			group[2].add(garagetype[i]);
			panel[2].add(garagetype[i]);
		}
		for(int i = 0; i < panel.length; i++){
			add(panel[i]);
		}
		add(pricepanel);
		pack();
	}

	public void actionPerformed(ActionEvent e){
		price = 0;
		if(models[0].isSelected())
			price += 100000;
		if(models[1].isSelected())
			price += 120000;
		if(models[2].isSelected())
			price += 180000;
		if(models[3].isSelected())
			price += 250000;
		if(bedrooms[0].isSelected())
			price += 10500 * 2;
		if(bedrooms[1].isSelected())
			price += 10500 * 3;
		if(bedrooms[2].isSelected())
			price += 10500 * 4;
		if(garagetype[1].isSelected())
			price += 7775;
		if(garagetype[2].isSelected())
			price += 7775 * 2;
		if(garagetype[3].isSelected())
			price += 7775 * 3;
		field.setText("P " + price);
	}

	public static void main(String[] args){
		JMyNewHome a = new JMyNewHome();
		a.setVisible(true);
	}
}