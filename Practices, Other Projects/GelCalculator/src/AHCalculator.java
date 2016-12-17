import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class AHCalculator extends JFrame {
	JLabel number1Label;
	JTextField number1TextField;

	JLabel number2Label;
	JTextField number2TextField;

	JLabel resultLabel;
	JTextField resultTextField;

	JButton additionButton;
	JButton subtractionButton;
	JButton multiplicationButton;
	JButton divisionButton;

	JPanel inputPanel;
	JPanel buttonsPanel;
	JPanel calculatorPanel;

	public AHCalculator() {
		initializeComponents();
		setComponentProperties();
		setComponentLayout();
		setVisible(true);
	}

	public void initializeComponents() {
		number1Label = new JLabel("1st Number");
		number1TextField = new JTextField(8);

		number2Label = new JLabel("2nd Number");
		number2TextField = new JTextField(8);

		resultLabel = new JLabel("Result");
		resultTextField = new JTextField(8);

		additionButton = new JButton("+");
		subtractionButton = new JButton("-");
		multiplicationButton = new JButton("×");
		divisionButton = new JButton("/");

		inputPanel = new JPanel(new GridBagLayout());
		buttonsPanel = new JPanel(new GridBagLayout());
		calculatorPanel = new JPanel(new GridBagLayout());
	}

	public void setComponentProperties() {
		setTitle("AHizon Calculator");
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
			
		additionButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				operationPerformed(new Addition(Double.parseDouble(number1TextField.getText()), Double.parseDouble(number2TextField.getText())));
			}
		});
		subtractionButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				operationPerformed(new Subtraction(Double.parseDouble(number1TextField.getText()), Double.parseDouble(number2TextField.getText())));
			}
		});
		multiplicationButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				operationPerformed(new Multiplication(Double.parseDouble(number1TextField.getText()), Double.parseDouble(number2TextField.getText())));
			}
		});
		divisionButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				operationPerformed(new Division(Double.parseDouble(number1TextField.getText()), Double.parseDouble(number2TextField.getText())));
			}
		});
	}

	public void setComponentLayout() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 1;
		c.weighty = 1;
		
		c.anchor = GridBagConstraints.EAST;
		c.gridy = 0;
		c.gridx = 0;
		inputPanel.add(number1Label, c);
		c.gridx = 1;
		inputPanel.add(number1TextField, c);

		c.gridy = 1;
		c.gridx = 0;
		inputPanel.add(number2Label, c);
		c.gridx = 1;
		inputPanel.add(number2TextField, c);

		c.gridy = 2;
		c.gridx = 0;
		inputPanel.add(resultLabel, c);
		c.gridx = 1;
		inputPanel.add(resultTextField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		buttonsPanel.add(additionButton, c);
		c.gridx = 1;
		buttonsPanel.add(subtractionButton, c);

		c.gridy = 1;
		c.gridx = 0;
		buttonsPanel.add(multiplicationButton, c);
		c.gridx = 1;
		buttonsPanel.add(divisionButton, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridy = 0;
		c.gridx = 0;
		calculatorPanel.add(inputPanel, c);
		
		c.gridy = 1;
		calculatorPanel.add(new JSeparator(JSeparator.HORIZONTAL), c);
		c.gridy = 2;
		calculatorPanel.add(buttonsPanel, c);

		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		add(calculatorPanel, c);

		pack();
	}
	
	public void operationPerformed(Operation operation){
		resultTextField.setText(Double.toString(operation.getResults()));
	}

	public static void main(String[] args) {
		new AHCalculator();
	}

}
