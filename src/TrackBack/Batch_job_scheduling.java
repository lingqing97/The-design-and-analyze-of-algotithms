//��������ҵ�������⣺
//����:
//n����ҵ{1, ��, n}
//��̨������M1��M2��
//��ҵ i ��M1��M2�ϵĴ���ʱ��ֱ�Ϊ a[i] �� b[i] 
//ÿ����ҵ��������M1��������M2����
//���:
//��ҵ���ȷ���ʹ���ܵȴ�ʱ����С
//��ҵ i��M1��M2�ϵ����ʱ��ֱ�Ϊ A[i] �� B[i] 
//�ܵȴ�ʱ�������M1�ϵ�ʱ�������M2�ϵ�ʱ��w
//˼·:
//Ҫ������������ѡ����С�����ʱ�䣬���Խ�ռ���һ��������.
package TrackBack;

import com.sun.org.apache.bcel.internal.generic.FLOAD;

public class Batch_job_scheduling {
public static class FlowShop{
	static int n;	//��ҵ��
	static int f1;	//����1��ɴ���ʱ��
	static int f;	//���ʱ���
	static int bestf;	//��ǰ����ֵ
	static int[][] m;	//����ҵ����Ĵ���ʱ��
	static int[] x;		//��ǰ��ҵ����
	static int[] bestx;		//��ǰ������ҵ����
	static int[] f2;	//����2��ɴ���ʱ��
	public static int best_schedule(int[][] mm,int nn,int [] xx)
	{
		n=nn;
		f1=0;
		f=0;
		bestf=Integer.MAX_VALUE;
		m=mm;
		x=new int[n+1];
		//��ʼ��Ϊ1....n���������˳��
		for(int i=1;i<=n;i++)
			x[i]=i;
		bestx=xx;
		f2=new int[n+1];
		backtrack(1);
		return bestf;
	}
	private static void backtrack(int i)
	{
		//����Ҷ�ӽڵ�
		if(i>n)
		{
			for(int j=1;j<=n;j++)
				bestx[j]=x[j];
			bestf=f;
		}
		else
		{
			//ָ��j��¼��ǰ��������ҵ����ָ��i��¼ȷ������ҵ����
			//����1.....n
			for(int j=i;j<=n;j++)
			{
				//��j��������б���
				f1+=m[x[j]][1];		//j����ҵ��m1�����ϵ����ʱ��
				//i-1������Ϊȷ������������
				f2[i]=(f2[i-1]>f1?f2[i-1]:f1)+m[x[j]][2];	//�ж�ǰһ����ҵ��m2�������Ƿ����
				f+=f2[i];
				//�����������
				if(f<bestf)
				{
					//��j��������ʽ����i���������У�֮����ȷ��i+1������ĺ�ѡ��
					swap(x,i,j);
					backtrack(i+1);
					swap(x,i,j);
				}
				f1-=m[x[j]][1];
				f-=f2[i];
			}
		}
	}
	private static void swap(int[] x,int i,int j)
	{
		int temp=x[i];
		x[i]=x[j];
		x[j]=temp;
	}
	public static void main(String[] args) {
		int[][] mm={{0,0,0},{1,2,1},{2,3,1},{3,2,3}};
		int n=3;
		int[] xx=new int[4];
		System.out.println(best_schedule(mm, n, xx));
		System.out.println("��ҵ����˳����:");
		for(int i=1;i<4;i++)
			System.out.print(xx[i]+" ");
	}
}
}
