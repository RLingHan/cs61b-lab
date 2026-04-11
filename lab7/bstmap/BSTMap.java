package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,  V> implements Map61B<K, V>{

    private int size;
    private TreeNode root;
    private class TreeNode {
        public K key;
        public V value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(K key, V value, TreeNode left, TreeNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    public BSTMap() {
        size = 0;
        root = null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return getHelper(root, key) != null;
    }

    @Override
    public V get(K key) {
        if (getHelper(root, key) == null) return null;
        return getHelper(root,key).value;
    }

    private TreeNode getHelper(TreeNode root,K key) {
        if (root == null) return null;
        if (root.key.compareTo(key) <0) return getHelper(root.right, key);
        if (root.key.compareTo(key) > 0) return getHelper(root.left, key);
        return root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) return;
        root = putHelper(root,key,value);
    }
    private TreeNode putHelper(TreeNode root, K key, V value) {
        if(root == null) {
            size++;
            return new TreeNode(key, value, null, null);
        }
        if (root.key.compareTo(key) <0) root.right = putHelper(root.right, key, value);
        if (root.key.compareTo(key) > 0) root.left = putHelper(root.left, key, value);
        return root;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        inOrderHelper(root, keySet);
        return keySet;
    }
    private void inOrderHelper(TreeNode root,Set<K> keySet) {
        if (root == null) return;
        inOrderHelper(root.left, keySet);
        keySet.add(root.key);
        inOrderHelper(root.right, keySet);
    }

    @Override
    public V remove(K key) {
        TreeNode target = getHelper(root, key);
        if (target == null) return null;
        V value = target.value;
        root = removeHelper(root, key);
        size--;
        return value;
    }

    private TreeNode removeHelper(TreeNode node, K key) {
        if (node == null) return null;

        int cmp = node.key.compareTo(key);
        if (cmp < 0) {
            // key 在右子树
            node.right = removeHelper(node.right, key);
        } else if (cmp > 0) {
            // key 在左子树
            node.left = removeHelper(node.left, key);
        } else {
            // 找到目标节点
            if (node.left == null)  return node.right; // 情况1/2：无左子，接右子
            if (node.right == null) return node.left;  // 情况2：无右子，接左子

            // 情况3：两个子节点 用右子树最小节点（后继）替换
            TreeNode successor = findMin(node.right);
            node.key   = successor.key;
            node.value = successor.value;
            // 删掉右子树中那个最小节点
            node.right = removeHelper(node.right, successor.key);
        }
        return node;
    }
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public V remove(K key, V value) {
        TreeNode target = getHelper(root, key);
        if (target == null || !target.value.equals(value)) return null;
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private final java.util.Deque<TreeNode> stack = new java.util.ArrayDeque<>();

        BSTIterator() {
            pushLeft(root); // 把最左侧路径全部压栈
        }

        /** 把 node 及其所有左子节点依次压栈 */
        private void pushLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            TreeNode node = stack.pop();   // 弹出当前最小节点
            pushLeft(node.right);          // 处理其右子树
            return node.key;
        }
    }
}
