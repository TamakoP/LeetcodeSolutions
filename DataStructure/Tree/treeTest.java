package Tree;

import java.util.Arrays;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/24 下午2:11
 * @Version: 1.0
 */
public class treeTest {
    public static void main(String[] args){
        TreeNode root=new TreeNode(3);
        TreeNode rl=new TreeNode(0);
        TreeNode rr=new TreeNode(4);
        TreeNode t1=new TreeNode(2);
        TreeNode t2=new TreeNode(1);
        //TreeNode t3=new TreeNode(6);
        root.left=rl;
        root.right=rr;
        rl.right=t1;
        t1.left=t2;

        treeSolutions ts=new treeSolutions();
        System.out.println(ts.kthSmallest(root,3));
    }
}
