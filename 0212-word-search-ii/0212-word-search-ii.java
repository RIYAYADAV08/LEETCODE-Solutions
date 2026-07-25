class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {

        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null)
                    node.children[idx] = new TrieNode();
                node = node.children[idx];
            }
            node.word = word;
        }

        List<String> ans = new ArrayList<>();

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, ans);
            }
        }

        return ans;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> ans) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
            return;

        char c = board[i][j];

        if (c == '#')
            return;

        node = node.children[c - 'a'];

        if (node == null)
            return;

        if (node.word != null) {
            ans.add(node.word);
            node.word = null;
        }

        board[i][j] = '#';

        dfs(board, i + 1, j, node, ans);
        dfs(board, i - 1, j, node, ans);
        dfs(board, i, j + 1, node, ans);
        dfs(board, i, j - 1, node, ans);

        board[i][j] = c;
    }
}