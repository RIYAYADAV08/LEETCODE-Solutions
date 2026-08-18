class Solution {
    public int largestInteger(int[] nums, int k) {

        int ans = -1;

        for (int x : nums) {

            int count = 0;

            // Har size-k subarray check karo
            for (int i = 0; i <= nums.length - k; i++) {

                boolean found = false;

                // Current subarray me x hai ya nahi
                for (int j = i; j < i + k; j++) {
                    if (nums[j] == x) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    count++;
                }
            }

            // Exactly ek subarray me present hai
            if (count == 1) {
                ans = Math.max(ans, x);
            }
        }

        return ans;
    }
}