/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
//only the root can take from both sides
//otherwise, each node has to pull from one side

class Solution {    
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0; 
        }
        
        
        int max = 0;
        
        //iterate through every root with a BFS
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.remove();
            int leftval = 0, rightval = 0;
            if (curr.left != null){
                queue.add(curr.left);
                leftval = innerDFS(curr.left);
            }
            if (curr.right != null){
                queue.add(curr.right);
                rightval = innerDFS(curr.right);
            }
            max = Math.max(leftval + rightval, max);
        }
        return max; 
    }
    
    private int innerDFS(TreeNode curr){
        if (curr == null){
            return 1;
        }
        int leftval = 0, rightval = 0; 
        if (curr.left != null){
            leftval = innerDFS(curr.left);
        }
        if (curr.right != null){
            rightval = innerDFS(curr.right);
        }
        return Math.max(leftval, rightval) + 1; 
    }
}
