import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistrationForm extends JFrame implements ActionListener {
    private JTextField SSNumberField, firstNameField, middleNameField, lastNameTF, civilstatusTF, addressTF, postalcodeTF, emailTF, fatherTF, motherTF, spouseTF, children1TF, children2TF, children3TF;
    private JComboBox birthDateDayCB, birthDateYearCB, birthDateMonthCB;
    private JComboBox sexCB;
    private JButton submitBtn, cancelBtn;
    private Member member;
    
    private JPanel infomationPanel;
    private JPanel beneficiariesPanel;
    private JPanel bottomPanel;
    
    // Constants
    private final Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December",};
    private final Integer[] years = {2013, 2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001, 2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980, 1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 1971, 1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961, 1960, 1959, 1958, 1957, 1956, 1955, 1954, 1953, 1952, 1951, 1950, 1949, 1948, 1947, 1946, 1945, 1944, 1943, 1942, 1941, 1940, 1939, 1938, 1937, 1936, 1935, 1934, 1933, 1932, 1931, 1930, 1929, 1928, 1927, 1926, 1925, 1924, 1923, 1922, 1921, 1920, 1919, 1918, 1917, 1916, 1915, 1914, 1913, 1912, 1911, 1910, 1909, 1908, 1907, 1906, 1905, 1904, 1903, 1902, 1901, 1900};
    private final String[] sex = {"Male", "Female"};
    
    public RegistrationForm(Member member) {
		//Initialize JFrame
		super("Register");
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Member
		this.member = member;
		
		//Initialize JTextFields
		SSNumberField = new JTextField(20);
		firstNameField = new JTextField(20);
		middleNameField = new JTextField(20);
		lastNameTF = new JTextField(20);
		civilstatusTF = new JTextField(20);
		addressTF = new JTextField(20);
		postalcodeTF = new JTextField(20);
		emailTF = new JTextField(20);
		fatherTF = new JTextField(20);
		motherTF = new JTextField(20);
		spouseTF = new JTextField(20);
		children1TF = new JTextField(20);
		children2TF = new JTextField(20);
		children3TF = new JTextField(20);
		
		// Initialize JComboBoxs
		birthDateDayCB = new JComboBox(days);
		birthDateMonthCB = new JComboBox(months);
		birthDateYearCB = new JComboBox(years);
		sexCB = new JComboBox(sex);
		
		// Initialize Buttons
		submitBtn = new JButton("Submit");
		cancelBtn = new JButton("Cancel");
		
		// Initialize Panels
		infomationPanel = new JPanel(new GridBagLayout());
		beneficiariesPanel = new JPanel(new GridBagLayout());
		bottomPanel = new JPanel();
		
		// topPanel.add Listeners
		birthDateMonthCB.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent event){
			validateDay();
		    }
		});
		birthDateYearCB.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent event){
			validateDay();
		    }
		});
		// Add Components to JFrame
		/*
		 * OUTLINE
		 * 4 Columns 6 Rows
		 *    |1 |2 |3 |4 |
		 *    |2 |  |  |  |
		 *    |3 |  |  |  |
		 *    |4 |  |  |  |
		 *    |5 |  |  |  |
		 *    |6 |  |  |  |
		 */
		infomationPanel.setBorder(BorderFactory.createTitledBorder("Personal Information"));
		beneficiariesPanel.setBorder(BorderFactory.createTitledBorder("Beneficiaries"));
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = 0;
		infomationPanel.add(new JLabel("SS Number:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(SSNumberField, c);
		
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridx = 0;
		infomationPanel.add(new JLabel("First Name:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(firstNameField, c);
		
		c.gridwidth = 1;
		c.gridy = 2;
		c.gridx = 0;
		infomationPanel.add(new JLabel("Middle Name:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(lastNameTF, c);
		
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridx = 0;
		infomationPanel.add(new JLabel("Last Name:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(middleNameField, c);
		
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridx = 0;
		infomationPanel.add(new JLabel("Sex:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(sexCB, c);
		
		c.gridwidth = 1;
		c.gridy = 5;
		c.gridx = 0;
		infomationPanel.add(new JLabel("Civil Status:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(civilstatusTF, c);
		
		c.gridwidth = 1;
		c.gridy = 6;
		c.gridx = 0;
		infomationPanel.add(new JLabel("Postal Code:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(postalcodeTF, c);
		
		c.gridwidth = 1;
		c.gridy = 7;
		c.gridx = 0;
		infomationPanel.add(new JLabel("Address:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(addressTF, c);
		
		c.gridwidth = 1;
		c.gridy = 8;
		c.gridx = 0;
		infomationPanel.add(new JLabel("Email Address:"), c);
		c.gridwidth = 3;
		c.gridx = 1;
		infomationPanel.add(emailTF, c);
		
		c.gridwidth = 1;
		c.gridy = 9;
		c.gridx = 0;
		infomationPanel.add(new JLabel("Birthday:"), c);
		
		c.gridx = 1;
		infomationPanel.add(birthDateMonthCB, c);
		c.gridx = 2;
		infomationPanel.add(birthDateDayCB, c);
		c.gridx = 3;
		infomationPanel.add(birthDateYearCB, c);

		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = 0;
		beneficiariesPanel.add(new JLabel("Father:"), c);
		c.gridx = 1;
		beneficiariesPanel.add(fatherTF, c);
				
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridx = 0;
		beneficiariesPanel.add(new JLabel("Mother:"), c);
		
		c.gridx = 1;
		beneficiariesPanel.add(motherTF, c);
		c.gridwidth = 1;
		c.gridy = 2;
		c.gridx = 0;
		beneficiariesPanel.add(new JLabel("Spouse:"), c);
		
		c.gridx = 1;
		beneficiariesPanel.add(spouseTF, c);
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridx = 0;
		beneficiariesPanel.add(new JLabel("Children:"), c);
		c.gridx = 1;
		beneficiariesPanel.add(children1TF, c);
		
		bottomPanel.add(submitBtn);
		bottomPanel.add(cancelBtn);
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = 0;
		add(infomationPanel, c);
		c.gridy = 1;
		add(beneficiariesPanel, c);
		c.gridy = 2;
		add(bottomPanel, c);
		
		// Create Border
		// Pack to Fit on Window
		pack();
		
		
    }
    
    private void validateDay(){
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.MONTH, birthDateMonthCB.getSelectedIndex());
		cal.set(Calendar.YEAR, 2013 - birthDateYearCB.getSelectedIndex());
		int numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		while(numDays < birthDateDayCB.getItemCount()){
		    birthDateDayCB.removeItemAt(birthDateDayCB.getItemCount() - 1);
		    System.out.println("REMOVE DAY");
		}
		
	    while(numDays > birthDateDayCB.getItemCount()){
			birthDateDayCB.addItem(birthDateDayCB.getItemCount() + 1);
			System.out.println("ADD DAY");
	    }
    }
    
    public void actionPerformed(ActionEvent event) {
    	
    }

    public static void main(String[] args){
    	new RegistrationForm(new Member()).setVisible(true);
	}

}
