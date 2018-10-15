//����װ��:��һ����װ��Ҫװ��һ��������Ϊc���ִ������м�װ��i������Ϊwi,����װ������Ҫ��ȷ����װ������������Ƶ�����£������ܶ�ļ�װ��װ���ִ���
//˼·:
//   ����ǰ״̬����������ļ�װ��װ���ϴ�
package greedy_algorithm;

import greedy_algorithm.bag_qustion.Element;

public class best_load {
public static class Element
{
	float w;
	int i;
	public Element(float ww,int ii) {
		w=ww;
		i=ii;
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
	public static float loading(float c,float[] w,int[] x)
	{
		int n=w.length;
		Element[] d=new Element[n];
		for(int i=0;i<n;i++)
			d[i]=new Element(w[i], i);
		MergeSort.sort(d);
		float opt=0;
		for(int i=0;i<n;i++) x[i]=0;
		for(int i=0;i<n&&d[i].w<=c;i++)
		{
			x[d[i].i]=1;
			opt+=d[i].w;
			c-=d[i].w;
		}
		return opt;
	}
	public static void main(String[] args) {
		float c=120;
		float[] w={40,30,60,10,20};
		int[] x=new int[w.length];
		System.out.println(loading(c, w, x));
		for(int i=0;i<x.length;i++)
			if(x[i]==1)
				System.out.println("ѡ������Ϊ"+w[i]+"��"+i+"����Ʒ");
	}
}
}
