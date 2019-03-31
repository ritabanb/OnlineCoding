package Amazon_Prepare;

public class CalltoFunctions {

    public static void main(String[] args) {
        ArraysAndStrings obj = new ArraysAndStrings();

//        System.out.println(obj.firstUniqChar("leetcode"));

//        obj.reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});

//        obj.reverseWords(new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'});

//        int[] twoSum = obj.twoSum(new int[]{2, 7, 11, 15}, 9);
//        for (int num : twoSum)
//            System.out.print(num + " ");
//        System.out.println();

//        System.out.println(obj.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));

//        System.out.println(obj.compareVersion("0.1", "1.1"));

//        System.out.println(obj.longestPalindrome("babad"));

//        int[] product = obj.productExceptSelf(new int[]{1,2,3,4});
//        for (int num : product)
//            System.out.print(num + " ");
//        System.out.println();

//        System.out.println(obj.numberToWords(1234567891));

//        System.out.println(obj.findDuplicate(new int[]{1,3,4,2,2}));

//        int a = 7/10;
//        System.out.println(a);

        DynamicProgramming dp = new DynamicProgramming();
        System.out.println(dp.climbStairs(2));
    }
}
