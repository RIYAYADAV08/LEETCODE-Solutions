class Solution {

    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // Store inorder value -> index
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, 0, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart,
                           int inStart, int inEnd) {

        // Base Case
        if (preStart >= preorder.length || inStart > inEnd)
            return null;

        // Root
        TreeNode root = new TreeNode(preorder[preStart]);

        // Root index in inorder
        int index = map.get(root.val);

        // Left subtree size
        int leftSize = index - inStart;

        // Left subtree
        root.left = build(preorder,
                          preStart + 1,
                          inStart,
                          index - 1);

        // Right subtree
        root.right = build(preorder,
                           preStart + leftSize + 1,
                           index + 1,
                           inEnd);

        return root;
    }
}