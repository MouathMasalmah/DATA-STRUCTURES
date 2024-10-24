package application;

public class AVLTree<T extends Comparable<T>> extends BSTree<T> {

	private QStack<TNode<T>> nextStack = new QStack<>();
	private QStack<TNode<T>> prefStack = new QStack<>();

	public QStack<TNode<T>> getNextStack() {
		return nextStack;
	}

	public void setNextStack(QStack<TNode<T>> nextStack) {
		this.nextStack = nextStack;
	}

	public QStack<TNode<T>> getPrefStack() {
		return prefStack;
	}

	public void setPrefStack(QStack<TNode<T>> prefStack) {
		this.prefStack = prefStack;
	}

	@Override
	public void insert(T value) {
		if (isEmpty()) {

			root = new TNode<>(value);
			return;
		}
		TNode<T> rootNode = root;
		addEntry(value, rootNode);
		root = rebalance(rootNode);
	}

	@Override
	public TNode<T> delete(T data) {
		TNode<T> temp = super.delete(data);
		if (temp != null && root != null) {
			TNode<T> rootNode = root;
			root = rebalance(rootNode);
		}
		return temp;
	}

	protected void addEntry(T value, TNode<T> targetNode) {
		assert targetNode != null;
		if (value.compareTo(targetNode.getData()) < 0) {
			if (targetNode.hasLeft()) {
				TNode<T> leftChild = targetNode.getLeft();
				addEntry(value, leftChild);
				targetNode.setLeft(rebalance(leftChild));
			} else {
				targetNode.setLeft(new TNode<>(value));
			}
		} else {
			if (targetNode.hasRight()) {
				TNode<T> rightChild = targetNode.getRight();
				addEntry(value, rightChild);
				targetNode.setRight(rebalance(rightChild));
			} else {
				targetNode.setRight(new TNode<>(value));
			}
		}
	}

	public void traverseInOrder() {
		nextStack.clear();
		prefStack.clear();
		traverseInOrder(root);
		TNode<T> currData = null;
		while (!prefStack.isEmpty()) {
			currData = prefStack.pop();
			nextStack.push(currData);
		}
	}

	private void traverseInOrder(TNode<T> node) {
		if (node != null) {
			if (node.getLeft() != null)
				traverseInOrder(node.getLeft());
			prefStack.push(node);

			if (node.getRight() != null)
				traverseInOrder(node.getRight());

		}

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

	public String traverseLevelByLevel() {
		nextStack.clear();
		prefStack.clear();
		traverseLevelByLevel(root);
		String result = "";
		TNode<T> currData;
		while (!prefStack.isEmpty()) {
			currData = prefStack.pop();
			result += currData + ",";
			nextStack.push(currData);
		}
		return result;
	}

	private void traverseLevelByLevel(TNode<T> node) {
		if (node == null)
			return;

		LinkedListQueue<TNode<T>> tempQueue = new LinkedListQueue<>();
		tempQueue.enqueue(node);

		while (!tempQueue.isEmpty()) {
			TNode<T> currentNode = tempQueue.dequeue();

			if (currentNode.getRight() != null) {
				tempQueue.enqueue(currentNode.getRight());
			}
			if (currentNode.getLeft() != null) {
				tempQueue.enqueue(currentNode.getLeft());
			}
			prefStack.push(currentNode);
		}

	}

	public boolean isEmpty() {
		return root == null;
	}

	protected TNode<T> rebalance(TNode<T> node) {
		int heightDiff = getHeightDifference(node);
		if (heightDiff == 0)
			return node;
		if (heightDiff > 1) {
			if (getHeightDifference(node.getLeft()) > 0)
				node = rotateRight(node);
			else
				node = rotateLeftRight(node);
		} else if (heightDiff < -1) {
			if (getHeightDifference(node.getRight()) < 0)
				node = rotateLeft(node);
			else
				node = rotateRightLeft(node);
		}
		return node;
	}

	protected int getHeightDifference(TNode<T> node) {
		return super.height(node.getLeft()) - super.height(node.getRight());
	}

	protected TNode<T> rotateRight(TNode<T> node) {
		TNode<T> temp = node.getLeft();
		node.setLeft(temp.getRight());
		temp.setRight(node);

		return temp;
	}

	protected TNode<T> rotateLeft(TNode<T> node) {
		TNode<T> temp = node.getRight();
		node.setRight(temp.getLeft());
		temp.setLeft(node);

		return temp;
	}

	protected TNode<T> rotateRightLeft(TNode<T> node) {
		TNode<T> temp = node.getRight();
		node.setRight(rotateRight(temp));

		return rotateLeft(node);
	}

	protected TNode<T> rotateLeftRight(TNode<T> node) {
		TNode<T> temp = node.getLeft();
		node.setLeft(rotateLeft(temp));

		return rotateRight(node);
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

	public int height() {
		return height(root);
	}

	public int height(TNode<T> node) {
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

	@Override
	public String toString() {
		return "AVLTree [root=" + root + ", nextStack=" + nextStack + ", prefStack=" + prefStack + "]";
	}

}