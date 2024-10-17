// Binary Search Tree implementation in Java
class BinarySearchTree {
    // Node class for the tree
    class Node {
        int data;
        Node left, right;

        // Constructor to create a new node
        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // Root of the BST
    Node root;

    // Constructor for an empty BST
    public BinarySearchTree() {
        root = null;
    }

    // Method to insert a new node
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // Recursive function to insert a new key in the BST
    private Node insertRec(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }
        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        // Return the unchanged root
        return root;
    }

    // Method to delete a node
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    // Recursive function to delete a node
    private Node deleteRec(Node root, int data) {
        // Base case: if the tree is empty
        if (root == null) {
            return root;
        }

        // Recur down the tree
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node with one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    // Helper method to find the minimum value in a subtree
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // Method to search for a node
    public boolean search(int data) {
        return searchRec(root, data);
    }

    // Recursive function to search for a node
    private boolean searchRec(Node root, int data) {
        // Base case: root is null or data is present at root
        if (root == null || root.data == data) {
            return root != null;
        }
        // Data is greater than root's data
        if (data < root.data) {
            return searchRec(root.left, data);
        }
        // Data is less than root's data
        return searchRec(root.right, data);
    }

    // In-order traversal
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.data + " ");
            inOrderRec(root.right);
        }
    }

    // Pre-order traversal
    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Post-order traversal
    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Main method to test the BST implementation
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert nodes
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        // Print in-order traversal
        System.out.print("In-order traversal: ");
        bst.inOrder(); // Output: 20 30 40 50 60 70 80

        // Print pre-order traversal
        System.out.print("Pre-order traversal: ");
        bst.preOrder(); // Output: 50 30 20 40 70 60 80

        // Print post-order traversal
        System.out.print("Post-order traversal: ");
        bst.postOrder(); // Output: 20 40 30 60 80 70 50

        // Search for a node
        int key = 40;
        System.out.println("Search for " + key + ": " + bst.search(key)); // Output: true

        // Delete a node
        bst.delete(40);
        System.out.print("In-order traversal after deleting 40: ");
        bst.inOrder(); // Output: 20 30 50 60 70 80

        // Search for the deleted node
        System.out.println("Search for " + key + " after deletion: " + bst.search(key)); // Output: false
    }
}
