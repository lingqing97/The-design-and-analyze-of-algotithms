//线性时间选择改进版:
//算法思想:
//将N个输入元素划分为n/5组，找出每组的中位数；
//将这些中位数中的中位数x找出
//根据x对数组进行划分

package Recursion_and_division;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class line_select {
private static void exchange(int[] a,int x,int y)
{
	int temp=a[x];
	a[x]=a[y];
	a[y]=temp;
}
private static boolean less(int a,int b)
{
	return a<b;
}
//冒泡排序
private static void bubblesort(int[] a,int p,int q)
{
	for(int i=p;i<=q-1;i++)		//长度为N的数组共需要冒n-1次“泡”
		for(int j=p;j<q-i;j++)	//每冒完一次泡都有一个最大值“浮”到上面，所以需要-i操作
		{
			if(less(a[j+1],a[j]))
				exchange(a,j+1,j);
		}
}
private static int partition(int[]a,int p,int q,int x)
{
	int i=p-1;
	int j=q+1;
	while(true)
	{
		while(less(a[++i],x)&&i<q);//{if(i==q) break;}
		while(less(x,a[--j]));//{if(j==p) break;}
		if(i>=j) break;
		exchange(a,i,j);
	}
	return j;
}
private static int select(int[] a,int p,int q,int k)
{
	if(q-p<5)	//若数组大小小于5时使用简单的冒泡排序找出所需要的第k大的值
	{
		bubblesort(a,p,q);
		return a[p+k-1];
	}
	for(int i=0;i<=(q-p-4)/5;i++)  //数组长度为(q-p+1),最大子数组序号为(q-p+1)/5-1=(q-p-4)/5
	{
		bubblesort(a,p+i*5,p+i*5+4);
		exchange(a,p+i,p+i*5+2);	//将这些中位数都放置在数组的最左侧
	}
	int x=select(a,p,p+(q-p-4)/5,(q-p+6)/10);   //(q-p+6)/10为a[p,p+(q-p-4)/5]的中位数的索引，即中位数的中位数
	int i=partition(a,p,q,x);
	int j=i-p+1;
	if(k<=j) return select(a,p,i,k);
	else return select(a,i+1,q,k-j);
}
public static void main(String[] args) {
	Random random=new Random(1000);
	int[] a=new int[100];
	int[] b=new int[10];
	for(int i=0;i<100;i++)
		a[i]=random.nextInt(100);
	for(int i=0;i<10;i++)
		b[i]=random.nextInt(100);
	long starttime=System.nanoTime();
	for(int i=0;i<10;i++)
		select(a,0,a.length-1,b[i]);
	long endtime=System.nanoTime();
	System.out.print("在100个数据中查找10个随机大的数的平均时间:\n");
	System.out.print((endtime-starttime)/10+"ns");
	
	random=new Random(100);
	a=new int[1000];
	for(int i=0;i<1000;i++)
		a[i]=random.nextInt(1000);
	for(int i=0;i<10;i++)
		b[i]=random.nextInt(1000);
	starttime=System.nanoTime();
	for(int i=0;i<10;i++)
		select(a,0,a.length-1,b[i]);
	endtime=System.nanoTime();
	System.out.print("\n在1000个数据中查找10个随机大的数的平均时间:\n");
	System.out.print((endtime-starttime)/10+"ns");
	
	random=new Random(10000);
	a=new int[10000];
	for(int i=0;i<10000;i++)
		a[i]=random.nextInt(10000);
	for(int i=0;i<10;i++)
		b[i]=random.nextInt(10000);
	starttime=System.nanoTime();
	for(int i=0;i<10;i++)
		select(a,0,a.length-1,b[i]);
	endtime=System.nanoTime();
	System.out.print("\n在10000个数据中查找10个随机大的数的平均时间:\n");
	System.out.print((endtime-starttime)/10+"ns");
	
	random=new Random(100000);
	a=new int[100000];
	for(int i=0;i<100000;i++)
		a[i]=random.nextInt(100000);
	for(int i=0;i<10;i++)
		b[i]=random.nextInt(100000);
	starttime=System.nanoTime();
	for(int i=0;i<10;i++)
		select(a,0,a.length-1,b[i]);
	endtime=System.nanoTime();
	System.out.print("\n在100000个数据中查找10个随机大的数的平均时间:\n");
	System.out.print((endtime-starttime)/10+"ns");
}
}
