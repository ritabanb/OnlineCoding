package Amazon_Prepare;

import java.util.Map;

public class DynamicProgramming {
    public int climbStairs(int n) {
        if (n == 0 || n == 1)
            return 1;
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[n];
    }

    public int maxProfit(int[] prices){
        int currMax = 0;
        int totalMax = 0;
        for(int i = 1; i < prices.length; i++){
            currMax = Math.max(0, currMax+prices[i]-prices[i-1]);
            totalMax = Math.max(totalMax, currMax);
        }
        return totalMax;
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0)
            return 0;
        int preSum = nums[0];
        int maxSum = preSum;

        for (int i = 1; i < nums.length; i++) {
            preSum = Math.max(preSum + nums[i], nums[i]);
            maxSum = Math.max(preSum, maxSum);
        }
        return maxSum;
    }
}
