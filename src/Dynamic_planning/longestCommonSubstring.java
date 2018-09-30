//最长公共子序列:给定两个序列x和y,当另一序列z即是x的子序列又是y的子序列时，则称z是x和y的公共子序列;
//给定x和y序列，找出他们的最长公共子序列；

//思路:
//设序列x={x1,x2,x3,...xi}和y={y1,y2,y3,...yj},则有以下两种情况：
//1.xi==yj,则问题等价于求x(i-1)和y(j-1)的最长公共子序列
//2.xi!=yj，则x和y的最长公共子序列等于 x(i-1)和yj的最长公共子序列 和 xi 和y(j-1)的最长公共子序列 中的较大值

//递归定义最优解：
//设c(i,j)是x={x1,x2,x3,...xi}和y={y1,y2,y3,...yj}的最长公共子序列的长度
//则：
//		 {	0      i=0||j=0	
//c(i,j)={	c(i-1,j-1)+1   xi==yj
//		 {	max( c(i-1,j), c(i,j-1)) xi!=yj  && i>0 && j>0

package Dynamic_planning;

import sun.applet.Main;

public class longestCommonSubstring {
//int[][] b用于记录c[i][j]的值是由哪个子问题得到的；
//若b[i][j]=1,则是由c(i,j)=c(i-1,j-1)+1得到的
//若b[i][j]=2,则是由c(i,j)=max( c(i-1,j), c(i,j-1)) 且 c(i-1,j) >= c(i,j-1)
//若b[i][j]=3,则是由c(i,j)=max( c(i-1,j), c(i,j-1)) 且 c(i-1,j) < c(i,j-1)
	public static int lcslength(char[] x,char[] y,int[][] b)
	{
		int len_x=x.length;
		int len_y=y.length;
		int[][] c=new int[len_x+1][len_y+1];
		for(int i=0;i<=len_x;i++) c[i][0]=0;
		for(int i=0;i<=len_y;i++) c[0][i]=0;
		for(int i=1;i<=len_x;i++)	//i,j都是从1开始,即x和y都是从非空序列开始
		{
			for(int j=1;j<=len_y;j++)
			{
				if(x[i-1]==y[j-1])		//判断x的第i号元素是否等于y的第j号元素，采用索引表示法，需要都减一
				{
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]=1;
				}
				else if(c[i-1][j]>=c[i][j-1])
				{
					c[i][j]=c[i-1][j];
					b[i][j]=2;
				}
				else
				{
					c[i][j]=c[i][j-1];
					b[i][j]=3;
				}
			}
		}
		return c[len_x][len_y];
	}
	public static void lcs(int i,int j,char[] x,int[][] b)
	{
		if(i==0||j==0) return;
		if(b[i][j]==1) 
		{
			lcs(i-1,j-1,x,b);
			System.out.print(x[i-1]);
		}
		else if(b[i][j]==2) lcs(i-1,j,x,b);
		else lcs(i,j-1,x,b);

	}
	public static void main(String[] args) {
		char[] x={'a','b','c','d','f','e'};
		char[] y={'a','b','c','t'};
		int[][] b=new int[x.length+1][y.length+1];
		System.out.println(lcslength(x, y, b));
		lcs(x.length, y.length, x, b);
	}
}
