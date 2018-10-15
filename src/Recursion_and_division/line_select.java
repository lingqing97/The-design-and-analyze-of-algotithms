//����ʱ��ѡ��Ľ���:
//�㷨˼��:
//��N������Ԫ�ػ���Ϊn/5�飬�ҳ�ÿ�����λ����
//����Щ��λ���е���λ��x�ҳ�
//����x��������л���

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
//ð������
private static void bubblesort(int[] a,int p,int q)
{
	for(int i=p;i<=q-1;i++)		//����ΪN�����鹲��Ҫðn-1�Ρ��ݡ�
		for(int j=p;j<q-i;j++)	//ÿð��һ���ݶ���һ�����ֵ�����������棬������Ҫ-i����
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
	if(q-p<5)	//�������СС��5ʱʹ�ü򵥵�ð�������ҳ�����Ҫ�ĵ�k���ֵ
	{
		bubblesort(a,p,q);
		return a[p+k-1];
	}
	for(int i=0;i<=(q-p-4)/5;i++)  //���鳤��Ϊ(q-p+1),������������Ϊ(q-p+1)/5-1=(q-p-4)/5
	{
		bubblesort(a,p+i*5,p+i*5+4);
		exchange(a,p+i,p+i*5+2);	//����Щ��λ��������������������
	}
	int x=select(a,p,p+(q-p-4)/5,(q-p+6)/10);   //(q-p+6)/10Ϊa[p,p+(q-p-4)/5]����λ��������������λ������λ��
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
	System.out.print("��100�������в���10������������ƽ��ʱ��:\n");
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
	System.out.print("\n��1000�������в���10������������ƽ��ʱ��:\n");
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
	System.out.print("\n��10000�������в���10������������ƽ��ʱ��:\n");
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
	System.out.print("\n��100000�������в���10������������ƽ��ʱ��:\n");
	System.out.print((endtime-starttime)/10+"ns");
}
}
