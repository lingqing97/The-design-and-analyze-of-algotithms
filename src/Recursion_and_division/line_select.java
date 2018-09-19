//线性时间选择改进版:
//算法思想:
//将N个输入元素划分为n/5组，找出每组的中位数；
//将这些中位数中的中位数x找出
//根据x对数组进行划分

package Recursion_and_division;

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
//	for(int i=p;i<=q-1;i++)		//长度为N的数组共需要冒n-1次“泡”
//		for(int j=p;j<q-i;j++)	//每冒完一次泡都有一个最大值“浮”到上面，所以需要-i操作
//		{
//			if(less(a[j+1],a[j]))
//				exchange(a,j+1,j);
//		}
 //   记录一次遍历中是否有元素的交换     
    boolean exchang;    
    for(int i=p; i<=q-1;i++)    
    {    
       exchang = false ;    
       for(int j=i+1; j<=q; j++)    
       {    
           if(a[j]<a[j-1])    
           {    
               //Swap(a[j],a[j-1]);   
        	   exchange(a,j,j-1);
               exchang = true;    
           }     
       }     
       //如果这次遍历没有元素的交换,那么排序结束     
       if(false == exchang)    
       {  
            break ;    
       }  
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
	if(q-p<75)	//若数组大小小于75时使用简单的冒泡排序找出所需要的第k大的值
	{
		bubblesort(a,p,q);
		return a[p+k-1];
	}
	for(int i=0;i<=(q-p-4)/5;i++)  //数组长度为(q-p+1),最大子数组序号为(q-p+1)/5-1=(q-p-4)
	{
		bubblesort(a,p+i*5,p+i*5+4);
		exchange(a,p+i*5+2,p+i);	//将这些中位数都放置在数组的最左侧
	}
	int x=select(a,p,p+(q-p-4)/5,(q-p-4)/10);   //(q-p-4)/10为a[p,p+(q-p-4)/5]的中位数的索引，即中位数的中位数
	int i=partition(a,p,q,x);
	int j=i-p+1;
	if(k<=j) return select(a,p,i,k);
	else return select(a,i+1,q,k-j);
}
public static void main(String[] args) {
	Random random=new Random();
	int[] a=new int[100];
	for(int i=0;i<100;i++)
		a[i]=random.nextInt(100);
//	bubblesort(a,0,a.length-1);
	Scanner sc=new Scanner(System.in);
	System.out.print("please input a index to test:\n");
	int temp=sc.nextInt();
	System.out.print(select(a,0,a.length-1,temp));
	System.out.println();
	for(int i=0;i<100;i++)
	{
		System.out.print(a[i]+" ");
	}
}
}
