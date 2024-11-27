package Tree;

import LinkLists.ListNode;
import com.sun.source.tree.Tree;

import java.util.*;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/24 下午2:03
 * @Version: 1.0
 */

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x){
        val=x;
    }
}
public class treeSolutions {
    //树的高度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth2(root);
        return result;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        if (Math.abs(l - r) > 1) result = false;
        return 1 + Math.max(l, r);
    }

    /**
     * @return Tree.TreeNode
     * @Description 合并两棵树：要么相加要么直接替换
     * @Param [t1, t2]
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        //上面三个判断结束后势必产生一个新的root
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        //用减法来验证可以避免新建一个变量，每次更新sum
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * @return int
     * @Description 统计左叶子节点的值
     * @Param [root]
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (isLeaf(root.left))
            return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode tn) {
        if (tn.left == null && tn.right == null)
            return true;
        return false;
    }

    /**
     * @Description 间隔遍历
     * @Param
     * @return
     */
    //用来存放每个树节点的孙子
    private Map<TreeNode, Integer> cache = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (cache.containsKey(root)) return cache.get(root);
        int sum = root.val;
        if (root.left != null) sum += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) sum += rob(root.right.left) + rob(root.right.right);
        int nextSum = rob(root.left) + rob(root.right);
        int res = Math.max(sum, nextSum);
        cache.put(root, res);
        return res;

    }

    /**
     * @return java.util.List<java.lang.Double>
     * @Description 一棵树每层节点的平均数
     * @Param [root]
     */
    public List<Double> averageOfLevels(TreeNode root) {
        //牢记List只是一个抽象类，必须实例化用ArrayList
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> qu = new LinkedList<TreeNode>();
        qu.offer(root);

        while (!qu.isEmpty()) {
            //进入for循环之前队列的长度对应同一层的节点个数
            int size = qu.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                //注意peek函数只是返回队首元素，poll返回队首元素并且将其从队列中删除
                TreeNode tn = qu.poll();
                sum += tn.val;
                if (tn.left != null) qu.offer(tn.left);
                if (tn.right != null) qu.offer(tn.right);
            }
            res.add(sum / size);

        }
        return res;

    }

    // 非递归的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tn = stack.pop();
            res.add(tn.val);
            //先压栈右节点，再压栈左节点
            if (tn.right != null) stack.push(tn.right);
            if (tn.left != null) stack.push(tn.left);

        }
        return res;
    }

    // 非递归的后序遍历   后序：左右根 （翻转结果 根右左） 修改前序的方法先把右节点压栈
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tn = stack.pop();
            res.add(tn.val);
            //先压栈右节点，再压栈左节点

            if (tn.left != null) stack.push(tn.left);
            if (tn.right != null) stack.push(tn.right);
        }
        Collections.reverse(res);
        return res;
    }

    // 非递归的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tn = root;
        while (tn != null || !stack.isEmpty()) {
            while (tn != null) {
                stack.push(tn);
                tn = tn.left;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            tn = node.right;
        }
        return res;
    }

    /**
     * @return Tree.TreeNode
     * @Description 修剪二叉树：只保留值在 L ~ R 之间的节点
     * @Param [root, L, R]
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val > R) trimBST(root.left, L, R);
        if (root.val < L) trimBST(root.right, L, R);
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    /**
     * @return int
     * @Description 寻找二叉查找树的第 k 个元素
     * @Param [root, k]
     */
    public int kthSmallest(TreeNode root, int k) {
        //curCount表示比当前节点的值小的个数
        int curCount = count(root.left);
        if (k - 1 == curCount) return root.val;
        if (k > curCount + 1) return kthSmallest(root.right, k - curCount - 1);
        return kthSmallest(root.left, k);

    }

    private int count(TreeNode root) {
        if (root == null) return 0;
        return count(root.left) + count(root.right) + 1;
    }

    /**
     * @Description 把二叉查找树每个节点的值都加上比它大的节点的值: 对于左节点，只需要加上其父节点的值；对于非叶子节点，加上其右支节点所有值
     * @Param
     * @return
     */
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traver(root);
        return root;
    }

    private void traver(TreeNode node) {
        if (node == null) return;
        traver(node.right);
        sum += node.val;
        node.val = sum;
        traver(node.left);
    }

    /**
     * @return Tree.TreeNode
     * @Description 二叉查找树的最近公共祖先:两个节点刚好一左一右，返回root；都大于root，递归更新至root.right；都小于root,递归更新至root.left
     * @Param [root, p, q]
     */
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestorBST(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestorBST(root.right, p, q);
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果其中之一是root，祖先只能是root
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    /**
     * @return Tree.TreeNode
     * @Description 从有序数组中构造二叉查找树
     * @Param [nums]
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        return toBST(nums, 0, nums.length - 1);
    }

    // 构造平衡的二叉查找树，使用二分法
    private TreeNode toBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBST(nums, start, mid);
        root.right = toBST(nums, mid, end);
        return root;
    }

    public TreeNode sortedListToBST(ListNode<Integer> head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.e);
        ListNode preMid = preMid(head);
        ListNode mid = preMid.next;
        preMid.next = null;  // 断开链表
        TreeNode t = new TreeNode((Integer) mid.e);
        t.left = sortedListToBST(head);
        t.right = sortedListToBST(mid.next);
        return t;
    }

    private ListNode preMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }

}

