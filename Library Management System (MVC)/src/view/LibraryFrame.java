package view;


import java.awt.BorderLayout;

import javax.swing.JFrame;

public class LibraryFrame extends JFrame {
	private LibraryBorrowersPanel borrowersPanel;

	public LibraryFrame() {
		super("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		borrowersPanel = new LibraryBorrowersPanel();
		
		add(borrowersPanel, BorderLayout.CENTER);
	}

	public LibraryBorrowersPanel getBorrowersPanel() {
		return borrowersPanel;
	}
	
}
