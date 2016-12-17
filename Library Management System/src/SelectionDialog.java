import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SelectionDialog extends JDialog {
	private DefaultListModel<String> listModel;
	private JList list;

	public SelectionDialog(JFrame frame) {
		super(frame, "Select a Student", true);
		setSize(400, 500);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		listModel = new DefaultListModel<String>();
		list = new JList(listModel);
		list.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				list.setSelectedIndex(list.locationToIndex(e.getPoint()));
			}
		});

		list.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				dispose();
			}
		});

		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {
			}

			public void focusLost(FocusEvent e) {
				dispose();
			}
		});

		JScrollPane pane = new JScrollPane(list);
		add(pane);
	}

	public JList getList() {
		return list;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}
}
