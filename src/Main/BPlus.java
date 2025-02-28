package Main;

import java.util.ArrayList;
import java.util.List;

public class BPlus {
    static class BPlusTreeNode<K extends Comparable<K>, V> {
        boolean isLeaf;
        List<K> keys;
        List<V> values;
        List<BPlusTreeNode<K, V>> children;
        BPlusTreeNode<K, V> next;  // 叶子节点的链表指针

        BPlusTreeNode(boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.keys = new ArrayList<>();
            this.values = isLeaf ? new ArrayList<>() : null;  // 只有叶子节点存储 values
            this.children = isLeaf ? null : new ArrayList<>();
            this.next = null;
        }
    }

    public static class BPlusTree<K extends Comparable<K>, V> {
        private final int maxKeys;  // B+ 树阶数 - 1
        private BPlusTreeNode<K, V> root;

        public BPlusTree(int order) {
            this.maxKeys = order - 1; // 阶数决定最大键数
            this.root = new BPlusTreeNode<>(true); // 初始根是叶子节点
        }

        // 插入
        public void insert(K key, V value) {
            BPlusTreeNode<K, V> leaf = findLeafNode(key);
            insertIntoLeaf(leaf, key, value);
            if (leaf.keys.size() > maxKeys) {
                splitLeafNode(leaf);
            }
        }

        // 查找
        public V search(K key) {
            BPlusTreeNode<K, V> leaf = findLeafNode(key);
            for (int i = 0; i < leaf.keys.size(); i++) {
                if (leaf.keys.get(i).equals(key)) {
                    return leaf.values.get(i);
                }
            }
            return null;
        }

        // 找到合适的叶子节点
        private BPlusTreeNode<K, V> findLeafNode(K key) {
            BPlusTreeNode<K, V> current = root;
            while (!current.isLeaf) {
                int i = 0;
                while (i < current.keys.size() && key.compareTo(current.keys.get(i)) >= 0) {
                    i++;
                }
                current = current.children.get(i);
            }
            return current;
        }

        // 在叶子节点插入键值对
        private void insertIntoLeaf(BPlusTreeNode<K, V> leaf, K key, V value) {
            int i = 0;
            while (i < leaf.keys.size() && key.compareTo(leaf.keys.get(i)) > 0) {
                i++;
            }
            leaf.keys.add(i, key);
            leaf.values.add(i, value);
        }

        // 叶子节点分裂
        private void splitLeafNode(BPlusTreeNode<K, V> leaf) {
            int mid = (maxKeys + 1) / 2;
            BPlusTreeNode<K, V> newLeaf = new BPlusTreeNode<>(true);
            newLeaf.keys.addAll(leaf.keys.subList(mid, leaf.keys.size()));
            newLeaf.values.addAll(leaf.values.subList(mid, leaf.values.size()));
            leaf.keys.subList(mid, leaf.keys.size()).clear();
            leaf.values.subList(mid, leaf.values.size()).clear();

            newLeaf.next = leaf.next;
            leaf.next = newLeaf;

            if (leaf == root) {
                BPlusTreeNode<K, V> newRoot = new BPlusTreeNode<>(false);
                newRoot.keys.add(newLeaf.keys.get(0));
                newRoot.children.add(leaf);
                newRoot.children.add(newLeaf);
                root = newRoot;
            } else {
                insertIntoParent(leaf, newLeaf.keys.get(0), newLeaf);
            }
        }

        // 父节点插入新键值
        private void insertIntoParent(BPlusTreeNode<K, V> left, K key, BPlusTreeNode<K, V> right) {
            BPlusTreeNode<K, V> parent = findParent(root, left);
            int index = parent.children.indexOf(left) + 1;
            parent.keys.add(index - 1, key);
            parent.children.add(index, right);

            if (parent.keys.size() > maxKeys) {
                splitInternalNode(parent);
            }
        }

        // 找到父节点（递归）
        private BPlusTreeNode<K, V> findParent(BPlusTreeNode<K, V> current, BPlusTreeNode<K, V> child) {
            if (current.isLeaf) return null;
            if (current.children.contains(child)) return current;
            for (BPlusTreeNode<K, V> node : current.children) {
                BPlusTreeNode<K, V> parent = findParent(node, child);
                if (parent != null) return parent;
            }
            return null;
        }

        // 内部节点分裂
        private void splitInternalNode(BPlusTreeNode<K, V> node) {
            int mid = node.keys.size() / 2;
            BPlusTreeNode<K, V> newNode = new BPlusTreeNode<>(false);
            newNode.keys.addAll(node.keys.subList(mid + 1, node.keys.size()));
            newNode.children.addAll(node.children.subList(mid + 1, node.children.size()));

            node.keys.subList(mid, node.keys.size()).clear();
            node.children.subList(mid + 1, node.children.size()).clear();

            if (node == root) {
                BPlusTreeNode<K, V> newRoot = new BPlusTreeNode<>(false);
                newRoot.keys.add(node.keys.remove(mid));
                newRoot.children.add(node);
                newRoot.children.add(newNode);
                root = newRoot;
            } else {
                insertIntoParent(node, node.keys.remove(mid), newNode);
            }
        }
    }

}