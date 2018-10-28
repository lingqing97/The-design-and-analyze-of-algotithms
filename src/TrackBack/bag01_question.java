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
	private static class Element implements Comparable
	{
		int id;		//��Ʒ���
		double d;	//��Ʒ��λ�����ļ�ֵ
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
	
	static double c;	//��������
	static int n;	//��Ʒ��
	static double[] w;	//��Ʒ��������
	static double[] p;	//��Ʒ��ֵ����
	static double cw;	//��ǰ����
	static double cp;	//��ǰ��ֵ
	static double bestp;	//��ǰ���ż�ֵ
	
	public static double knapsack(double[] pp,double[] ww,double cc)
	{
		c=cc;
		n=pp.length-1;
		cw=0.0;
		cp=0.0;
		bestp=0.0;
		//qΪ��λ������ֵ����
		Element[] q=new Element[n];
		
		//��ʼ��q[0:n-1]
		for(int i=1;i<=n;i++)
			q[i-1]=new Element(i,pp[i]/ww[i]);
		
		//������Ʒ����λ������ֵ�Ӵ�С����
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
			//����Ҷ�ӽڵ�
			bestp=cp;
			return;
		}
		//��������
		//�������δװ��������������
		if(cw+w[i]<=c)
		{
			cw+=w[i];
			cp+=p[i];
			backtrack(i+1);
			cw-=w[i];
			cp-=p[i];
		}
		//�����Ͻ纯���ж��Ƿ���Ҫ����������,����bound(i+1)Ϊ������������ܹ��õ��ļ�ֵ
		//������������ֵ����bestp,˵���������ı����б�Ҫ����
		if(bound(i+1)>bestp)
		{
			backtrack(i+1);
		}
	}
	//�����Ͻ纯��
	private static double bound(int i)
	{
		double cleft=c-cw;		//����ʣ������
		double bound=cp;	//�����������ֵ��ʼ��Ϊ��ǰ��ֵ
		//����Ʒ��λ��ֵ�ݼ�˳��װ����Ʒ
		while(i<=n&&w[i]<=cleft)
		{
			cleft-=w[i];
			bound+=p[i];
			i++;
		}
		
		//װ������
		if(i<=n)
		{
			bound+=p[i]*cleft/w[i];			//��
		}
		return bound;
	}
	public static void main(String[] args) {
		double[] pp={0,45,25,25};
		double[] ww={0,16,15,15};
		double cc=30;
		System.out.println("���Ž�Ϊ:"+knapsack(pp, ww, cc));
	}
}
}