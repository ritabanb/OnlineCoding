package Amazon_Prepare;

import java.util.*;
import java.util.LinkedList;

public class TreesAndGraphs {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }

        if(isValidBST(root.left) && (prev == null || root.val > prev.val)) {
            prev = root;
            return isValidBST(root.right);
        }

        return false;
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null)
            return true;
        if(root1 == null || root2 == null)
            return false;
        return root1.val == root2.val && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    public int closestValue(TreeNode root, double target) {
        int ret = root.val;
        while(root != null){
            if(Math.abs(target - root.val) < Math.abs(target - ret)){
                ret = root.val;
            }
            root = root.val > target? root.left: root.right;
        }
        return ret;
    }

    public int sumNumbers(TreeNode root) {
        return help(root, 0);
    }

    int help(TreeNode root, int sum){
        if (root == null) return 0;
        sum = 10*sum + root.val;
        if (root.left == null && root.right == null) return sum;
        return help(root.left, sum)+help(root.right, sum);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums,0,nums.length-1);
    }

    public TreeNode construct(int[] nums,int left,int right){
        if (left > right)return null;
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = left;i<=right;i++) {
            if (max < nums[i]){
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = construct(nums,left,maxIndex-1);
        root.right = construct(nums,maxIndex+1,right);
        return root;
    }

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if(root == null)return false;
        if(set.contains(k - root.val))return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null||t==null)
            return false;
        //preview the tree
        return isSameTree(s,t)|isSubtree(s.left,t)|isSubtree(s.right,t);
    }

    public boolean isSameTree(TreeNode node1,TreeNode node2){
        if(node1==null&&node2==null)
            return true;
        if(node1==null||node2==null)
            return false;
        return node1.val==node2.val&&isSameTree(node1.left,node2.left)&&isSameTree(node1.right,node2.right);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> solution = new ArrayList<>();
        if(root==null)
            return solution;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode temp;
        while(q.size()!=0)
        {
            List<Integer> li = new ArrayList<>();
            int size = q.size();
            for(int i=0;i<size;i++)
            {
                temp = q.poll();
                if(temp.left!=null)
                    q.add(temp.left);
                if(temp.right!=null)
                    q.add(temp.right);
                li.add(temp.val);
            }
            solution.add(li);
        }
        return solution;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        TreeNode suc = null;
        boolean foundP = false;
        while (root != null ) {
            if (root.val > p.val) {
                suc = root;
                root = root.left;
            } else if (root.val == p.val) {
                root = root.right;
                foundP = true;
            } else
                root = root.right;
        }
        if (!foundP) return null;
        return suc;
    }

    public int numIslands(char[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfsFill(grid,i,j);
                    count++;
                }
            }
        return count;
    }

    private void dfsFill(char[][] grid,int i, int j){
        if(i>=0 && j>=0 && i<grid.length && j<grid[0].length&&grid[i][j]=='1'){
            grid[i][j]='0';
            dfsFill(grid, i + 1, j);
            dfsFill(grid, i - 1, j);
            dfsFill(grid, i, j + 1);
            dfsFill(grid, i, j - 1);
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if(n==1)
            return true;
        if(edges == null ||edges.length==0)
            return false;

        Set<Integer> visited = new HashSet<Integer>();
        List<List<Integer>> graph = new ArrayList(n);
        for (int i = 0; i < n; i++)
            graph.add(i, new ArrayList<Integer>());
        for(int i=0; i<edges.length; i++){
            graph.get(edges[i][1]).add(edges[i][0]);
            graph.get(edges[i][0]).add(edges[i][1]);
        }
        helper(graph, 0, visited,-1);
        return visited.size()==n && edges.length == n-1;
    }
    private void helper( List<List<Integer>> graph, int node, Set<Integer> visited,int parent){
        if(visited.contains(node))
            return;
        visited.add(node);
        for(int edge : graph.get(node)){
            helper(graph, edge, visited, node);
        }
    }
}
