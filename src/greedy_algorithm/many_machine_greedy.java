//多机调度问题:设有n个作业{1,2,...n},有m台相同的机器进行加工处理.作业i所需时间为ti,现约定,每个作业均可在任何一台机器上加工处理,但未完工前不允许中断处理.
//            多机调度问题要求给出一种作业调度方案，使所给的n个作业在尽可能短的时间内在m台机器上完成.
//
//思路:
//		使用贪心算法，在当前情况下，选择结束时间最早的机器，将任务分配给这台机器;
//      当n<=m时，只要将机器i的[0,ti]时间区间分配给作业i即可.
//      当n>m时，首先将n个作业依其所需的处理时间从小到大排序，然后依次顺序将作业分配给空闲的处理机;
package greedy_algorithm;

import greedy_algorithm.best_load.Element;
import greedy_algorithm.best_load.Element.MergeSort;

public class many_machine_greedy {
static class JobNode implements Comparable
{
	int id;
	int time;
	public JobNode(int i,int t) {
		id=i;
		time=t;
	}
	public int compareTo(Object x)
	{
		int xt=((JobNode)x).time;
		if(time<xt) return -1;
		if(time==xt) return 0;
		return 1;
	}
}
static class MachineNode implements Comparable
{
	int id;
	int avail;
	public MachineNode(int i,int a) {
		id=i;
		avail=a;
	}
	public int compareTo(Object x)
	{
		int xa=((MachineNode)x).avail;
		if(avail<xa) return -1;
		if(avail==xa) return 0;
		return 1;
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
		if(N>0&&N==pq.length/4) resize(pq.length/2);
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
public static int greedy(int[] a,int m)
{
	int n=a.length;
	int sum=0;
	//如果任务数小于等于机器数，直接为每个任务分配一台机器
	if(n<=m)
	{
		for(int i=0;i<n;i++) sum+=a[i];
		System.out.println("为每个任务分配一台机器");
		return sum;
	}
	JobNode[] jobs=new JobNode[n];
	//初始化这n个任务
	for(int i=0;i<n;i++)
	{
		jobs[i]=new JobNode(i+1,a[i]);
	}
	//将任务按运行时间进行排序
	MergeSort.sort(jobs);
	MachineNode[] machines=new MachineNode[m];
	MinHeap H=new MinHeap(m);
	//将m台机器初始化及将m台机器放进小堆中
	for(int i=0;i<m;i++)
	{
		machines[i]=new MachineNode(i+1,0);
		H.put(machines[i]);
	}
	//先分配运行时间长的任务,所以是从i=n开始
	for(int i=n;i>=1;i--)
	{
		//从小堆中选出结束时间最早的机器，将i号任务分配给它
		MachineNode temp_machine=(MachineNode)H.removeMin();
		System.out.println("将机器"+temp_machine.id+"从"+temp_machine.avail+"到"+(temp_machine.avail+jobs[i-1].time)+"的时间分配给"+jobs[i-1].id);
		temp_machine.avail+=jobs[i-1].time;
		sum=temp_machine.avail;
		//分配完后，需要将这台机器重新压入小堆中
		H.put(temp_machine);
	}
	return sum;
}
public static void main(String[] args) {
	int[] a={2,14,4,16,6,5,3};
	int m=3;
	greedy(a, m);
}
}
