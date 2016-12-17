import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {
	private BorrowerPanel pnlBorrow;
	private AddStudentPanel pnlAddStudent;
	private SelectionDialog studentDialog;

	public View() {
		super("Library Management System");
		setLayout(new BorderLayout());
		setSize(800, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		pnlBorrow = new BorrowerPanel();
		pnlAddStudent = new AddStudentPanel(this);
		studentDialog = new SelectionDialog(this);

		add(pnlBorrow, BorderLayout.CENTER);
	}

	public BorrowerPanel getPnlBorrow() {
		return pnlBorrow;
	}

	public AddStudentPanel getPnlAddStudent() {
		return pnlAddStudent;
	}

	public SelectionDialog getStudentDialog() {
		return studentDialog;
	}
}
