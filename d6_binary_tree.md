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

# 404.左叶子之和

https://leetcode.cn/problems/sum-of-left-leaves/

1. 首先必须有左节点
2. 然后必须是叶子节点
3. 每次的递归累计值不能丢失

```java
class Solution {
    public int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return sum;
        if(root.left != null && root.left.left == null && root.left.right == null)
            sum = sum + root.left.val;
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return sum;
    }
}
```

# 513.找树左下角的值
左下角的值，也就是从左往右dfs最深的一层的第一个节点

或者，bfs从左往右的第一个节点（从右往左的最后一个节点）
## dfs
记录选择节点的深度
```java
public class Solution {
    int curDepth = 1;
    int maxDepth = 0;
    int curVal = 0;
    public int findBottomLeftValue(TreeNode root) {
        if(root == null) return curVal;
        if(root.left == null && root.right == null){
            if(maxDepth < curDepth){
                maxDepth = curDepth;
                curVal = root.val;
                return curVal;
            }
        }
        curDepth += 1;
        findBottomLeftValue(root.left);
        findBottomLeftValue(root.right);
        curDepth -= 1;
        return curVal;
    }
}
```

# 112.路径总和
easy

求二叉树路径上所有节点的和是否等于某个数

每次向下递归的时候减去当前节点的值，直到叶子节点看是否减到0了

左右子树只要有一个返回 true，就存在

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null){
            if(targetSum - root.val == 0) return true;
            return false;
        }
        return hasPathSum(root.left, targetSum - root.val) 
                || hasPathSum(root.right, targetSum - root.val);
    }
}
```

# 106.从中序与后序遍历序列构造二叉树

中序遍历：左 [中] 右 ：获取左右子树的范围

后序遍历：左 右 [中] ：获取根节点

所以后序遍历的最后一个节点一定是根节点
1. 首先根据后序遍历，其最后一个位置就是根节点idx
2. 找到根节点idx对应中中序遍历的位置 mid ，获取左右子树的范围 [l, mid-1], [mid+1, r]
3. 递归的在 [l, mid-1], [mid+1, r] 区间重复上述两个过程；
4. 由于后序遍历是先左，再右，所以下一个根节点也就是--idx是右子树的根节点。所以必须先递归右区间，再递归左区间

```java
class Solution {
    Map<Integer, Integer> hash;
    int idx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        hash = new HashMap<>();
        for(int i = 0; i < inorder.length; ++ i){
            hash.put(inorder[i], i);
        }
        idx = inorder.length-1;
        return build(inorder, postorder, 0, inorder.length-1);
    }

    public TreeNode build(int[] inorder, int[] postorder, int l, int r){
        if(l > r){
            return null;
        }
        // if(l == r){
        //     idx--;
        //     return new TreeNode(inorder[l]);
        // }
        TreeNode root = new TreeNode(postorder[idx]);
        int mid = hash.get(postorder[idx--]);

        root.right = build(inorder, postorder, mid + 1, r);
        root.left = build(inorder, postorder, l, mid-1);
        return root;
    }
}
```

# 654.最大二叉树
给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:

* 创建一个根节点，其值为 nums 中的最大值。
* 递归地在最大值 左边 的 子数组前缀上 构建左子树。
* 递归地在最大值 右边 的 子数组后缀上 构建右子树。


## 递归

每次在指定区间内找到最大值作为根节点

```java
class Solution {
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
```

## 单调栈

找到每个节点，左边第一个和右边第一个比它大的元素，其中较小的元素为当前节点的父节点

找到每个元素第一个比它大的元素可以用单调栈：

构造一个单调递减的栈，每当加入当前元素：
- 如果栈顶元素比当前元素小，那么当前元素就是栈顶元素右边第一个比它大的
- 如果栈顶元素比当前元素大，那么栈顶元素就是当前元素左边第一个比它大的

```java
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        TreeNode[] nodes = new TreeNode[n];
        for(int i = 0; i < n; ++ i){
            nodes[i] = new TreeNode(nums[i]);
            while(!stack.isEmpty() && nums[i] > nums[stack.peek()]){
                right[stack.pop()] = i;
            }
            if(!stack.isEmpty()){
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        TreeNode root = null;
        for(int i = 0; i < n; ++ i){
            if(left[i] == -1 && right[i] == -1){
                root = nodes[i];
            }else if(left[i] == -1 || (right[i] != -1 && nums[left[i]] > nums[right[i]])){
                nodes[right[i]].left = nodes[i];
            }else{
                nodes[left[i]].right = nodes[i];
            }
        }
        return root;
    }
}
```

# 617.合并二叉树

```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){
            return null;
        }
        int val1 = root1 == null ? 0 : root1.val;
        int val2 = root2 == null ? 0 : root2.val;
        TreeNode root = new TreeNode(val1 + val2);
        root.left = mergeTrees(root1 == null ? null : root1.left, root2 == null ? null : root2.left);
        root.right = mergeTrees(root1 == null ? null : root1.right, root2 == null ? null : root2.right);
        return root;
    }
}
```

更简介的代码：

不需要判断两个通知为 null 才返回 null

只要有一方为 null，就返回另一方

```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }
}
```

# 700.二叉搜索树中的搜索

```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null){
            return null;
        }
        if(root.val == val){
            return root;
        }
        if(root.val > val){
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }
}
```

# 98.验证二叉搜索树

## 递归判断区间范围

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean check(TreeNode root, long left, long right){
        if(root == null) return true;
        if(root.val <= left || root.val >= right) return false;

        return check(root.left, left, root.val) && check(root.right, root.val, right);
    }
}
```

