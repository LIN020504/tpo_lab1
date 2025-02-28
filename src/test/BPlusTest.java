package test;

import Main.BPlus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BPlusTest {

    @Test
    @DisplayName("Test for BPlusTree")
    void testInsertionAndSearch() {
        BPlus.BPlusTree<Integer, String> tree = new BPlus.BPlusTree<>(7);

        // 插入 10 个键值对
        tree.insert(10, "Ten");
        tree.insert(20, "Twenty");
        tree.insert(5, "Five");
        tree.insert(6, "Six");
        tree.insert(12, "Twelve");
        tree.insert(30, "Thirty");
        tree.insert(7, "Seven");
        tree.insert(17, "Seventeen");
        tree.insert(3, "Three");
        tree.insert(25, "Twenty-Five");

        // 测试查找是否正确
        assertEquals("Ten", tree.search(10));
        assertEquals("Twenty", tree.search(20));
        assertEquals("Five", tree.search(5));
        assertEquals("Seventeen", tree.search(17));
        assertEquals(null, tree.search(100));// 不存在的键
    }
}
