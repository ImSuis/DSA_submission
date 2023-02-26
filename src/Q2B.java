
public class Q2B {
    int res = 0;

    public int minCameraCover(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }

    public static void main(String[] args) {
        // Create the binary tree from the given input
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = null;
        root.left.right = new TreeNode(0);
        root.left.right.left = null;
        root.left.right.right = new TreeNode(0);
        root.right = null;

        Q2B solution = new Q2B();
        int result = solution.minCameraCover(root);

        System.out.println(result);
    }
}