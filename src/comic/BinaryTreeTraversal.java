package comic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description: 二叉树的遍历
 * @author: zhukai
 * @date: 2019/7/21 10:57
 */
public class BinaryTreeTraversal {

    /**
     * 利用链表创建一个二叉树，链表中的null值表示二叉树的左/右孩子为空
     *
     * @param list 输入序列
     * @return
     */
    public TreeNode createBinaryTree(LinkedList<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        TreeNode node = null;
        Integer data = list.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(list);
            node.rightChild = createBinaryTree(list);
        }
        return node;
    }

    /**
     * 二叉树的前序遍历
     *
     * @param node
     */
    public void preOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrderTraversal(node.leftChild);
        preOrderTraversal(node.rightChild);
    }

    /**
     * 二叉树的非递归前序遍历
     *
     * @param node
     */
    public void preOrderTraversalWithStack(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = node;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 二叉树的中序遍历
     *
     * @param node
     */
    public void inOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.leftChild);
        System.out.println(node.data);
        inOrderTraversal(node.rightChild);
    }

    /**
     * 二叉树的非递归中序遍历
     *
     * @param node
     */
    public void inOrderTraversalWithStack(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = node;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 二叉树的后序遍历
     *
     * @param node
     */
    public void postOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.leftChild);
        postOrderTraversal(node.rightChild);
        System.out.println(node.data);
    }

    /**
     * 二叉树的非递归后序遍历
     *
     * @param node
     */
    public void postOrderTraversalWithStack(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = node;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 二叉树的层序遍历
     *
     * @param node
     */
    public void levelOrderTraversal(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.println(treeNode.data);
            if (treeNode.leftChild != null) {
                queue.offer(treeNode.leftChild);
            }
            if (treeNode.rightChild != null) {
                queue.offer(treeNode.rightChild);
            }
        }
    }

    class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        BinaryTreeTraversal binaryTreeTraversal = new BinaryTreeTraversal();
        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(new Integer[]{
                3, 2, 9, null, null, 10, null, null, 8, null, 4
        }));
        // 构建二叉树
        TreeNode node = binaryTreeTraversal.createBinaryTree(list);
        // 前序遍历
//        binaryTreeTraversal.preOrderTraversal(node);
//        binaryTreeTraversal.preOrderTraversalWithStack(node);
        // 中序遍历
//        binaryTreeTraversal.inOrderTraversal(node);
//        binaryTreeTraversal.inOrderTraversalWithStack(node);
        // 后序遍历
//        binaryTreeTraversal.postOrderTraversal(node);
        binaryTreeTraversal.postOrderTraversalWithStack(node);
        // 层序遍历
//        binaryTreeTraversal.levelOrderTraversal(node);
    }
}
