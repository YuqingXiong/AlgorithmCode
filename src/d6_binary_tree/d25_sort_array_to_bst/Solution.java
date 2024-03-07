package d6_binary_tree.d25_sort_array_to_bst;

import d6_binary_tree.TreeNode;

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int l, int r){
        if(l == r){
            return new TreeNode(nums[l]);
        }else if(l > r){
            return null;
        }
        int mid = l + (r - l)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, l, mid - 1);
        root.right = build(nums, mid + 1, r);
        return root;
    }
}
