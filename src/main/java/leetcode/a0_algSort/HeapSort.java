package leetcode.a0_algSort;

import java.util.Arrays;

/**
 * 树-二叉树-完全二叉树-堆-堆排序
 * heap是堆，不是sorted array。heap只要符合"父节点大于子节点"就可以了。
 * @author neptune
 * @create 2020 04 19 12:15 下午
 */
public class HeapSort {

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void heapify(int[] tree, int n, int i) {
        // 递归返回条件
        if(i > n) {
            return;
        }
        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        int max = i;
        if(c1 < n && tree[c1] > tree[max]) {
            max = c1;
        }
        if(c2 < n && tree[c2] > tree[max]) {
            max = c2;
        }
        if (max != i) {
            swap(tree, max, i);
            heapify(tree, n, max);
        }
    }

    void buildHeap(int[] tree, int n) {
        int last_node_idx = n - 1;
        int parent_last_node_idx = (last_node_idx - 1) / 2;
        for (int i = parent_last_node_idx; i >= 0 ; i--) {
            heapify(tree, n, i);
        }
    }

    // 堆排序
    void sort(int[] tree, int n) {
        buildHeap(tree, n);
        for (int i = n - 1; i >= 0 ; i--) {
            swap(tree, i, 0);
            // i 是递减，每次交换出最大的值就不参与下一次的 heapify 了
            heapify(tree, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] tree = new int[]{4,10,3,5,1,2};
        HeapSort heapSort = new HeapSort();
        // 1. 从顶向下完成一个堆的构建
        heapSort.heapify(tree, tree.length, 0);
        Arrays.stream(tree).forEach(n -> System.out.println(n));
        System.out.println("============================");
        // 2. 从底部节点父节点开始向上完成堆的构建
        tree = new int[]{2,5,3,1,10,4};
        heapSort.buildHeap(tree, tree.length);
        Arrays.stream(tree).forEach(n -> System.out.println(n));
        System.out.println("============================");
        // 3. 堆排序，不断交换顶部和子节点的位置
        heapSort.sort(tree, tree.length);
        Arrays.stream(tree).forEach(n -> System.out.println(n));
    }
}
