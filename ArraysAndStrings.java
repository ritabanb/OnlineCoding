package Amazon_Prepare;


import java.util.HashMap;

public class ArraysAndStrings {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        for(int i=0; i < s.length(); i++)
            charCount.put(s.charAt(i), charCount.getOrDefault(s.charAt(i), 0) + 1);

        for(int i=0; i < s.length(); i++)
            if(charCount.get(s.charAt(i)) == 1)
                return i;

        return -1;
    }

    public void reverseString(char[] s) {
        int index = s.length - 1;
        for (int counter = 0; counter < s.length / 2; counter++) {
            char temp = s[counter];
            s[counter] = s[index];
            s[index] = temp;
            index--;
        }

        System.out.println(s);
    }

    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    public void reverseWords(char[] str) {
        this.reverse(str, 0, str.length - 1);
        int start = 0;
        for (int end = 0; end < str.length;) {
            if (end == str.length - 1 || str[end + 1] == ' ') {
                this.reverse(str, start, end);
                start = end + 2;
                end += 2;
            }
            else
                end++;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numberIndex = new HashMap<>();
        int diff;

        for (int i = 0; i < nums.length; i++) {
            diff = target - nums[i];
            if (numberIndex.containsKey(diff)){
                return new int[]{numberIndex.get(diff), i};
            }
            numberIndex.put(nums[i], i);
        }
        return null;
    }

    public int maxSubArrayLen(int[] nums, int k) {
        int[] sum = new int[nums.length];
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum[i] = nums[i] + preSum;
            preSum += nums[i];
        }

        int len = 0;
        HashMap<Integer, Integer> targetToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (sum[i] == k) {
                len = Math.max(len, i + 1);
            }
            if (targetToIndex.containsKey(sum[i])) {
                len = Math.max(len, i - targetToIndex.get(sum[i]));
            }

            int target = sum[i] + k;
            if (!targetToIndex.containsKey(target))
                targetToIndex.put(target, i);
        }
        return len;
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int size = 0;
        if(v1.length < v2.length){
            size = v2.length;
        }else{
            size = v1.length;
        }

        int[] v1int = new int[size];
        int[] v2int = new int[size];

        for(int i=0;i<v1.length;i++){
            v1int[i] = Integer.parseInt(v1[i]);
        }

        for(int i=0;i<v2.length;i++){
            v2int[i] = Integer.parseInt(v2[i]);
        }

        for(int i = 0; i < size; i++){
            if(v1int[i] < v2int[i]){
                return -1;
            }
            if(v1int[i] > v2int[i]){
                return 1;
            }
        }

        return 0;
    }

    public String longestPalindrome(String s) {
        return Manacher.findLongestPalindrome(s);
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    private final String[] lessThanTen = new String[]{"", "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine"};
    private final String[] lessThanTwenty = new String[]{"Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] lessThanHundred = new String[]{"", "Ten", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if(num==0)
            return "Zero";
        return number2Words(num);
    }

    private  String number2Words(int num){
        String result = new String();
        if (num < 10)
            result = lessThanTen[num];
        else if (num < 20)
            result = lessThanTwenty[num -10];
        else if (num < 100)
            result = lessThanHundred[num/10] + " " + number2Words(num % 10);
        else if (num < 1000)
            result = number2Words(num/100) + " Hundred " +  number2Words(num % 100);
        else if (num < 1000000)
            result = number2Words(num/1000) + " Thousand " +  number2Words(num % 1000);
        else if (num < 1000000000)
            result = number2Words(num/1000000) + " Million " +  number2Words(num % 1000000);
        else
            result = number2Words(num/1000000000) + " Billion " + number2Words(num % 1000000000);
        return result.trim();
    }

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 1;
        int n = nums.length;
        while (nums[slow] != nums[fast]) {
            slow = (slow + 1) % n;
            fast = (fast + 2) % n;
            if (fast == slow) fast = (fast + 1) % n;
        }
        return nums[slow];
    }

}
