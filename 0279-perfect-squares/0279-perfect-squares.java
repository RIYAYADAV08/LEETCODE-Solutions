class Solution {
    public int numSquares(int n) {
        
        int[] dp = new int[n + 1];
        
        // dp[i] = minimum number of perfect squares
        // required to make sum i
        
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // worst case: 1 + 1 + 1 + ...
            
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        return dp[n];
    }
}