import java.awt.Color;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class SelectionDialog extends JDialog {
	protected DefaultListModel listModel = new DefaultListModel();
	protected JList jList = new JList(listModel);
	private JScrollPane pane = new JScrollPane(jList);
	protected ArrayList<String[]> booksList = new ArrayList();

	SelectionDialog(Window window, String title, String fileName) {
		super(window, title);
		setSize(300, 400);
		setLocationRelativeTo(window);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent event) {
			}

			public void windowLostFocus(WindowEvent event) {
				setVisible(false);
			}
		});

		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
			booksList.addAll((ArrayList) input.readObject());
			input.close();
		} catch (Exception e1) {
		}

		jList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ae) {
				selectPerformed();
				setVisible(false);
			}
		});

		add(pane);
	}

	public abstract void selectPerformed();

	public int getSelectedIndex() {
		return jList.getSelectedIndex();
	}
}
