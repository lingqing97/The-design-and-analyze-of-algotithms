//单源最短路径问题：
//输入:
//有向带权图G=(V, E)
//图中顶点s
//输出:
//s到图中其它顶点的最短路径
package branch_and_bound_method;

public class BBShortSet {
static class HeapNode implements Comparable
{
	int i;//顶点编号
	float length;//当前路长
	HeapNode(int ii,float ll){
		i=ii;
		length=ll;
	}
	public int compareTo(Object x)
	{
		float xl=((HeapNode)x).length;
		if(length<xl) return -1;
		if(length==xl) return 0;
		return 1;
	}
}

static float[][] a={{0,0,0,0,0,0},
	{0,0,10,Float.MAX_VALUE,30,100},
	{0,Float.MAX_VALUE,0,50,Float.MAX_VALUE,Float.MAX_VALUE},
	{0,Float.MAX_VALUE,Float.MAX_VALUE,0,Float.MAX_VALUE,10},
	{0,Float.MAX_VALUE,Float.MAX_VALUE,20,0,60},
	{0,Float.MAX_VALUE,Float.MAX_VALUE,Float.MAX_VALUE,Float.MAX_VALUE,0}};;//图G的邻接矩阵

public static void shortset(int v,float[] dist,int[] p)
{
	int n=p.length-1;
	MinHeap H=new MinHeap(2*n);
	//将v作为起始的源点
	HeapNode enode=new HeapNode(v,0);
	//初始化dist
	for(int i=1;i<=n;i++)
		dist[i]=Float.MAX_VALUE;
	dist[v]=0;
	//遍历解空间
	while(true)
	{
		for(int j=1;j<=n;j++)
		{
			//如果j顶点与enode顶点相邻，且dist[j]有变小
			if(a[enode.i][j]<Float.MAX_VALUE&&(enode.length+a[enode.i][j])<dist[j])
			{
				dist[j]=enode.length+a[enode.i][j];
				//更新j的前导顶点为j
				p[j]=enode.i;
				//将j号顶点压入小堆中
				HeapNode temp_node=new HeapNode(j,dist[j]);
				H.put(temp_node);
			}
			//遍历了所有的可能后退出循环
			if(H.isEmpty()) break;
			//将当前路径长度最小的顶点从小堆中取出，作为新的源点
			enode=(HeapNode)H.removeMin();
		}
	}
}
public static class MinHeap {
	private Comparable[] pq;
	private int N;
	public MinHeap(int max) {
		pq=new Comparable[max+1];
		N=0;
	}
	public void put(Comparable tmp)
	{
		if((N+1)==pq.length) resize(pq.length*2);
		pq[++N]=tmp;
		swin(N);
	}
	public Comparable removeMin()
	{
		Comparable key=pq[1];
		exch(1, N--);
//		if(N>0&&N==pq.length/4) resize(pq.length/2);
		pq[N+1]=' ';
		sink(1);
		return key;
	}
	public boolean isEmpty()
	{
		return N==0;
	}
	public int size()
	{
		return N;
	}
	private void resize(int max)
	{
		Comparable[] temp=new Comparable[max];
		for(int i=0;i<=N;i++)
			temp[i]=pq[i];
		pq=temp;
	}
	private boolean less(Comparable x,Comparable y)
	{
		return x.compareTo(y)<0;
	}
	private void exch(int x,int y)
	{
		Comparable temp=pq[x];
		pq[x]=pq[y];
		pq[y]=temp;
	}
	private void swin(int k)
	{
		while(k>1&&less(pq[k],pq[k/2]))
		{
			exch(k/2, k);
			k/=2;
		}
	}
	private void sink(int k)
	{
		while(2*k<=N)
		{
			int j=2*k;
			if(j<N&&less(pq[j+1], pq[j])) j++;
			if(!less(pq[j], pq[k])) break;
			exch(k, j);
			k=j;
		}
	}
}
public static void main(String[] args) {
	int v=0;
	float[] dist=new float[6];
	int[] p=new int[6];
	shortset(v, dist, p);
	for(int i=1;i<6;i++)
		System.out.println(i+"的前导顶点是:"+p[i]);
}
}
