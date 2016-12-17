public class Tree {
	private Tree left;
	private Tree right;
	private String data;

	public Tree(String data) {
		this.data = data;
	}

	public Tree getLeft() {
		return left;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	public Tree getRight() {
		return right;
	}

	public void setRight(Tree right) {
		this.right = right;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}