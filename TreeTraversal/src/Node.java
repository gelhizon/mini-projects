public class Node {
	public Node left;
	public Node right;
	public String data;

	public Node(String data) {
		this.data = data;
	}

	public static String preorder(Node n) {
		if (n != null) {
			return (n.data + preorder(n.left) + preorder(n.right));
		}
		return "";
	}

	public static String inorder(Node n) {
		if (n != null) {
			return inorder(n.left) + n.data + inorder(n.right);
		}
		return "";
	}

	public static String postorder(Node n) {
		if (n != null) {
			return postorder(n.left) + postorder(n.right) + n.data;
		}
		return "";
	}
}