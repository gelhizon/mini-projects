public class TreeHelper {
	public static String preorder(Tree n) {
		if (n != null) {
			return (n.getData() + preorder(n.getLeft()) + preorder(n.getRight()));
		}
		return "";
	}

	public static String inorder(Tree n) {
		if (n != null) {
			return inorder(n.getLeft()) + n.getData() + inorder(n.getRight());
		}
		return "";
	}

	public static String postorder(Tree n) {
		if (n != null) {
			return postorder(n.getLeft()) + postorder(n.getRight()) + n.getData();
		}
		return "";
	}

}
