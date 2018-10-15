//���ֲ��ҷ����ڸ������ź����n��Ԫ��a[0:n-1],����Ҫ����n��Ԫ�����ҳ�һ�ض�Ԫ��x;
package Recursion_and_division;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class binarysearch {
public static int binarysearch(int[] a,int x,int n)
{
	int low=0;
	int high=n-1;
	while(low<=high)
	{
		int mid=(low+high)/2;
		if(a[mid]>x) high=mid-1;
		else if(a[mid]<x) low=mid+1;
		else return mid;
	}
	return -1;
}
public static void main(String[] args) {
	Random random=new Random(1000);
	int[] a=new int[100];
	int[] b=new int[10];
	for(int i=0;i<100;i++)
		a[i]=random.nextInt(100);
	for(int i=0;i<10;i++)
		b[i]=random.nextInt(100);
	Arrays.sort(a);
	long starttime=System.nanoTime();
	for(int i=0;i<10;i++)
		binarysearch(a,b[i],a.length);
	long endtime=System.nanoTime();
	System.out.print("��100�������в���10���������ƽ��ʱ��:\n");
	System.out.print((endtime-starttime)/10+"ns");
	
	random=new Random(100);
	a=new int[1000];
	for(int i=0;i<1000;i++)
		a[i]=random.nextInt(1000);
	for(int i=0;i<10;i++)
		b[i]=random.nextInt(1000);
	Arrays.sort(a);
	starttime=System.nanoTime();
	for(int i=0;i<10;i++)
		binarysearch(a,b[i],a.length);
	endtime=System.nanoTime();
	System.out.print("\n��1000�������в���10���������ƽ��ʱ��:\n");
	System.out.print((endtime-starttime)/10+"ns");
	
	random=new Random(10000);
	a=new int[10000];
	for(int i=0;i<10000;i++)
		a[i]=random.nextInt(10000);
	for(int i=0;i<10;i++)
		b[i]=random.nextInt(10000);
	Arrays.sort(a);
	starttime=System.nanoTime();
	for(int i=0;i<10;i++)
		binarysearch(a,b[i],a.length);
	endtime=System.nanoTime();
	System.out.print("\n��10000�������в���10���������ƽ��ʱ��:\n");
	System.out.print((endtime-starttime)/10+"ns");
	
	random=new Random(100000);
	a=new int[100000];
	for(int i=0;i<100000;i++)
		a[i]=random.nextInt(100000);
	for(int i=0;i<10;i++)
		b[i]=random.nextInt(100000);
	Arrays.sort(a);
	starttime=System.nanoTime();
	for(int i=0;i<10;i++)
		binarysearch(a,b[i],a.length);
	endtime=System.nanoTime();
	System.out.print("\n��100000�������в���10���������ƽ��ʱ��\n");
	System.out.print((endtime-starttime)/10+"ns");
}
}
