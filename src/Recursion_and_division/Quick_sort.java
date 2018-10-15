//快速排序:快速排序的时间复杂度很受每次所取值的影响，所以需要使用随机方法产生随机数

package Recursion_and_division;

import java.util.Random;

public class Quick_sort {
private static void sort(int[] a,int p,int q)
{
	if(q>p)
	{
		int i=randompartition(a,p,q);
		sort(a,p,i-1);
		sort(a,i+1,q);
	}
}
private static int randompartition(int[]a,int p,int q)
{
	int i=p;
	int j=q+1;
	Random random=new Random(100);
	int index=random.nextInt(q)%(q-p+1)+p;   //int s = random.nextInt(max)%(max-min+1) + min;取max与min之间的随机值
	exchange(a,p,index);
	int temp=a[p];
	while(true)
	{
		while(less(a[++i],temp)&&i<q);
		while(less(temp,a[--j]));
		if(j<=i) break;
		exchange(a,i,j);
	}
	exchange(a,p,j);
	return j;
}
private static boolean less(int a,int b)
{
	return a<b;
}
private static void exchange(int[] a,int x,int y)
{
	int temp=a[x];
	a[x]=a[y];
	a[y]=temp;
}
public static void main(String[] args) {
	Random random=new Random(1000);
	int[] a=new int[100];
	for(int i=0;i<100;i++)
		a[i]=random.nextInt(1000);
	long starttime=System.nanoTime();
	sort(a,0,a.length-1);
	long endtime=System.nanoTime();
	System.out.print("快速排序100个数据所花时间:\n");
	System.out.print((endtime-starttime)+"ns");
	
	a=new int[1000];
	for(int i=0;i<1000;i++)
		a[i]=random.nextInt(1000);
	starttime=System.nanoTime();
	sort(a,0,a.length-1);
	endtime=System.nanoTime();
	System.out.print("\n快速排序1000个数据所花时间:\n");
	System.out.print((endtime-starttime)+"ns");
	
	a=new int[10000];
	for(int i=0;i<10000;i++)
		a[i]=random.nextInt(100000);
	starttime=System.nanoTime();
	sort(a,0,a.length-1);
	endtime=System.nanoTime();
	System.out.print("\n快速排序10000个数据所花时间:\n");
	System.out.print((endtime-starttime)+"ns");
	
	a=new int[100000];
	for(int i=0;i<100000;i++)
		a[i]=random.nextInt(100000);
	starttime=System.nanoTime();
	sort(a,0,a.length-1);
	endtime=System.nanoTime();
	System.out.print("\n快速排序100000个数据所花时间:\n");
	System.out.print((endtime-starttime)+"ns");
}
}
