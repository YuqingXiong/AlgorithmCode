package d6_binary_tree.d16_max_binary_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length-1);
    }

    public TreeNode build(int[] nums, int l, int r){
        if(l > r){
            return null;
        }
        int mid = l;
        for(int i = l + 1; i <= r; ++ i){
            if(nums[i] > nums[mid]){
                mid = i;
            }
        }

        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, l, mid - 1);
        root.right = build(nums, mid + 1, r);
        return root;
    }
}
