//���ֲ��ҷ����ڸ������ź����n��Ԫ��a[0:n-1],����Ҫ����n��Ԫ�����ҳ�һ�ض�Ԫ��x;
package Recursion_and_division;

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
	int[] a={1,2,3,4,5,6,7,8,9,10};
	System.out.print("please input the num you want to find:\n");
	Scanner sc=new Scanner(System.in);
	int temp=sc.nextInt();
	if(binarysearch(a, temp,10)!=(-1))
		System.out.print("the "+temp+"'s position is "+(binarysearch(a, temp,10)+1));
	else
		System.out.print("not found the "+temp+"\n");
}
}
