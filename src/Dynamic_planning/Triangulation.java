//凸多边形最优三角剖分：定义凸多边形p={v0,v1,v2....vn},约定v0=vn;
//将多边形分割成互不相交的三角形，使所有三角形的权值之和最小

//思路:
//设w(i,j,k)=|vivj|+|vjvk|+|vkvi|,t(i,j)为凸多边形{vi-1,vi,.....vj}的最优三角剖分所对应的权值
//则：(设2顶点多边形的权值为0)
//		 {  0    i=j
//t[i][j]{
//		 {	min(  t[i][k]+ t[k+1][j]+ w(i-1,k,j))  i<j



package Dynamic_planning;

public class Triangulation {
	public static int[][] weight={{0,2,2,3,1,4},{2,0,1,5,2,3},{2,1,0,2,1,4},{3,5,2,0,6,2},{1,2,1,6,0,1},{4,3,4,2,1,0}};
	public static int weight(int a,int b,int c)
	{
		return weight[a][b]+weight[b][c]+weight[a][c];
	}
	public static int minWeightTriangulation(int n,int[][] t,int[][] s)
	{
		for(int i=1;i<=n;i++) t[i][i]=0;
		for(int r=2;r<=n;r++)
		{
			for(int i=1;i<=n-r+1;i++)
			{
				int j=i+r-1;
				t[i][j]=t[i+1][j]+weight(i-1,i,j);
				s[i][j]=i;
				for(int k=i;k<j;k++)
				{
					int u=t[i][k]+t[k+1][j]+weight(i-1,k,j);
					if(u<t[i][j])
					{
						t[i][j]=u;
						s[i][j]=k;
					}
				}
			}
		}
		return t[1][n];
	}
	public static void TrackBack(int i,int j,int[][] s)
	{
		if(i==j) return;
		TrackBack(i,s[i][j],s);
		TrackBack(s[i][j]+1,j,s);
		System.out.println("最优分割三角形:"+"V"+(i-1)+",V"+j+",V"+s[i][j]);
	}
	public static void main(String[] args) {
		int[][] t=new int[7][7];
		int[][] s=new int[7][7];
		System.out.println("最优权值为:"+minWeightTriangulation(5, t, s));
		TrackBack(1, 5, s);
	}
}
