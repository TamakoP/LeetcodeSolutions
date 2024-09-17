package Tree;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;

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
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1 ;
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
     * @Description 合并两棵树：要么相加要么直接替换
     * @Param [t1, t2]
     * @return Tree.TreeNode
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        //上面三个判断结束后势必产生一个新的root
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left=mergeTrees(t1.left,t2.left);
        root.right=mergeTrees(t1.right,t2.right);
        return root;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        //用减法来验证可以避免新建一个变量，每次更新sum
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum- root.val);
    }
    /**
     * @Description 统计左叶子节点的值
     * @Param [root]
     * @return int
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null) return 0;
        if(isLeaf(root.left))
            return root.left.val+sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
    }
    private boolean isLeaf(TreeNode tn){
        if(tn.left==null&&tn.right==null)
            return true;
        return false;
    }

    /**
     * @Description 间隔遍历
     * @Param
     * @return
     */
    //用来存放每个树节点的孙子
    private Map<TreeNode,Integer> cache=new HashMap<>();
    public int rob(TreeNode root) {
        if(root==null) return 0;
        if(cache.containsKey(root)) return cache.get(root);
        int sum=root.val;
        if (root.left != null) sum += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) sum += rob(root.right.left) + rob(root.right.right);
        int nextSum=rob(root.left) + rob(root.right);
        int res=Math.max(sum,nextSum);
        cache.put(root,res);
        return res;

    }
}