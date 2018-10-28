package TrackBack;

import edu.princeton.cs.algs4.Merge;

public class bag01_question {
public static class Knapsack
{
	public static class MergeSort
	{
		private static Comparable[] aux;
		public static void sort(Comparable[] a)
		{
			aux=new Comparable[a.length];
			mergeSort(a,0,a.length-1);
		}
		public static void mergeSort(Comparable[] a,int low,int high)
		{
			if(low<high)//至少有两个元素才进行归并排序
			{
				int mid=(low+high)/2;
				mergeSort(a,low,mid);
				mergeSort(a,mid+1,high);
				merge(a,low,mid,high);
			}
		}
		public static void merge(Comparable[] a,int low,int mid,int high)
		{
			int j=low;
			int k=mid+1;
			for(int i=low;i<=high;i++)
				aux[i]=a[i];
			for(int i=low;i<=high;i++)
			{
				if(j>mid) a[i]=aux[k++];
				else if(k>high) a[i]=aux[j++];
				else if(aux[j].compareTo(aux[k])<=0) a[i]=aux[j++];
				else a[i]=aux[k++];
			}
		}
	}
	private static class Element implements Comparable
	{
		int id;		//物品编号
		double d;	//物品单位重量的价值
		private Element(int ii,double dd) {
			id=ii;
			d=dd;
		}
		public int compareTo(Object x)
		{
			double xd=((Element)x).d;
			if(d<xd) return -1;
			if(d==xd) return 0;
			return 1;
		}
		public boolean equals(Object x)
		{
			return d==((Element)x).d;
		}
	}
	
	static double c;	//背包容量
	static int n;	//物品数
	static double[] w;	//物品重量数组
	static double[] p;	//物品价值数组
	static double cw;	//当前重量
	static double cp;	//当前价值
	static double bestp;	//当前最优价值
	
	public static double knapsack(double[] pp,double[] ww,double cc)
	{
		c=cc;
		n=pp.length-1;
		cw=0.0;
		cp=0.0;
		bestp=0.0;
		//q为单位重量价值数组
		Element[] q=new Element[n];
		
		//初始化q[0:n-1]
		for(int i=1;i<=n;i++)
			q[i-1]=new Element(i,pp[i]/ww[i]);
		
		//将各物品依单位重量价值从大到小排序
		Merge.sort(q);
		
		p=new double[n+1];
		w=new double[n+1];
		for(int i=1;i<=n;i++)
		{
			p[i]=pp[q[n-i].id];
			w[i]=ww[q[n-i].id];
		}
		backtrack(1);
		return bestp;
	}
	private static void backtrack(int i)
	{
		if(i>n)
		{
			//到达叶子节点
			bestp=cp;
			return;
		}
		//搜索子树
		//如果背包未装满，进入左子树
		if(cw+w[i]<=c)
		{
			cw+=w[i];
			cp+=p[i];
			backtrack(i+1);
			cw-=w[i];
			cp-=p[i];
		}
		//依据上界函数判断是否需要进入右子树,定义bound(i+1)为接下来的最大能够得到的价值
		//如果最大期望价值大于bestp,说明接下来的遍历有必要进行
		if(bound(i+1)>bestp)
		{
			backtrack(i+1);
		}
	}
	//定义上界函数
	private static double bound(int i)
	{
		double cleft=c-cw;		//背包剩余容量
		double bound=cp;	//将最大期望价值初始化为当前价值
		//以物品单位价值递减顺序装入物品
		while(i<=n&&w[i]<=cleft)
		{
			cleft-=w[i];
			bound+=p[i];
			i++;
		}
		
		//装满背包
		if(i<=n)
		{
			bound+=p[i]*cleft/w[i];			//？
		}
		return bound;
	}
	public static void main(String[] args) {
		double[] pp={0,45,25,25};
		double[] ww={0,16,15,15};
		double cc=30;
		System.out.println("最优解为:"+knapsack(pp, ww, cc));
	}
}
}