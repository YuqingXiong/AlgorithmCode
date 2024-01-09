# 144.二叉树的前序遍历

前序遍历是：根-左-右

所以记录序列的的时候放在最前面

## 递归

```java
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return ans;
        ans.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return ans;
    }
}
```
## 迭代
用栈模拟递归

- 首先访问根节点，存储根节点的值
- 随后访问左节点，每个左节点又是当前根节点，所以存储当前根节点的值，直到没有左节点就跳出循环
- 跳出循环后，访问当前节点的右节点，从栈顶取出当前节点，同时由于当前节点的左右节点都被要访问了，所以直接弹出当前节点，然后访问当前节点的右节点
- 右节点变成了当前根节点，记录值。回到步骤2判断是否有左节点

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                ans.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return ans;
    }
}
```