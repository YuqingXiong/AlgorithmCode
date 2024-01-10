package d6_binary_tree.d11_binary_tree_paths;

import d6_binary_tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        TreePath(root, "", paths);
        return paths;
    }

    public void TreePath(TreeNode root, String path, List<String> paths){
        if(root == null) return;
        if(root.left == null && root.right == null){
            paths.add(path + root.val);
            return;
        }
        path += root.val + "->";
        TreePath(root.left, path, paths);
        TreePath(root.right, path, paths);
    }
}
