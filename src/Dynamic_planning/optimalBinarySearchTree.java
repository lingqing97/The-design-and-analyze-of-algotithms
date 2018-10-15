package Dynamic_planning;

public class optimalBinarySearchTree {
	static final int n = 5;
	public static void optimalBinarySearchTree(double[] a,double[] b,double[][] m,int[][] s,double[][] w)
	{
		int n=a.length-1;
		for(int i=0;i<=n;i++)
		{
			m[i+1][i]=0;
			w[i+1][i]=a[i];
		}
		for(int r=0;r<=n;r++)
			for(int i=1;i<=n-r;i++)
			{
				int j=i+r;
				w[i][j]=w[i][j-1]+a[j]+b[j];
				m[i][j]=m[i+1][j];
				s[i][j]=i;
				for(int k=i+1;k<=j;k++)
				{
					double t=m[i][k-1]+m[k+1][j];
					if(t<m[i][j]){
						m[i][j]=t;
						s[i][j]=k;
					}
				}
				m[i][j]+=w[i][j];
			}
	}
	public static void TrackBack(int[][] s,int i,int j,int r)
	{
		int root=s[i][j];
		if(root==s[1][n])
		{
			System.out.print("k"+root+"是根节点\n");
			TrackBack(s,i,root-1,root);
			TrackBack(s,root+1,j,root);
		}
		if(j<(i-1))
		{
			return;
		}
		else if(j==(i-1))
		{
			if(j<r)
				System.out.print("d"+j+"是"+"k"+r+"的左孩子\n");
			else
				System.out.print("d"+j+"是"+"k"+r+"的右孩子\n");
			return;
		}
		else
		{
			if(root<r)
				System.out.print("k"+root+"是"+"k"+r+"的左孩子\n");
			else
				System.out.print("k"+root+"是"+"k"+r+"的右孩子\n");
		}
		TrackBack(s, i, root-1, root);
		TrackBack(s, root+1, j, root);
	}
	public static void main(String[] args) {
		double[] p = {-1,0.15,0.1,0.05,0.1,0.2};
		double[] q = {0.05,0.1,0.05,0.05,0.05,0.1}; 
		int[][] s=new int[n+1][n+1];
		double[][] w=new double[n+2][n+2];
		double[][] m=new double[n+2][n+2];
		optimalBinarySearchTree(p, q, m, s, w);
		System.out.print("最优二叉搜索树为:\n");
		TrackBack(s, 1, n, -1);
		
	}
}
