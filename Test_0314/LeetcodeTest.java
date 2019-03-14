package Test_0314;

import java.util.Arrays;

/*
1006. 笨阶乘  
通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。
                 (10 * 9 / 8 + 7 ) - (6 * 5 / 4 + 3) + 6 - 2 * 1
然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。

示例 1：
输入：4
输出：7
解释：7 = 4 * 3 / 2 + 1
示例 2：
输入：10
输出：12
解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
         
提示：
1 <= N <= 10000
-2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
 */
/*
 * 分析：
 * 通过上题，可以发现，除非遇到减号，我们都可以将部分的和计算出来，
 * 比如：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 *        = (10 * 9 / 8 + 7) - (6 * 5 / 4 - 3) - 2 * 1;
 * 发现当N不是4的倍数时会有不完整的一组数需要计算，我们可以先计算这组数，结果记为d，
 * 将每四个数归在一起计算，遇到减号在计算下一组，最后计算的结果存放在sum中，
 * 若sum等于0，说明N小于4，返回d，否则返回sum-d.
 */
//public class LeetcodeTest {
//	public static void main(String[] args) {
//		Solution So = new Solution();
//		int N = 10;
//		System.out.println(So.clumsy(N));
//	}
//}
//class Solution {
//    public int clumsy(int N) {
//    	int d = 0;
//    	if(N%4 == 1){
//    		d = 1;
//    	}else if(N%4 == 2){
//    		d = 2;
//    	}else if(N%4 == 3){
//    		d = 6;
//    	}
//    	int f = 0;
//    	int sum = 0;
//    	for(int i=N;i>N%4;i-=4){
//    		if(f == 0){
//    			sum += (i * (i-1) / (i-2) + i-3);	
//    			f = 1;
//    		}else{
//    			sum -= (i * (i-1) / (i-2) - i+3);	
//    		}
//    	}
//    	return sum == 0 ? d : sum-d;
//    }
//}
/*
1007. 行相等的最少多米诺旋转
在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）
我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。
返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。
如果无法做到，返回 -1.
输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
输出：2
解释：
图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
示例 2：
输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
输出：-1
解释：
在这种情况下，不可能旋转多米诺牌使一行的值相等。
提示：
1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000
 */
/*
 * 分析：
 * 分析题可以发现，牌的倒向方式有两种，一种是遍历A，B中遇到相同的倒向A，一种是遍历B，A中遇到相同的倒向B,将倒向的过程封装成一个方法，
 * 在swingToA(int[]A, int[]B)方法中，用temp存出现元素的个数，索引+1代表出现的数字，此索引下的内容代表这个数字出现的次数，
 * 遍历A中的数组，若发现已经在前面出现过（由于有数组temp,可以直接查找相对应的索引），就跳过这个数，
 *            没有出现过，指针j就同时遍历A、B数组，同时计数B倒向A的个数(d)和倒向A后此数的个数(count)，
 *            若count等于A.length,就返回d,不等于就到下一个数，同样是如上操作。
 * 遍历完毕还是没能使A中的数都相同，就返回-1.
 * 在minDominoRotations(int[] A, int[] B)方法中，两次调用，返回A倒向B和B倒向A的最小值。
 */
public class LeetcodeTest {
	public static void main(String[] args) {
		Solution So = new Solution();
		int[] A = {2,1,2,4,2,2};
		int[] B = {5,2,6,2,3,2};
		System.out.println(So.minDominoRotations(A, B));
	}
}
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
    	int a = swingToA(A, B);
    	int b = swingToA(B, A);
    	return Math.min(a, b);
    }
    private int swingToA(int[]A, int[]B){
    	int[] temp = new int[6];
    	for(int i=0; i<A.length; i++){
    		if(temp[A[i]-1] == 0){
    			temp[A[i]-1] = 1;
    			int count = 1;
    			int d = 0;
        		for(int j=0; j<B.length; j++){
        			if(j!=i){
            			if((A[j] == A[i]) || (B[j] == A[i])){
            				count++;
            			}
            			if((B[j] == A[i]) && (A[j] != A[i])){
            				d++;
            			}
        			}
        		}
        		if(count == A.length){
        			return d;
        		}
    		}
    	}
    	return -1;
    }
}