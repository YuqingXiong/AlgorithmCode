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

# 94.二叉树的中序遍历

## 递归
```java
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return ans;
        inorderTraversal(root.left);
        ans.add(root.val);
        inorderTraversal(root.right);
        return ans;
    }
}
```

## 迭代
```java
class Solution {
   
    public List<Integer> inorderTraversal(TreeNode root) {
         List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            ans.add(node.val);
            node = node.right;
        }
        return ans;
    }
}
```

# 145.二叉树的后序遍历

## 递归

```java
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return ans;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        ans.add(root.val);
        return ans;
    }
}
```

## 迭代
后序遍历：左-右-中

- 遍历完左子树后，需要从栈中获得当前节点，来获得右子树，但是后续还需要遍历中间节点，所以需要加回去栈中
- 但是当左右子树遍历完，就不需要再加回栈中了，只需要记录中间节点的值了
- 通过prev记录上一个遍历的右节点，然后判断当前节点的右节点和prev是否指向同一个，就可以判断当前节点是否还需要继续遍历右节点了

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
         List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root, prev = null;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(node.right == null || prev == node.right){
                ans.add(node.val);
                prev = node;
                node = null;
            }else{
                stack.push(node);
                node = node.right;
            }
        }
        return ans;
    }
}
```

# 102.二叉树的层序遍历

## BFS
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int curLevel = queue.size();
            List<Integer> ansLevel = new ArrayList<>();
            for(int i = 0; i < curLevel; ++ i){
                TreeNode node = queue.poll();
                ansLevel.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            ans.add(ansLevel);
        }
        return ans;
    }
}
```


## 我的代码
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        List<TreeNode> levelNode = new ArrayList<>();
        levelNode.add(root);
        while(levelNode.size() != 0){
            List<Integer> levelAns = new ArrayList<>();
            List<TreeNode> nextLevelNode = new ArrayList<>();
            for(TreeNode node : levelNode){
                levelAns.add(node.val);
                System.out.println(node.val);
                if(node.left != null)
                    nextLevelNode.add(node.left);
                if(node.right != null)
                    nextLevelNode.add(node.right);
            }
            levelNode = nextLevelNode;
            ans.add(levelAns);
        }
        return ans;
    }
}
```
# 226.翻转二叉树

递归
```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        TreeNode right = invertTree(root.left);
        TreeNode left = invertTree(root.right);
        root.right = right;
        root.left = left;
        return root;
    }
}
```

# 101.对称二叉树

根节点的左右子树是对称的，所以让两个子树同时往下遍历

左子树的左节点等于右子树的右节点，反之亦然

并且如果是迭代则让应该相等的节点放在临近的一起

## 递归
```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(TreeNode l, TreeNode r){
        if(l == null && r == null) return true;
        if(l == null || r == null) return false;
        boolean check1 = check(l.left, r.right);
        boolean check2 = check(l.right, r.left);
        return l.val == r.val && check1 && check2;
    }
}
```

## 迭代

注意遍历到叶子节点的时候要 continue

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(!queue.isEmpty()){
            TreeNode l = queue.poll();
            TreeNode r = queue.poll();
            if(l == null && r == null) continue;
            if(l == null || r == null || l.val != r.val) return false;
            
            queue.offer(l.left);
            queue.offer(r.right);

            queue.offer(l.right);
            queue.offer(r.left);
        }
        return true;
    }
}
```

# 104.二叉树的最大深度

递归写法：

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int lnum = maxDepth(root.left);
        int rnum = maxDepth(root.right);
        return Math.max(lnum, rnum) + 1;
    }
}
```

# 111.二叉树的最小深度

```java
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int lmin = minDepth(root.left);
        int rmin = minDepth(root.right);
        if(lmin == 0 && rmin == 0) return 1;
        else if(lmin == 0 || rmin == 0) return Math.max(lmin, rmin)+1;
        return Math.min(lmin, rmin)+1;
    }
}
```

# 222.完全二叉树的节点个数

## 完全二叉树的性质

分左右子树遍历：
- 如果左子树高度和右子树高度相等，则说明左子树已满，所以左子树的节点个数可以计算为 $2^{lLevel}$，只需要计算右子树节点个数了
- 如果左子树高度和右子树高度不相等，则右子树已满，所以右子树的节点个数可以计算为 $2^{rLevel}$，只需要计算左子树节点个数了

![img.png](https://xiongyuqing-img.oss-cn-qingdao.aliyuncs.com/img/202401101035909.png)

```java
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int lLevel = countLevel(root.left);
        int rLevel = countLevel(root.right);
        if(lLevel == rLevel){
            return countNodes(root.right) + (1<<lLevel);
        }else{
            return countNodes(root.left) + (1<<rLevel);
        }
    }

    public int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            ++level;
            root = root.left;
        }
        return level;
    }
}
```

## 常规递归遍历
```java
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
```
# 110.平衡二叉树

## 自顶向下递归
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int lLevel = countLevel(root.left);
        int rLevel = countLevel(root.right);
        if(Math.abs(lLevel-rLevel) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int countLevel(TreeNode root){
        if(root == null) return 0;
        return Math.max(countLevel(root.left), countLevel(root.right)) + 1;
    }
}
```
countLevel被重复调用了

## 自底向上递归

- 如果平衡就返回高度
- 如果不平衡就返回一个负数
- 后面判断是否子树已经不平衡，通过高度是否是负数检测

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return countLevel(root) >= 0;
    }

    public int countLevel(TreeNode root){
        if(root == null) return 0;
        int lLevel = countLevel(root.left);
        int rLevel = countLevel(root.right);
        if(lLevel == -1 || rLevel == -1 || Math.abs(lLevel-rLevel) > 1){
            return -1;
        }
        return Math.max(lLevel, rLevel) + 1;
    }
}
```

左子树已经不平衡，右子树就没必要递归下去了，直接 return
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return countLevel(root) >= 0;
    }

    public int countLevel(TreeNode root){
        if(root == null) return 0;
        int lLevel = countLevel(root.left);
        if(lLevel == -1) return -1;
        int rLevel = countLevel(root.right);
        if(rLevel == -1) return -1;
        if(Math.abs(lLevel-rLevel) > 1){
            return -1;
        }
        return Math.max(lLevel, rLevel) + 1;
    }
}
```

# 257.二叉树的所有路径

遇到叶子节点就把路径计入答案，否则把当前节点计入路径中

```java
class Solution {
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
```

String 的加法会不断的创建拷贝生成新的字符串，耗费时间和空间。可以用 StringBuilder 或者 StringBuffer 优化

但是它们属于引用类型，后面对其字符串的更改，回溯后更改依然存在，所以在回溯后需要delete掉多余的内容

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        TreePath(root, new StringBuilder(), paths);
        return paths;
    }

    public void TreePath(TreeNode root, StringBuilder path, List<String> paths){
        if(root == null) return;
        if(root.left == null && root.right == null){
            paths.add(path.toString() + root.val); // toString 一定在前，+val在后
            return;
        }
        int prevLen = path.length();
        path.append(root.val).append("->");
        
        TreePath(root.left, path, paths);
        TreePath(root.right, path, paths);

        path.setLength(prevLen);
    }
}
```