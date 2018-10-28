//n后问题
//输入:
//n×n的棋盘
//n个皇后
//输出:
//n个皇后的放置方案
//任意两个皇后都不在同一行、同一列或同一斜线上
//解空间:(排列树)
//每行有且仅有一个皇后
//用x[i]表示第i行皇后位于第几列
//此皇后的坐标为(i,x[i])
//问题的解是x[1,…, n]，满足
//任意两个皇后不在同一列上：x[i]!=x[j]
//任意两个皇后不在同一斜线上:| i-j | != | x[i] - x[j] |
//
//sum记录当前已找到的可行方案数
package TrackBack;

public class n_queue_question {
static int n;	//皇后个数
static int[] x;		//当前解
static long sum;	//当前已找到的可行方案数

public static long nQueen(int nn)
{
	n=nn;
	sum=0;
	x=new int [n+1];
	for(int i=0;i<=n;i++) x[i]=0;
	backtrack(1);
	return sum;
}
private static boolean constraint(int k)
{
	//将当前行,即第K行的皇后的位置与前k-1行的皇后的位置进行比对，判断放置的位置是否合法
	for(int j=1;j<k;j++)
		if(((Math.abs(k-j)==Math.abs(x[j]-x[k]))||x[j]==x[k])) return false;
		return true;
}
private static void backtrack(int t)
{
	if(t>n) 
	{
		sum++;
		System.out.println("可行方案"+sum+"为:");
		for(int i=1;i<=n;i++)
			System.out.println("("+i+","+x[i]+")");
	}
	else
	{
		for(int i=1;i<=n;i++)
		{
			x[t]=i;
			if(constraint(t)) backtrack(t+1);
		}
	}
}
public static void main(String[] args) {
	System.out.println("可行方案总数为:"+nQueen(4));
}
}
