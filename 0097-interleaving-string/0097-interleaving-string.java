class Solution {

    private int[][] dp;

    public boolean isInterleave(String s1, String s2, String s3) {

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        dp = new int[s1.length() + 1][s2.length() + 1];

        return solve(0, 0, s1, s2, s3);
    }

    private boolean solve(int i, int j, String s1, String s2, String s3) {

        if (i == s1.length() && j == s2.length()) {
            return true;
        }

        if (dp[i][j] != 0) {
            return dp[i][j] == 1;
        }

        int k = i + j;

        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            if (solve(i + 1, j, s1, s2, s3)) {
                dp[i][j] = 1;
                return true;
            }
        }

        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            if (solve(i, j + 1, s1, s2, s3)) {
                dp[i][j] = 1;
                return true;
            }
        }

        dp[i][j] = -1;
        return false;
    }
}