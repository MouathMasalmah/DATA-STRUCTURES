package Lab8;

public class AVLTree<T extends Comparable<T>> extends BSTree<T> {
	 private TNode<T> root;
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
    public TNode<T> delete(T value) {
        if (isEmpty()) return null;
        TNode<T> grand = root;
        TNode<T> parent = root;
        TNode<T> current = root;
        boolean isLeftParent = false;
        boolean isLeftChild = false;

        while (current != null && current.getData().compareTo(value) != 0) {
            grand = parent;
            parent = current;
            if (value.compareTo(current.getData()) < 0) {
                current = current.getLeft();
                isLeftParent = isLeftChild;
                isLeftChild = true;
            } else {
                current = current.getRight();
                isLeftParent = isLeftChild;
                isLeftChild = false;
            }
        }

        if (current == null) return null;

        if (current.isLeaf()) { // is a leaf
            if (current == root) {
                root = null;
            } else {
                if (isLeftChild) parent.setLeft(null);
                else parent.setRight(null);
            }
        } else if (current.hasLeft() && !current.hasRight()) { // is a node with one child
            if (current == root) {
                root = current.getLeft();
            } else if (isLeftChild) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else if (current.hasRight() && !current.hasLeft()) { // is a node with one child
            if (current == root) {
                root = current.getRight();
            } else if (isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        } else { // is a node with two children
            TNode<T> successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }

            successor.setLeft(current.getLeft());
        }

        if (isLeftParent) {
            grand.setLeft(rebalance(grand.getLeft()));
        } else {
            grand.setRight(rebalance(grand.getRight()));
        }

        return current;
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

    protected TNode<T> rebalance(TNode<T> node) {
        int heightDiff = getHeightDifference(node);
        if (heightDiff == 0) return node;
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
}