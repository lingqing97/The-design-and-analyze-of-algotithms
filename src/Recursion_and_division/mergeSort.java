//�鲢����
//ͨ��Random������ȡ�������
//
//ʹ�÷������£�
//(01) ����Random���������ַ������Դ���Random�������£�
//
//Random random = new Random();//Ĭ�Ϲ��췽��
//Random random = new Random(1000);//ָ����������
//(02) ͨ��Random�����ȡ�������Random֧�ֵ����ֵ���Ͱ�����boolean, byte, int, long, float, double��
//���磬��ȡ[0, 100)֮���int�������������£�
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
	if(low<high)//����������Ԫ�زŽ��й鲢����
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
