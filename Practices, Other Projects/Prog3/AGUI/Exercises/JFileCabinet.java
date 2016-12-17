import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JFileCabinet extends JFrame implements ActionListener{
	JButton[] b = new JButton[26];
	JPanel[] p = new JPanel[5];
	JLabel l = new JLabel("Folder X");
	public JFileCabinet(){
		for(char c = 'A'; c <= 'Z'; c++){
			b[c - 'A'] = new JButton(Character.toString(c));
			b[c - 'A'].addActionListener(this);
		}
		for(int i = 0; i < p.length; i++)
			p[i] = new JPanel();
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++)
				p[i].add(b[j + (i * 6)]);
			add(p[i]);
		}
		p[4].add(b[24]);
		p[4].add(b[25]);	
		p[4].add(l);

		add(p[4]);
		setTitle("File Cabinet");
		setSize(400, 300);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		JButton source = (JButton) e.getSource();
		l.setText("Folder " + source.getText());
	}
	public static void main(String[]args){
		JFileCabinet f = new JFileCabinet();
		f.setVisible(true);
	}
}