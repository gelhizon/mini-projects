import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JEmail2 extends JFrame implements ActionListener{
	JLabel toLabel = new JLabel("To: ");
	JLabel subjectLabel = new JLabel("Subject: ");
	JTextField to = new JTextField(15);
	JTextField subject = new JTextField(15);
	JPanel labels = new JPanel();
	JPanel textfields = new JPanel();
	JPanel toparea = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextArea ta = new JTextArea(10, 10);
	JButton send = new JButton("Send");
	JButton clear = new JButton("Clear");
	JPanel clearPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	public JEmail2(){
		setTitle("Oh No Email");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));
		textfields.setLayout(new BoxLayout(textfields, BoxLayout.PAGE_AXIS));
		labels.add(toLabel);
		labels.add(subjectLabel);
		textfields.add(to);
		textfields.add(subject);
		toparea.add(labels);
		toparea.add(textfields);
		send.addActionListener(this);
		clear.addActionListener(this);
		clearPanel.add(clear);
		clearPanel.add(send);
		add(toparea);
		add(ta);
		add(clearPanel);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if(source == send)
			ta.append("\n\nMail has been sent!");
		if(source == clear){
			to.setText("");
			subject.setText("");
			ta.setText("");
		}
	}
	public static void main(String[] args){
		JEmail2 frame = new JEmail2();
	}
}