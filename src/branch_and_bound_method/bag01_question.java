//0_1 背包问题:
//限界函数bound(V) = p + q(p为当前价值，q为剩余物品一般背包问题的最优解)
//其中q是针对输入{i+1, ..., n}和C’的一般背包问题的最优解
//可以按价重比顺序依次向背包中装入物品得到q

//堆式分支限界法:
//每次从大堆中选出最大的bound(i)的节点作为扩张节点
package branch_and_bound_method;

import greedy_algorithm.bag_qustion.Element;

public class bag01_question {
	public static class MaxHeap {
		private Comparable[] pq;
		private int N;
		public MaxHeap(int max) {
			pq=new Comparable[max+1];
			N=0;
		}
		public void put(Comparable tmp)
		{
			if((N+1)==pq.length) resize(pq.length*2);
			pq[++N]=tmp;
			swin(N);
		}
		public Comparable removeMax()
		{
			Comparable key=pq[1];
			exch(1, N--);
//			if(N>0&&N==pq.length/4) resize(pq.length/2);
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
			while(k>1&&less(pq[k/2],pq[k]))
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
				if(j<N&&less(pq[j], pq[j+1])) j++;
				if(!less(pq[k], pq[j])) break;
				exch(k, j);
				k=j;
			}
		}
	}
	public static class MergeSort
	{
		private static Element[] aux;
		public static void sort(Element[] a)
		{
			aux=new Element[a.length];
			mergeSort(a,0,a.length-1);
		}
		public static void mergeSort(Element[] a,int low,int high)
		{
			if(low<high)//至少有两个元素才进行归并排序
			{
				int mid=(low+high)/2;
				mergeSort(a,low,mid);
				mergeSort(a,mid+1,high);
				merge(a,low,mid,high);
			}
		}
		public static void merge(Element[] a,int low,int mid,int high)
		{
			int j=low;
			int k=mid+1;
			for(int i=low;i<=high;i++)
				aux[i]=a[i];
			for(int i=low;i<=high;i++)
			{
				if(j>mid) a[i]=aux[k++];
				else if(k>high) a[i]=aux[j++];
				else if(aux[j].compareTo(aux[k])<0) a[i]=aux[j++];
				else a[i]=aux[k++];
			}
		}
	}

	private static class BBnode
	{
		BBnode parent;	//父节点
		boolean leftChild;	//左儿子节点标志
		
		BBnode(BBnode par,boolean ch)
		{
			parent=par;
			leftChild=ch;
		}
		
	}
	private static class HeapNode implements Comparable
	{
		BBnode liveNode;	//活节点
		double upperProfit;//节点的价值上界
		double profit;//节点所对应的价值
		double weight;//节点所对应的重量
		int level;//活节点在子集树中所处的层序号
		
		//构造方法
		HeapNode(BBnode node,double up,double pp,double ww,int lev)
		{
			liveNode=node;
			upperProfit=up;
			profit=pp;
			weight=ww;
			level=lev;
		}
		public int compareTo(Object x)
		{
			double xup=((HeapNode)x).upperProfit;
			if(upperProfit<xup) return -1;
			if(upperProfit==xup) return 0;
			return 1;
		}
	}
	private static class Element implements Comparable
	{
		int id;	//编号
		double d;//单位重量价值
		//构造方法
		public Element(int idd,double dd) {
			id=idd;
			d=dd;
		}
		public int compareTo(Object x)
		{
			double xd=((Element)x).d;
			if(d<xd) return -1;
			if(d==xd) return 0;
			return 1;
		}
	}
	public static class BBKnapsack
	{
		static double c;	//背包容量
		static int n;	//物品总量
		static double[] w;//物品重量数组
		static double[] p;//物品价值数组
		static double cw;//当前重量
		static double cp;//当前价值
		static int[] bestx;//最优解
		static MaxHeap heap;//活节点优先队列
		//初始化
	    public static double knapsack(double[] pp,double[] ww,double cc,int[] xx)
	    {
	    	c=cc;
	    	n=pp.length-1;
	    	
	    	//定义依单位重量价值排序的物品数组
	    	Element[] q=new Element[n];
	    	double ws=0.0;//装包物品重量
	    	double ps=0.0;//装包物品价值
	    	for(int i=1;i<=n;i++)
	    	{
	    		q[i-1]=new Element(i,pp[i]/ww[i]);
	    		ps+=pp[i];
	    		ws+=ww[i];
	    	}
	    	//所有物品装包
	    	if(ws<=c)
	    	{
	    		for(int i=1;i<=n;i++)
	    			xx[i]=1;
	    		return ps;
	    	}
	    	
	    	//依单位重量价值排序
	    	MergeSort.sort(q);
	    	
	    	//初始化类数据成员
	    	p=new double[n+1];
	    	w=new double[n+1];
	    	for(int i=1;i<=n;i++)
	    	{
	    		p[i]=pp[q[n-i].id];
	    		w[i]=ww[q[n-i].id];
	    	}
	    	cw=0.0;
	    	cp=0.0;
	    	bestx=new int[n+1];
	    	heap=new MaxHeap(n);
	    	
	    	//调用BBknapsack求问题的最优解
	    	double maxp=bbKnapsack();
	    	for(int i=1;i<=n;i++)
	    		xx[q[n-i].id]=bestx[i];
	    	return maxp;
	    }
		private static double bound(int i)
		{
			//计算节点所对应价值的上界
			double cleft=c-cw;//剩余容量
			double b=cp;//当前价值上界
			while(i<=n&&w[i]<=cleft)
			{
				cleft-=w[i];
				b+=p[i];
				i++;
			}
			//如果最后还未装满背包，还剩余一点空间又放不下最后一个物品
			//则装入一部分的物品
			if(i<=n) b+=(p[i]/w[i])*cleft;
			return b;
		}
		private static void addLiveNode(double up,double pp,double ww,int lev,BBnode par,boolean ch)
		{
			//将一个新的活节点插入到子集树和最大堆H中
			BBnode d=new BBnode(par, ch);
			HeapNode node=new HeapNode(d,up,pp,ww,lev);
			heap.put(node);
		}
		private static double bbKnapsack()
		{
			//初始化
			BBnode enode=null;
			int i=1;
			double bestp=0.0;
			double up=bound(1);
			//搜索子集空间树
			while(i!=(n+1))
			{
				//非叶节点
			    //检查当前扩展节点的左孩子节点
				double wt=cw+w[i];
				if(wt<c)
				{
					//左儿子节点为可行节点
					//判断是否需要进行减支操作
					if(cp+p[i]>bestp)
					{
						bestp=cp+p[i];
						addLiveNode(up, cp+p[i], cw+w[i], i+1, enode, true);
					}
				}
				up=bound(i+1);
				//检查当前扩展节点的右儿子节点
				if(up>=bestp)
				{
					//右子树可能含有最优解
					addLiveNode(up,cp,cw,i+1,enode,false);
				}
				//取下一扩展节点
				HeapNode node=(HeapNode)heap.removeMax();
				enode=node.liveNode;
				cw=node.weight;
				cp=node.profit;
				up=node.upperProfit;
				i=node.level;
			}
			//构造当前 最优解
			for(int j=n;j>0;j--)
			{
				bestx[j]=(enode.leftChild)?1:0;
				enode=enode.parent;
			}
			return cp;
		}
	}
	public static void main(String[] args) {
		double[] v={0,6,3,5,4,6};	//假想的0号物品质量和将价值都为0，方便算法的设计
	    double[] w={0,2,2,6,5,4};
		double cc=10;
		int n=5;
		int[] x=new int[n+1];
		System.out.println("最优值为:"+BBKnapsack.knapsack(v, w, cc, x));
		for(int i=1;i<x.length;i++)
			if(x[i]==1)
				System.out.println("选择了"+i+"号物品");
	}
}
