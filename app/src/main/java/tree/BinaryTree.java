package tree;


import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
  Node root;

  private Node addRecursive(Node current, int value) {
    if (current == null) {
      return new Node(value);
    }

    if (value < current.value) {
      current.left = addRecursive(current.left, value);
    } else if (value > current.value) {
      current.right = addRecursive(current.right, value);
    } else {
      // value already exists
      return current;
    }

    return current;
  }

  public void add(int value) {
    root = addRecursive(root, value);
  }

  //===============================================================================

  private boolean containsNodeRecursive(Node current, int value) {
    if (current == null) {
      return false;
    }
    if (value == current.value) {
      return true;
    }
    if (value < current.value) {
      return containsNodeRecursive(current.left, value);
    } else {
      return containsNodeRecursive(current.right, value);
    }
  }

  public boolean containsNode(int value) {
    return containsNodeRecursive(root, value);
  }

  //===============================================================================

  private Node deleteRecursive(Node current, int value) {
    if (current == null) {
      return null;
    }

    if (value == current.value) {
      // Node to delete found
      if (current.left == null && current.right == null) {
        return null;
      }


      if (current.right == null) {
        return current.left;
      }

      if (current.left == null) {
        return current.right;
      }

      int smallestValue = findSmallestValue(current.left);
      current.value = smallestValue;
      current.left = deleteRecursive(current.left, smallestValue);
      return current;
    }
    if (value < current.value) {
      current.left = deleteRecursive(current.left, value);
      return current;
    }
    current.right = deleteRecursive(current.right, value);
    return current;
  }

  private int findSmallestValue(Node root) {
    if (root.left == null) {
      return root.value;
    } else {
      return findSmallestValue(root.left);
    }
  }

  public void delete(int value) {
    root = deleteRecursive(root, value);
  }

  //===============================================================================

  public void traverseInOrder(Node node) {
    if (node != null) {
      traverseInOrder(node.left);
      System.out.print(" " + node.value);
      traverseInOrder(node.right);
    }
  }

  public void traversePreOrder(Node node) {
    if (node != null) {
      System.out.print(" " + node.value);
      traversePreOrder(node.left);
      traversePreOrder(node.right);
    }
  }

  public void traversePostOrder(Node node) {
    if (node != null) {
      traversePostOrder(node.left);
      traversePostOrder(node.right);
      System.out.print(" " + node.value);
    }
  }


  //===============================================================================

  public void traverseLevelOrder() {
    if (root == null) {
      return;
    }

    Queue<Node> nodes = new LinkedList<>();
    nodes.add(root);

    while (!nodes.isEmpty()) {

      Node node = nodes.remove();

      System.out.print(" " + node.value);

      if (node.left != null) {
        nodes.add(node.left);
      }

      if (node.right != null) {
        nodes.add(node.right);
      }
    }
  }


}
