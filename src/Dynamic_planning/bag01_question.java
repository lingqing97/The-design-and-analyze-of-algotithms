//0-1��������:����n����Ʒ��һ��������Ʒi��������wi,���ֵΪvi������������ΪC,�ʣ�Ӧ�����ѡ��װ�뱳������Ʒ��ʹ��װ�뱳������Ʒ���ܼ�ֵ���
//˼·:
//��m(i,j)�Ǳ�������Ϊj����ѡ����ƷΪi,i+1,...nʱ0-1�������������ֵ�����Խ������¼���m(i,j)�ĵݹ�ʽ:
//		 {max(m(i+1,j),m(i+1,j-wi)+vi)    j>=wi     ����ʣ���������������i����Ʒ������
//m(i,j)={
//		 {m(i+1,j)     0<=j<wi		��ǰ����ʣ����������������i����Ʒ
//		 {vn        j>wn
//m(n,j)={
//		 {0         0<=j<wn
package Dynamic_planning;

public class bag01_question {
public static void knapsack(int[] v,int[] w,int c,int[][] m)
{
	int n=v.length-1;
	int jMax=Math.min(w[n]-1, c);	
	for(int j=0;j<=jMax;j++)
	{
		m[n][j]=0;		//��wn���ڱ���ʣ������jʱ���޷���װ��
	}
	for(int j=w[n];j<=c;j++)
	{
		m[n][j]=v[n];		//��wnС�ڱ���ʣ������jʱ������װ���n����Ʒ
	}
	for(int i=n-1;i>1;i--)
	{
		jMax=Math.min(w[i]-1, c);
		for(int j=0;j<=jMax;j++)
			m[i][j]=m[i+1][j];	//��i����Ʒ��wi���ڱ���ʣ������jʱ���޷�װ��i����Ʒ
		for(int j=w[i];j<=c;j++)
			m[i][j]=Math.max(m[i+1][j], m[i+1][j-w[i]]+v[i]);	//��i����Ʒ��wiС�ڱ���ʣ������jʱ������װ��Ҳ���Բ�װ�ˣ�����ѡ�����ŵ�;
	}
	m[1][c]=m[2][c];
	if(c>=w[1])
		m[1][c]=Math.max(m[1][c], m[2][c-w[1]]+v[1]);	//��1����Ʒ��������ѡ��
}
public static void traceback(int[][] m,int[] w,int c,int[] x)
{
	int n=w.length-1;
	for(int i=1;i<n;i++)	//ѭ�������ж��Ƿ�ѡ��i����Ʒ
	{
		if(m[i][c]==m[i+1][c]) x[i]=0;
		else
		{
			x[i]=1;
			c-=w[i];	
		}
	}
	x[n]=(m[n][c]>0)?1:0;
}
public static void main(String[] args) {
	int[] v={0,6,3,5,4,6};	//�����0����Ʒ�����ͽ���ֵ��Ϊ0�������㷨�����
	int[] w={0,2,2,6,5,4};
	int c=10;
	int n=5;
	int[][] m=new int[n+1][c+1];
	int[] x=new int[n+1];
	knapsack(v, w, c, m);
	traceback(m, w, c, x);
	System.out.println("����ֵΪ:"+m[1][10]);
	for(int i=1;i<x.length;i++)
		if(x[i]==1)
			System.out.println("ѡ����"+i+"����Ʒ");
}
}
