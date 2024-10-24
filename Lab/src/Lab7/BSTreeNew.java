package Lab7;

public class BSTreeNew <T extends Comparable<T>> {
	private TNode<T> root;
	private LinkedStack<T> nextStack = new LinkedStack<>();
	private LinkedStack<T> prefStack = new LinkedStack<>();
	public BSTreeNew() {
		root = null;
	}

	public TNode<T> getRoot() {
		return root;
	}

	public void setRoot(TNode<T> root) {
		this.root = root;
	}

	public BSTreeNew(T data) {
		root = new TNode<>(data);
	}

	public LinkedStack<T> getNextStack() {
		return nextStack;
	}

	public void setNextStack(LinkedStack<T> nextStack) {
		this.nextStack = nextStack;
	}

	public LinkedStack<T> getPrefStack() {
		return prefStack;
	}

	public void setPrefStack(LinkedStack<T> prefStack) {
		this.prefStack = prefStack;
	}

	public T getNext() {
		if (nextStack.isEmpty()) {
			return null;
		}
		Node<T> node = new Node<>(nextStack.pop().getData());
		prefStack.push(node.getData());
		return node.getData();
	}

	public T getPref() {
		if (prefStack.isEmpty()) {
			return null;
		}
		Node<T> node = new Node<>(prefStack.pop().getData());
		nextStack.push(node.getData());
		return node.getData();
	}

	public void insert(T data) {
		if (isEmpty())
			root = new TNode<T>(data);
		else
			insert(data, root);
	}

	private void insert(T data, TNode<T> node) {
		if (data.compareTo((T) node.getData()) >= 0) { // insert into right
														// subtree
			if (!node.hasRight())
				node.setRight(new TNode<>(data));
			else
				insert(data, node.getRight());
		} else { // insert into left subtree
			if (!node.hasLeft())
				node.setLeft(new TNode<>(data));
			else
				insert(data, node.getLeft());
		}
	}

