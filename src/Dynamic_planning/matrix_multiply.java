//矩阵连乘问题：给定n个矩阵{A1,A2,A3,...An},其中，Ai与Ai+1是可乘的，i=1,2,3,4...n-1;
//找出一种计算顺序使计算量最小


package Dynamic_planning;

public class matrix_multiply {
public static void matrixChain(int[] p,int[][] m,int[][] s)
{
	int n=p.length-1;
	for(int i=1;i<=n;i++) m[i][i]=0;
	for(int r=2;r<=n;r++)
		for(int i=1;i<=n-r+1;i++)
		{
			int j=i+r-1;
			m[i][j]=m[i+1][j]+p[i-1]*p[i]*p[j];
			s[i][j]=i;
			for(int k=i+1;k<j;k++)
			{
				int z=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
				if(z<m[i][j])
				{
					m[i][j]=z;
					s[i][j]=k;
				}
			}
		}
}
public static void trackback(int[][] s,int i,int j)
{
	if(i==j) return;
	trackback(s,i,s[i][j]);
	trackback(s,s[i][j]+1,j);
	System.out.println("Multiply A"+i+","+s[i][j]+" and A"+(s[i][j]+1)+","+j);
}
public static void main(String[] args) {
	int[] p={30,35,15,5,10,20,25};
	int[][] m=new int[p.length][p.length];
	int[][] s=new int[p.length][p.length];
	matrixChain(p, m, s);
	trackback(s,1,6);
	for(int i=0;i<p.length;i++)
	{
		for(int j=0;j<p.length;j++)
			System.out.print(s[i][j]+" ");
		System.out.println();
	}
}
}