## 中序遍历

迭代版本的，便于拿到前一个节点的值

# 530.二叉搜索树的最小绝对差

二叉搜索树的中序遍历结果中，相邻数字相减的最小值

## 迭代：
```java
class Solution {
    public int getMinimumDifference(TreeNode root) {
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        int pre = -1, res = Integer.MAX_VALUE;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != -1){
                res = Math.min(res, root.val - pre);
            }
            pre = root.val;
            root = root.right;
        }
        return res;
    }
}
```
中序遍历模板：
```java
class Solution{
    public void minSearch(TreeNode root){
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 节点不为空，或者栈里面还有剩下没被遍历的左节点们
        while(root != null || !stack.isEmpty()){
            // 中序遍历，先遍历到极左：
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            // 中序遍历，极左遍历完了，遍历中间的节点：
            root = stack.pop(); // 栈中弹出最后遍历到的左节点
            root = root.rigth; // 最后遍历右节点
        }
    }
}
```

## 递归：

```java
class Solution {

    int pre = -1;
    int ans = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if(root == null){
            return ans;
        }
        getMinimumDifference(root.left);
        if(pre != -1){
            ans = Math.min(ans, root.val - pre);
        }
        pre = root.val;
        getMinimumDifference(root.right);
        return ans;
    }
}
```

# 236.二叉树的最近公共祖先

## dfs

回溯的时候判断，该节点 x 本身及其子节点是否包含p或q中的一个，判断条件：
- x == p || x == q || lson || rson

- 如果一个节点 x 的左子树 lson 和 右子树 rson 各自包含一个 p 或者 q 节点，那么 x 就是最近公共祖先
- 如果一个节点 x 本身是 p 或者 q 节点，x 的 lson 或者 rson 包含剩下的 p 或者 q 节点，那么 x 就是最近公共祖先

转换为：
- (lson && rson) || ((x == p || x == q) && (lson || rson))


```java
class Solution {
    TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        dfs(root, p, q);
        return ans;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return false;

        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);

        if((lson && rson) || ((root.val == p.val || root.val == q.val)&&(lson || rson))){
            ans = root;
            return true;
        }

        return root.val == p.val || root.val == q.val || lson || rson;
    }
}
```

优雅的解法：
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;     // 1)一个是另外一个的祖先
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;     // 2)左右两边各自有一个o1、o2，返回这个祖先"
        }
        return left != null ? left : right;
        // 1) / 2) 找不到，回溯时一直是null，如果找到了，那么将找到的值往上窜！
    }
}
```

## 存储父节点

从根节点 dfs，存储每个节点的父节点

从 p 开始向上搜索，将遇到的节点进行记录

再次从 q 开始向上搜索，如果遇到了之前遇到的节点，这个节点就是最近公共祖先