	public TNode<T> delete(T data) {
		TNode<T> current = root;
		TNode<T> parent = root;
		boolean isLeftChild = false;

		if (isEmpty())
			return null; // tree is empty

		while (current != null && !current.getData().equals(data)) {
			parent = current;
			if (data.compareTo(current.getData()) < 0) {
				current = current.getLeft();
				isLeftChild = true;
			} else {
				current = current.getRight();
				isLeftChild = false;
			}
		}
		if (current == null)
			return null; // not found
		// case 1: node is a leaf
		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root)
				root = null;
			else {
				if (isLeftChild)
					parent.setLeft(null);
				else
					parent.setRight(null);
			}
		} // Case 2 broken down further into 2 separate cases
		else if (current.hasLeft()) { // current has left child only
			if (current == root) {
				root = current.getLeft();
			} else if (isLeftChild) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setRight(current.getLeft());
			}
		} else if (current.hasRight()) { // current has right child only
			if (current == root) {
				root = current.getRight();
			} else if (isLeftChild) {
				parent.setLeft(current.getRight());
			} else {
				parent.setRight(current.getRight());
			}
		} // case 3: node to be deleted has 2 children
		else {
			TNode<T> successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild) {
				parent.setLeft(successor);
			} else {
				parent.setRight(successor);
			}
			successor.setLeft(current.getLeft());
		}

		return current;
	}

	private TNode<T> getSuccessor(TNode<T> node) {
		TNode<T> parentOfSuccessor = node;
		TNode<T> successor = node;
		TNode<T> current = node.getRight();
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.getRight();
		}
		if (successor != node.getRight()) { // fix successor connections
			parentOfSuccessor.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}
		return successor;
	}

	public TNode<T> find(T data) {
		return find(data, root);
	}

	private TNode<T> find(T data, TNode<T> node) {
		if (node != null) {
			int comp = node.getData().compareTo(data);
			if (comp == 0)
				return node;
			else if (comp > 0 && node.hasLeft())
				return find(data, node.getLeft());
			else if (comp < 0 && node.hasRight())
				return find(data, node.getRight());
		}
		return null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public TNode<T> largest() {
		return largest(root);
	}

	private TNode<T> largest(TNode<T> node) {
		if (node != null) {
			if (!node.hasRight())
				return (node);
			return largest(node.getRight());
		}
		return null;
	}

	public TNode<T> smallest() {
		return smallest(root);
	}

	private TNode<T> smallest(TNode<T> node) {
		if (node != null) {
			if (!node.hasLeft())
				return (node);
			return smallest(node.getLeft());
		}
		return null;
	}

	public int height() {
		return height(root);
	}

	private int height(TNode<T> node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.getLeft());
		if (node.hasRight())
			right = height(node.getRight());
		return (left > right) ? (left + 1) : (right + 1);
	}

	public String traverseLevelByLevel() {
		nextStack.clear();
		prefStack.clear();
		traverseLevelByLevel(root);
		String result = "";
		T currData;
		while (!prefStack.isEmpty()) {
			currData = prefStack.pop().getData();
			result += currData + ",";
			nextStack.push(currData);
		}
		return result;
	}

	private void traverseLevelByLevel(TNode<T> node) {
		if (node == null)
			return;

		Squeue<TNode<T>> tempQueue = new Squeue<>();
		tempQueue.enqueue(node);

	        while (!tempQueue.isEmpty()) {
	            TNode<T> currentNode = tempQueue.dequeue();

	            if (currentNode.getLeft() != null) {
	                tempQueue.enqueue(currentNode.getLeft());
	            }
	            if (currentNode.getRight() != null) {
	                tempQueue.enqueue(currentNode.getRight());
	            }

	            prefStack.push(currentNode.getData());
	        }

	}


	public String traverseInOrder() {
		nextStack.clear();
		prefStack.clear();
		traverseInOrder(root);
		String result = "";
		T currData;
		while (!prefStack.isEmpty()) {
			currData = prefStack.pop().getData();
			result += currData + ",";
			nextStack.push(currData);
		}
		return result;
	}

	private void traverseInOrder(TNode<T> node) {
		if (node != null) {
			if (node.getLeft() != null)
				traverseInOrder(node.getLeft());
			prefStack.push(node.getData());

			if (node.getRight() != null)
				traverseInOrder(node.getRight());

		}

	}

	public void traversePreOrder() {
		traversePreOrder(root);
	}

	private void traversePreOrder(TNode<T> node) {
		System.out.print(node + " ");
		if (node != null) {
			if (node.getLeft() != null)
				traversePreOrder(node.getLeft());
			if (node.getRight() != null)
				traversePreOrder(node.getRight());
		}
	}

	public void traversePostOrder() {
		traversePostOrder(root);
	}

	private void traversePostOrder(TNode<T> node) {
		if (node != null) {
			if (node.getLeft() != null)
				traversePostOrder(node.getLeft());
			if (node.getRight() != null)
				traversePostOrder(node.getRight());
			System.out.print(node + " ");
		}
	}

	public int countLeavesR() {
		return countLeaves(root);
	}

	private int countLeaves(TNode<T> node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		return countLeaves(node.getLeft()) + countLeaves(node.getRight());
	}

	public int countParent() {
		return countParent(root);
	}

	private int countParent(TNode<T> node) {
		if (node == null)
			return 0;
		if (node.getLeft() == null && node.getRight() == null)
			return 0;
		int leftCurr = countParent(node.getLeft());
		int rightCurr = countLeaves(node.getRight());
		return leftCurr + rightCurr + 1;
	}

	public int Size() {
		return Size(root);
	}

	private int Size(TNode<T> curr) {
		if (curr == null) {
			return 0;
		}
		int leftCurr = Size(curr.getLeft());
		int rightCurr = Size(curr.getRight());
		return 1 + leftCurr + rightCurr;
	}

	

}
