//����ʱ��ѡ��Ľ���:
//�㷨˼��:
//��N������Ԫ�ػ���Ϊn/5�飬�ҳ�ÿ�����λ����
//����Щ��λ���е���λ��x�ҳ�
//����x��������л���

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
//ð������
private static void bubblesort(int[] a,int p,int q)
{
//	for(int i=p;i<=q-1;i++)		//����ΪN�����鹲��Ҫðn-1�Ρ��ݡ�
//		for(int j=p;j<q-i;j++)	//ÿð��һ���ݶ���һ�����ֵ�����������棬������Ҫ-i����
//		{
//			if(less(a[j+1],a[j]))
//				exchange(a,j+1,j);
//		}
 //   ��¼һ�α������Ƿ���Ԫ�صĽ���     
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
       //�����α���û��Ԫ�صĽ���,��ô�������     
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
	if(q-p<75)	//�������СС��75ʱʹ�ü򵥵�ð�������ҳ�����Ҫ�ĵ�k���ֵ
	{
		bubblesort(a,p,q);
		return a[p+k-1];
	}
	for(int i=0;i<=(q-p-4)/5;i++)  //���鳤��Ϊ(q-p+1),������������Ϊ(q-p+1)/5-1=(q-p-4)
	{
		bubblesort(a,p+i*5,p+i*5+4);
		exchange(a,p+i*5+2,p+i);	//����Щ��λ��������������������
	}
	int x=select(a,p,p+(q-p-4)/5,(q-p-4)/10);   //(q-p-4)/10Ϊa[p,p+(q-p-4)/5]����λ��������������λ������λ��
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
