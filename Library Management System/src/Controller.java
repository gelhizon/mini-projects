import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class Controller {
	private Model model;
	private View view;

	public Controller() {
		model = new Model();
		view = new View();
		view.setVisible(true);

		final BorrowerPanel pnlBorrow = view.getPnlBorrow();
		pnlBorrow.getBtnBorrow().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			}
		});
		final AddStudentPanel pnlStudent = view.getPnlAddStudent();
		pnlBorrow.getBtnAddStudents().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				pnlStudent.setVisible(true);
			}
		});
		pnlBorrow.getBtnStudentsSelection().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				view.getStudentDialog().getListModel().clear();
				view.getStudentDialog().getList().clearSelection();
				for (Map.Entry<String, Object> e : model.getStudentFM().getTable().entrySet()) {
					view.getStudentDialog().getListModel().addElement(((Student) e.getValue()).getStudentNumber() + "-" + ((Student) e.getValue()).getLastName() + " " + ((Student) e.getValue()).getFirstName());
				}
				view.getStudentDialog().setVisible(true);
			}
		});
		view.getStudentDialog().getList().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				String number;
				int i = view.getStudentDialog().getList().getSelectedIndex();
				String studentid = view.getStudentDialog().getListModel().get(i).split("-")[0];
				pnlBorrow.getTxtStudentNumber().setText(((Student) model.getStudentFM().getTable().get(studentid)).getStudentNumber());
			}
		});
		pnlStudent.getBtnAddStudent().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Student student = new Student(pnlStudent.getTxtFirstName().getText(), pnlStudent.getTxtLastName().getText(), pnlStudent.getTxtMiddleName().getText(), pnlStudent.getTxtGender().getText(), pnlStudent.getTxtDateOfBirth().getText(), pnlStudent.getTxtAddress().getText(), pnlStudent.getTxtContactNumber().getText(), pnlStudent.getTxtStudentNumber().getText());
				model.getStudentFM().addStudent(student);
				
				pnlStudent.setVisible(false);
			}
		});

	}

	public static void main(String[] args) {
		new Controller();
	}
}
