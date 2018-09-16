//归并排序
//通过Random类来获取随机数。
//
//使用方法如下：
//(01) 创建Random对象。有两种方法可以创建Random对象，如下：
//
//Random random = new Random();//默认构造方法
//Random random = new Random(1000);//指定种子数字
//(02) 通过Random对象获取随机数。Random支持的随机值类型包括：boolean, byte, int, long, float, double。
//比如，获取[0, 100)之间的int整数。方法如下：
//
//int i2 = random.nextInt(100);


package Recursion_and_division;
import java.lang.Integer;
import java.util.Random;
public class mergeSort {
private static int[] aux;
private static void sort(int[] a)
{
	aux=new int[a.length];
	mergeSort(a,0,a.length-1);
}
private static void mergeSort(int[] a,int low,int high)
{
	if(low<high)//至少有两个元素才进行归并排序
	{
		int mid=(low+high)/2;
		mergeSort(a,low,mid);
		mergeSort(a,mid+1,high);
		merge(a,low,mid,high);
	}
}
private static void merge(int[] a,int low,int mid,int high)
{
	int j=low;
	int k=mid+1;
	for(int i=low;i<=high;i++)
		aux[i]=a[i];
	for(int i=low;i<=high;i++)
	{
		if(j>mid) a[i]=aux[k++];
		else if(k>high) a[i]=aux[j++];
		else if(aux[j]<=aux[k]) a[i]=aux[j++];
		else a[i]=aux[k++];
	}
}
public static void main(String[] args) {
	Random rand=new Random(100);
	int[] a=new int[20];
	for(int i=0;i<20;i++)
		a[i]=rand.nextInt(100);
	sort(a);
	for(int i=0;i<20;i++)
		System.out.print(a[i]+" ");
}
}
