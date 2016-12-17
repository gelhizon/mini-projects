import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class LibraryFrame extends JFrame {
	private LibraryBorrowersPanel borrowersPanel;

	public LibraryFrame() {
		// Setup Frame
		super("Library Management System");
		setSize(800, 600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Instantiate Components
		borrowersPanel = new LibraryBorrowersPanel();
		
		// Add Components
		add(borrowersPanel, BorderLayout.CENTER);
	}
}
