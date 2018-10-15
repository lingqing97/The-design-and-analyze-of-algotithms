//0-1背包问题:给定n种物品和一背包，物品i的重量是wi,其价值为vi，背包的容量为C,问：应该如何选择装入背包的物品，使得装入背包中物品的总价值最大
//思路:
//设m(i,j)是背包容量为j，可选择物品为i,i+1,...n时0-1背包问题的最优值，可以建立如下计算m(i,j)的递归式:
//		 {max(m(i+1,j),m(i+1,j-wi)+vi)    j>=wi     背包剩余的容量可以容纳i号物品的重量
//m(i,j)={
//		 {m(i+1,j)     0<=j<wi		当前背包剩余容量不可以容纳i号物品
//		 {vn        j>wn
//m(n,j)={
//		 {0         0<=j<wn
package Dynamic_planning;

public class bag01_question {
public static void knapsack(int[] v,int[] w,int c,int[][] m)
{
	int n=v.length-1;
	int jMax=Math.min(w[n]-1, c);	
	for(int j=0;j<=jMax;j++)
	{
		m[n][j]=0;		//当wn大于背包剩余容量j时，无法再装入
	}
	for(int j=w[n];j<=c;j++)
	{
		m[n][j]=v[n];		//当wn小于背包剩余容量j时，可以装入第n号物品
	}
	for(int i=n-1;i>1;i--)
	{
		jMax=Math.min(w[i]-1, c);
		for(int j=0;j<=jMax;j++)
			m[i][j]=m[i+1][j];	//当i号物品的wi大于背包剩余容量j时，无法装入i号物品
		for(int j=w[i];j<=c;j++)
			m[i][j]=Math.max(m[i+1][j], m[i+1][j-w[i]]+v[i]);	//当i号物品的wi小于背包剩余容量j时，可以装入也可以不装人，从中选出最优的;
	}
	m[1][c]=m[2][c];
	if(c>=w[1])
		m[1][c]=Math.max(m[1][c], m[2][c-w[1]]+v[1]);	//对1号物品做出最优选择
}
public static void traceback(int[][] m,int[] w,int c,int[] x)
{
	int n=w.length-1;
	for(int i=1;i<n;i++)	//循环遍历判断是否选择i号物品
	{
		if(m[i][c]==m[i+1][c]) x[i]=0;
		else
		{
			x[i]=1;
			c-=w[i];	
		}
	}
	x[n]=(m[n][c]>0)?1:0;
}
public static void main(String[] args) {
	int[] v={0,6,3,5,4,6};	//假想的0号物品质量和将价值都为0，方便算法的设计
	int[] w={0,2,2,6,5,4};
	int c=10;
	int n=5;
	int[][] m=new int[n+1][c+1];
	int[] x=new int[n+1];
	knapsack(v, w, c, m);
	traceback(m, w, c, x);
	System.out.println("最优值为:"+m[1][10]);
	for(int i=1;i<x.length;i++)
		if(x[i]==1)
			System.out.println("选择了"+i+"号物品");
}
}
