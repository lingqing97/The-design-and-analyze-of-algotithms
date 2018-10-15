//��������:��0-1�������ƣ�����ͬ������ѡ����Ʒiװ�뱳��ʱ������ѡ����Ʒi��һ���֣�����һ��Ҫȫ��װ�뱳��,1<=i<=n;
//����˼·:
//      ���ȼ���ÿ����Ʒ��λ�����ļ�ֵvi/wi,��Ȼ����̰��ѡ����ԣ��������ܶ�ĵ�λ������ֵ��ߵ���Ʒװ�뱳��������������Ʒȫ��װ�뱳���󣬱����ڵ���Ʒ������
//  δ����C,��ѡ��λ������ֵ�θߵ���Ʒ�������ܶ��װ�뱳��.
package greedy_algorithm;

import edu.princeton.cs.algs4.Merge;
import Recursion_and_division.mergeSort;
import java.lang.Integer;
import java.util.Random;
public class bag_qustion {
	
	public static class Element{
		public float w;
		public float v;
		public int i;
		public Element(float w,float v,int i) {
			this.w=w;
			this.v=v;
			this.i=i;
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
			if(low<high)//����������Ԫ�زŽ��й鲢����
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
				else if(aux[j].w<=aux[k].w) a[i]=aux[j++];
				else a[i]=aux[k++];
			}
		}
	}
	public static float knapsack(float c,float[] w,float[] v,float[] x)
	{
		int n=v.length;
		Element[] d=new Element[n];
		for(int i=0;i<n;i++)
			d[i]=new Element(w[i],v[i],i);
		MergeSort.sort(d);
		int i;
		float opt=0;
		for(i=0;i<n;i++) x[i]=0;
		for(i=0;i<n;i++)
		{
			if(d[i].w>c) break;
			x[d[i].i]=1;
			opt+=d[i].v;
			c-=d[i].w;
		}
		if(i<n)
		{
			x[d[i].i]=c/d[i].w;
			opt+=x[d[i].i]*d[i].v;
		}
		return opt;
	}
	public static void main(String[] args) {
		float c=50;
		float[] w={30,20,10};
		float[] v={120,100,60};
		float[] x=new float[w.length];
		knapsack(c, w, v, x);
		for(int i=0;i<x.length;i++)
			System.out.println(x[i]+" ");
	}
}
