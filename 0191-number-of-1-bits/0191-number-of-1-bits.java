class Solution {
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count += (n & 1);   // Last bit check
            n >>>= 1;           // Unsigned right shift
        }

        return count;
    }
}