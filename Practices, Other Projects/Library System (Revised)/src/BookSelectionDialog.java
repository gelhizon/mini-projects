import java.awt.Window;

import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BookSelectionDialog extends SelectionDialog {
	BookSelectionDialog(Window window, String title, String fileName) {
		super(window, title, fileName);
		
		for (int i = 0; i < booksList.size(); i++) {
			listModel.addElement(booksList.get(i)[0] + " " + booksList.get(i)[1] + " " + booksList.get(i)[2]);
		}
	}
	
	public void selectPerformed() {
		LibraryBorrowersPanel.booknumberTextField.setText(booksList.get(getSelectedIndex())[0]);
		LibraryBorrowersPanel.titleTextField.setText(booksList.get(getSelectedIndex())[1]);
		LibraryBorrowersPanel.authorTextField.setText(booksList.get(getSelectedIndex())[2]);
	}

}
