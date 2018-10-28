//�����������:����n����ҵ{1,2,...n},��m̨��ͬ�Ļ������мӹ�����.��ҵi����ʱ��Ϊti,��Լ��,ÿ����ҵ�������κ�һ̨�����ϼӹ�����,��δ�깤ǰ�������жϴ���.
//            �����������Ҫ�����һ����ҵ���ȷ�����ʹ������n����ҵ�ھ����̵ܶ�ʱ������m̨���������.
//
//˼·:
//		ʹ��̰���㷨���ڵ�ǰ����£�ѡ�����ʱ������Ļ�����������������̨����;
//      ��n<=mʱ��ֻҪ������i��[0,ti]ʱ������������ҵi����.
//      ��n>mʱ�����Ƚ�n����ҵ��������Ĵ���ʱ���С��������Ȼ������˳����ҵ��������еĴ����;
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
		if(low<high)//����������Ԫ�زŽ��й鲢����
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
	//���������С�ڵ��ڻ�������ֱ��Ϊÿ���������һ̨����
	if(n<=m)
	{
		for(int i=0;i<n;i++) sum+=a[i];
		System.out.println("Ϊÿ���������һ̨����");
		return sum;
	}
	JobNode[] jobs=new JobNode[n];
	//��ʼ����n������
	for(int i=0;i<n;i++)
	{
		jobs[i]=new JobNode(i+1,a[i]);
	}
	//����������ʱ���������
	MergeSort.sort(jobs);
	MachineNode[] machines=new MachineNode[m];
	MinHeap H=new MinHeap(m);
	//��m̨������ʼ������m̨�����Ž�С����
	for(int i=0;i<m;i++)
	{
		machines[i]=new MachineNode(i+1,0);
		H.put(machines[i]);
	}
	//�ȷ�������ʱ�䳤������,�����Ǵ�i=n��ʼ
	for(int i=n;i>=1;i--)
	{
		//��С����ѡ������ʱ������Ļ�������i������������
		MachineNode temp_machine=(MachineNode)H.removeMin();
		System.out.println("������"+temp_machine.id+"��"+temp_machine.avail+"��"+(temp_machine.avail+jobs[i-1].time)+"��ʱ������"+jobs[i-1].id);
		temp_machine.avail+=jobs[i-1].time;
		sum=temp_machine.avail;
		//���������Ҫ����̨��������ѹ��С����
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
