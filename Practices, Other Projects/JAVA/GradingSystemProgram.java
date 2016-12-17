import javax.swing.*;
import java.awt.*;

public class GradingSystemProgram extends Thread{
	GradingSystem gs;
	JFrame frame;
	JLabel mquizlabel;
	JLabel mexamlabel;
	JLabel mseatworklabel;
	JLabel mcasestudieslabel;
	JLabel mlabactivitieslabel;
	JLabel mlabexamlabel;
	
	public void run(){
		init();

		frame.setLayout(new FlowLayout());
		frame.setSize(100,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);

		frame.add(mquizlabel);
		frame.add(mexamlabel);
		frame.add(mseatworklabel);
		frame.add(mcasestudieslabel);
		frame.add(mlabactivitieslabel);
		frame.add(mlabexamlabel);	  

		frame.setVisible(true);  
	}

	public void init(){
		gs = new GradingSystem();
		frame = new JFrame("My Grading System");
		mquizlabel = new JLabel("Midterm Quiz");
		mexamlabel = new JLabel("Midterm Exam");
		mseatworklabel = new JLabel("Midterm Seatwork");
		mcasestudieslabel = new JLabel("Midterm Case Studies");
		mlabactivitieslabel = new JLabel("Midterm Lab Activities");
		mlabexamlabel = new JLabel("Midterm Lab Exam");
	}

	public static void main(String[] args){
		(new GradingSystemProgram()).start();
	}
}