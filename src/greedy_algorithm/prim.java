//最小生成树问题:设G=(V,E)是无向连通带权图，即是一个网络。E中每条边(v,w)的权为c[v][w].如果G的子图G'是一棵包含G的所有顶点的树，则称G'是
//				G的生成树。生成树上个边权值之和称为该生成树的耗费。在G的所有生成树，耗费最小的生成树称为G的最小生成树.
//Prim算法:
//		首先置S={1},然后，只要s是v的真子集，就进行如下的贪心选择:选取满足条件i属于s,j属于(v-s)，且c[i][j]最小的边,将顶点j添加到s中。
//		这个过程一直进行到s=v时为止。
//时间复杂度是O(N^2) 
package greedy_algorithm;

public class prim {
	public static void prim(int n,float[][] c)
	{
		float[] lowcost=new float[n+1];	//记录当前情况下，各顶点到树的最近距离(到树而不是到源点，这是与dijkstra的区别)
		int[] closeset=new int[n+1];		//记录当前情况下，各顶点离树中最近的顶点
		boolean[] s=new boolean[n+1];		//记录顶点是否在集合s中
		//初始化，另s={1}
		s[1]=true;
		for(int i=2;i<=n;i++)
		{
			lowcost[i]=c[1][i];
			closeset[i]=1;
			s[i]=false;
		}
		for(int i=1;i<n;i++)		//将剩余n-1个顶点加入到集合s中
		{
			float min=Float.MAX_VALUE;
			int j=1;		//j用来设置当前正在遍历的顶点，初始为1
			for(int k=2;k<=n;k++)	//找出距离树距离最近的顶点
			{
				if(!s[k]&&lowcost[k]<min)	//从到树的距离中选出最小值
				{
					min=lowcost[k];
					j=k;
				}
			}
			//找到距离树距离最近的点，将其打印出来
			System.out.println(j+", "+closeset[j]);
			s[j]=true;
			//更新剩余顶点到树的当前最近距离，和colseset[k]
			for(int k=2;k<=n;k++)
			{
				if(!s[k]&&c[j][k]<lowcost[k])
				{
					lowcost[k]=c[j][k];
					closeset[k]=j;
				}
			}
		}
	}
	public static void main(String[] args) {
		float[][] c={{0,0,0,0,0,0,0},
				{0,0,6,1,5,Float.MAX_VALUE,Float.MAX_VALUE},
				{0,6,0,5,Float.MAX_VALUE,3,Float.MAX_VALUE},
				{0,1,5,0,5,6,4},
				{0,5,Float.MAX_VALUE,5,0,Float.MAX_VALUE,2},
				{0,Float.MAX_VALUE,3,6,Float.MAX_VALUE,0,6},
				{0,Float.MAX_VALUE,Float.MAX_VALUE,4,2,6,0}};
		prim(c.length-1, c);
	}
}
