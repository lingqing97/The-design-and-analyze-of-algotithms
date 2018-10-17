//单源最短路径:给定带权有向图G=(V,E),其中每条边的权是非负实数，另外，还给定V中的一个顶点，称为源。现在要计算从源到所有其他各顶点的最短路长
//			  度。这里的路的长度是指路上各边权之和。
//Dijkstra算法:设置顶点集合s并不断地做贪心选择来扩充这个集合。一个顶点属于集合s当且仅当从源到该顶点的最短路径长度已知，初始时，s中仅有源点
//			 设u是G的某个顶点，把从源到u且中间只经过s中顶点的路称为从源到u的特殊路径，并用数值dist记录当前每个顶点所对应的最短特殊路径长度。
//			 
//Dijkstra中更新最短路径长度的规则:dist[u]+a[u][i]<dist[i]时,就置prev[i]=u.(a[u][i]:从u到i的边长;prev[i]到i顶点的前一个顶点)

package greedy_algorithm;

public class Dijkstra {
public static void dijkstra(int v,float[][] a,float[] dist,int[] prev)
{
	int n=a.length-1;
	if(v<1||v>n) return;
	boolean[] s=new boolean[n+1];
	//初始化与源点v相邻的各顶点信息(dist,prev,s)
	for(int i=1;i<=n;i++)
	{
		dist[i]=a[v][i];
		s[i]=false;
		if(dist[i]==Float.MAX_VALUE) prev[i]=0;
		else prev[i]=v;
	}
	//初始化源点v;
	dist[v]=0;
	s[v]=true;
	//遍历n-1次，将剩余的n-1个顶点加入s集合中
	for(int i=1;i<n;i++)
	{
		float temp=Float.MAX_VALUE;
		int u=v;
		//查找到s集合最近的顶点，将其加入s集合中
		for(int j=1;j<=n;j++)	
		{
			if(!s[j]&&dist[j]<temp)		//遍历所有不在s集合中的顶点，找出最短特殊路径
			{
				u=j;
				temp=dist[j];
			}
		}
		s[u]=true;    //将u加入s集合中
		//遍历与u相邻且不在s集合中的顶点，判断加入u后，dist[j]是否发生变化
		for(int j=1;j<=n;j++)
		{
			if(!s[j]&&a[u][j]<Float.MAX_VALUE)
			{
				float dis=dist[u]+a[u][j];
				if(dis<dist[j])	
				{
					dist[j]=dis;	//更新dist[j]
					prev[j]=u;		//将先导顶点设置为u
				}
			}
		}
	}
}
public static void main(String[] args) {
	float[][] a={{0,0,0,0,0,0},
			{0,0,10,Float.MAX_VALUE,30,100},
			{0,Float.MAX_VALUE,0,50,Float.MAX_VALUE,Float.MAX_VALUE},
			{0,Float.MAX_VALUE,Float.MAX_VALUE,0,Float.MAX_VALUE,10},
			{0,Float.MAX_VALUE,Float.MAX_VALUE,20,0,60},
			{0,Float.MAX_VALUE,Float.MAX_VALUE,Float.MAX_VALUE,Float.MAX_VALUE,0}};
	int v=1;
	float[] dist=new float[6];
	int[] prev=new int[6];
	dijkstra(v, a, dist, prev);
	for(int i=1;i<=5;i++)
		System.out.println(dist[i]);
}
}
