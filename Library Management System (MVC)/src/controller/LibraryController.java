package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Borrower;
import view.LibraryFrame;


public class LibraryController {
	Borrower model;
	LibraryFrame frame;
	
	public LibraryController() {
		model = new Borrower();
		frame = new LibraryFrame();
		
		frame.getBorrowersPanel().getSubmitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				model.getStudent().setFirstName(frame.getBorrowersPanel().getFirstNameTextField().getText());
				model.getStudent().setLastName(frame.getBorrowersPanel().getLastNameTextField().getText());
				model.getStudent().setMiddleName(frame.getBorrowersPanel().getFirstNameTextField().getText());
				model.getStudent().setFirstName(frame.getBorrowersPanel().getFirstNameTextField().getText());
			}
		});
		
		frame.setVisible(true);
	}
}
