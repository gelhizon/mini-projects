public class Tree {
	public Tree left;
	public Tree right;
	public String data;

	public Tree(String data) {
		this.data = data;
	}

	public static String preorder(Tree n) {
		if (n != null) {
			return (n.data + preorder(n.left) + preorder(n.right));
		}
		return "";
	}

	public static String inorder(Tree n) {
		if (n != null) {
			return inorder(n.left) + n.data + inorder(n.right);
		}
		return "";
	}

	public static String postorder(Tree n) {
		if (n != null) {
			return postorder(n.left) + postorder(n.right) + n.data;
		}
		return "";
	}
}