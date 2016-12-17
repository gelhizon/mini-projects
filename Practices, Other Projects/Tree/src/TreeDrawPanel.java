import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TreeDrawPanel extends JPanel {
	private Tree root;
	private final int yGap = 80;

	public TreeDrawPanel() {
		super();
	}

	public void setRoot(Tree root) {
		this.root = root;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int center = Math.round(getWidth() / 2);
		g.setFont(new Font("Segoe UI", Font.BOLD, 20));

		show(g, root, center, 40, center / 2);
	}

	public void show(Graphics g, Tree n, int x, int y, int d) {
		if (n != null) {
			g.drawString(n.getData(), x, y);
			FontMetrics fm = g.getFontMetrics();
			int fontHeight = fm.getHeight();
			int fontWidth = fm.charWidth(n.getData().charAt(0));
			g.drawOval(x - 20, y -fontHeight, fontWidth + 40, 40);
			System.out.println(fontHeight);
			if (n.getLeft() != null) {
				g.drawLine(x, y, x - d, y + yGap);
				show(g, n.getLeft(), x - d, y + yGap, d / 2);
			}
			if (n.getRight() != null) {
				g.drawLine(x, y, x + d, y + yGap);
				show(g, n.getRight(), x + d, y + yGap, d / 2);
			}
		}
	}
}
