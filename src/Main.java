import d1_array.d5_59_spiral_matrix_ii.Solution2;
import d2_link.d2_design_linked_list.MyLinkedList;
import d2_link.d2_design_linked_list.MyLinkedList2;
import d6_binary_tree.TreeNode;
import d6_binary_tree.d9_count_complete_tree_nodes.Solution;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(solution.countNodes(root));

    }
}
