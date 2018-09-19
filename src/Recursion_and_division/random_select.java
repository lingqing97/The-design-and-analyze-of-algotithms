//元素选择问题:给定线性序集中的n个元素和一个整数k，1<=k<=n,要求找出这n个元素中第k小的元素;

package Recursion_and_division;

import java.util.Random;
import java.util.Scanner;

public class random_select {
private static boolean less(int x,int y)
{
	return x<y;
}
private static void exchange(int[] a,int x,int y)
{
	int temp=a[x];
	a[x]=a[y];
	a[y]=temp;
}
private static int partition(int[] a,int p,int q)
{
	int i=p;
	int j=q+1;
	int temp=a[p];
	while(true)
	{
		while(less(a[++i],temp)){if(i==q) break;}
		while(less(temp,a[--j])){if(j==p) break;}
		if(i>=j)break;
		exchange(a,i,j);
	}
	exchange(a,p,j);
	return j;
}
private static int randompartition(int[] a,int p,int q)
{
	Random random=new Random(q);
	int index=random.nextInt(q)%(q-p+1)+p;
	exchange(a,index,p);
	return partition(a,p,q);
}
private static int randomselect(int[] a,int p,int q,int k)
{
	if(p==q) return a[p];
	int i=randompartition(a,p,q);
	int l=i-p+1;
	if(k<=l) return randomselect(a,p,i,k);
	else return randomselect(a,i+1,q,k-l);
}
public static void main(String[] args) {
	Random random=new Random(1000);
	int[] a=new int[20];
	for(int i=0;i<20;i++)
		a[i]=random.nextInt(100);
	Scanner sc=new Scanner(System.in);
	int testcount=0;
	while(testcount<20)
	{
		System.out.print("please input a index:");
		int temp=sc.nextInt();
		System.out.print(randomselect(a,0,a.length-1,temp));
		System.out.println();
		for(int i=0;i<20;i++)
			System.out.print(a[i]+" ");
		testcount++;
		System.out.println();
	}
}
}
