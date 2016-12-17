import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JEmail extends JFrame implements ActionListener{
	JLabel toLabel = new JLabel("To: ");
	JLabel subjectLabel = new JLabel("Subject: ");
	JTextField to = new JTextField(15);
	JTextField subject = new JTextField(15);
	JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel subjectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextArea ta = new JTextArea(20, 20);
	JButton send = new JButton("Send");
	public JEmail(){
		setTitle("Oh No Email");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		toPanel.add(toLabel);
		toPanel.add(to);
		subjectPanel.add(subjectLabel);
		subjectPanel.add(subject);
		send.addActionListener(this);
		add(toPanel);
		add(subjectPanel);
		add(ta);
		add(send);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		ta.append("\n\nMail has been sent!");
	}
	public static void main(String[] args){
		JEmail frame = new JEmail();
	}
}