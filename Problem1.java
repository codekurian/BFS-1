import java.util.*;

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
class Problem1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrderList = new ArrayList<List<Integer>>();
        //return levelOrderBFS(root,levelOrderList);
        dfs(root,0,levelOrderList);
        return levelOrderList;


    }

    //TC: O(N)
//SC: O(N/2) == O(N) N/2 children will be in the queue
    public List<List<Integer>> levelOrderBFS(TreeNode root, List<List<Integer>> levelOrderList) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        if (root != null) {
            List<Integer> rootNodeList = new ArrayList<>();
            rootNodeList.add(root.val);
            levelOrderList.add(rootNodeList);
            bfs(queue, levelOrderList);
        }


        return levelOrderList;
    }

    public void bfs(Queue<TreeNode>levelQueue,List<List<Integer>> levelOrderList) {
        int levelCount = 0;
        while(!levelQueue.isEmpty()) {
            int levelSize = levelQueue.size();
            List<Integer> levelNodes = new LinkedList<Integer>();
            levelCount++;
            for(int i = 0;i<levelSize;i++) {
                TreeNode levelNode = levelQueue.poll();




                if(levelNode.left!=null) {
                    levelNodes.add(levelNode.left.val);
                    levelQueue.offer(levelNode.left);
                }
                if(levelNode.right!=null) {
                    levelQueue.offer(levelNode.right);
                    levelNodes.add(levelNode.right.val);
                }





            }
            if(!levelNodes.isEmpty()) {
                levelOrderList.add(levelNodes);
            }



        }
    }

    //TC : O(N)
    //SC : O(H) stack size
    public void dfs (TreeNode node , int level,List<List<Integer>> levelOrderList){
        if(node == null){
            return;
        }

        if(levelOrderList.size()-1 >= level){
            List<Integer> levelLst = levelOrderList.get(level);
            levelLst.add(node.val);
        }else{
            List<Integer> levelLst = new ArrayList<>();
            levelLst.add(node.val);
            levelOrderList.add(levelLst);
        }
        level = level+1;
        dfs(node.left,level,levelOrderList);
        dfs(node.right,level,levelOrderList);
    }
}