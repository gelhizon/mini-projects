package gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import random.NumberGenerator;
import sorter.BubbleSorter;
import sorter.InsertionSorter;
import sorter.QuickSorter;
import sorter.SelectionSorter;
import sorter.Sorter;

public class SorterGUI extends JFrame {

	// INPUTs
	private JLabel lblArraySize;
	private JTextField tfArraySize;
	private JButton btnSubmit;
	private int[] numbers;

	// NUMBER GENERATOR CONSTANTS
	private final int MIN = 10;
	private final int MAX = 100;

	// OUPUTs
	private JTextArea taOutputList;
	private JScrollPane spOutputList;
	private JButton btnNew;
	private JTextArea taRandomNumbers;

	// SORTER SELECTOR
	private JLabel lblSelection;
	private ButtonGroup bgSelection;
	private JRadioButton rbBubble;
	private JRadioButton rbSelection;
	private JRadioButton rbInsertion;
	private JRadioButton rbQuick;

	// LAYOUTs
	private CardLayout cardLayout;
	private JPanel panelInput;
	private JPanel panelOutput;
	private JPanel panelOutputTopLeft;
	private JPanel panelOutputTopRight;

	// ABOUT
	private JButton btnHizon;
	private JButton btnLic;
	
	// BETA
	private JCheckBox checkbox;

	public SorterGUI() {
		// Initialization
		lblArraySize = new JLabel("ENTER ARRAY SIZE");
		tfArraySize = new JTextField(5);
		btnSubmit = new JButton("SUBMIT");
		numbers = null;

		taOutputList = new JTextArea(15, 15);
		spOutputList = new JScrollPane(taOutputList);
		btnNew = new JButton("NEW");
		taRandomNumbers = new JTextArea(1, 15);

		lblSelection = new JLabel("Select:");
		bgSelection = new ButtonGroup();
		rbBubble = new JRadioButton("Bubble");
		rbSelection = new JRadioButton("Selection");
		rbInsertion = new JRadioButton("Insertion");
		rbQuick = new JRadioButton("Quick");

		cardLayout = new CardLayout();
		panelInput = new JPanel();
		panelOutput = new JPanel();
		panelOutputTopLeft = new JPanel();
		panelOutputTopRight = new JPanel();

		btnHizon = new JButton("About");
		btnLic = new JButton("Enter License");
		
		checkbox = new JCheckBox("Symbolize");

		// Setup JFrame
		setTitle("Hizon <3 Badillos Sorter");
		setMinimumSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Setup TextAreas
		taRandomNumbers.setEditable(false);
		taRandomNumbers.setOpaque(false);
		taRandomNumbers.setLineWrap(true);
		taRandomNumbers.setWrapStyleWord(true);
		taOutputList.setEditable(false);
		taOutputList.setLineWrap(true);
		taOutputList.setWrapStyleWord(true);
		taOutputList.setTabSize(4);

		// Group RadioButtons
		bgSelection.add(rbBubble);
		bgSelection.add(rbSelection);
		bgSelection.add(rbInsertion);
		bgSelection.add(rbQuick);

		// Setup Fonts
		tfArraySize.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNew.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		rbBubble.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		rbSelection.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		rbInsertion.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		rbQuick.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		taOutputList.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		taRandomNumbers.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		checkbox.setFont(new Font("Lucida Console", Font.PLAIN, 12));

		// Setup Extras
		btnHizon.setFont(new Font("Lucida Console", Font.PLAIN, 8));
		btnLic.setFont(new Font("Lucida Console", Font.PLAIN, 8));

		// Setup Layouts
		// Input Area
		GridBagConstraints constraints = new GridBagConstraints();

		panelInput.setLayout(new GridBagLayout());

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		panelInput.add(lblArraySize, constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		panelInput.add(tfArraySize, constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		panelInput.add(btnSubmit, constraints);

		// Output Area
		panelOutput.setLayout(new GridBagLayout());

		panelOutputTopLeft.add(btnNew);
		panelOutputTopLeft.add(lblSelection);
		panelOutputTopLeft.add(rbBubble);
		panelOutputTopLeft.add(rbSelection);
		panelOutputTopLeft.add(rbInsertion);
		panelOutputTopLeft.add(rbQuick);
		panelOutputTopLeft.add(checkbox);

		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		panelOutput.add(panelOutputTopLeft, constraints);

		panelOutputTopRight.add(btnLic);
		panelOutputTopRight.add(btnHizon);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		panelOutput.add(panelOutputTopRight, constraints);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		panelOutput.add(new JSeparator(JSeparator.HORIZONTAL), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.gridwidth = 2;
		panelOutput.add(taRandomNumbers, constraints);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 2;
		panelOutput.add(spOutputList, constraints);

		Container container = getContentPane();
		container.setLayout(cardLayout);
		container.add(panelInput);
		container.add(panelOutput);

		// Setup Listeners
		tfArraySize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				submitPerformed();
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				submitPerformed();
			}
		});
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				newPerformed();
			}
		});
		rbBubble.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				sortingPerformed(new BubbleSorter());
			}
		});
		rbSelection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				sortingPerformed(new SelectionSorter());
			}
		});
		rbInsertion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				sortingPerformed(new InsertionSorter());
			}
		});
		rbQuick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				sortingPerformed(new QuickSorter());
			}
		});
		btnHizon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						"Version 1\n10-11-2013\n©2013 Hizon, Angelito",
						"About", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnLic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String lic = JOptionPane.showInputDialog(null, "Enter your license");	
				if(lic.equals("helloworld")){
					rbBubble.setEnabled(true);
					rbInsertion.setEnabled(true);
					rbSelection.setEnabled(true);
					rbQuick.setEnabled(true);
					btnLic.setVisible(false);
				}
			}
		});

		rbBubble.setEnabled(false);
		rbInsertion.setEnabled(false);
		rbSelection.setEnabled(false);
		rbQuick.setEnabled(false);


		// Show JFrame
		setVisible(true);
	}

	public void submitPerformed() {
		try {
			// Create new array
			numbers = new int[Integer.parseInt(tfArraySize.getText())];

			if (numbers.length <= 1) {
				JOptionPane.showMessageDialog(null,
						"Sze cannot be less than 2.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			NumberGenerator.generateNumbers(numbers, MIN, MAX);

			// flip
			cardLayout.next(getContentPane());

			// Display Generated Numbers
			taOutputList.setText("");
			taRandomNumbers.setText("Generated: " + Arrays.toString(numbers));
			JOptionPane.showMessageDialog(null, numbers.length
					+ " numbers successfully generated.", "INFO",
					JOptionPane.INFORMATION_MESSAGE);

			bgSelection.clearSelection();
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Invalid inputted", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void newPerformed() {
		cardLayout.next(getContentPane());
		tfArraySize.setText("");
	}

	public void sortingPerformed(Sorter sorter) {
		if(checkbox.isSelected())
			sorter.format.setSymbolized(true);
		sorter.setNumbers(numbers);
		taOutputList.setText(sorter.getList());
		taOutputList.setCaretPosition(0);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SorterGUI();
			}
		});
	}

}
