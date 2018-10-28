//批处理作业调度问题：
//输入:
//n个作业{1, …, n}
//两台机器（M1和M2）
//作业 i 在M1和M2上的处理时间分别为 a[i] 和 b[i] 
//每个作业必须先由M1处理，再由M2处理
//输出:
//作业调度方案使得总等待时间最小
//作业 i在M1和M2上的完成时间分别为 A[i] 和 B[i] 
//总等待时间等于在M1上的时间加上在M2上的时间w
//思路:
//要从所有排列中选出最小的完成时间，所以解空间是一棵排列树.
package TrackBack;

import com.sun.org.apache.bcel.internal.generic.FLOAD;

public class Batch_job_scheduling {
public static class FlowShop{
	static int n;	//作业数
	static int f1;	//机器1完成处理时间
	static int f;	//完成时间和
	static int bestf;	//当前最优值
	static int[][] m;	//各作业所需的处理时间
	static int[] x;		//当前作业调度
	static int[] bestx;		//当前最优作业调度
	static int[] f2;	//机器2完成处理时间
	public static int best_schedule(int[][] mm,int nn,int [] xx)
	{
		n=nn;
		f1=0;
		f=0;
		bestf=Integer.MAX_VALUE;
		m=mm;
		x=new int[n+1];
		//初始化为1....n的任务调度顺序
		for(int i=1;i<=n;i++)
			x[i]=i;
		bestx=xx;
		f2=new int[n+1];
		backtrack(1);
		return bestf;
	}
	private static void backtrack(int i)
	{
		//到达叶子节点
		if(i>n)
		{
			for(int j=1;j<=n;j++)
				bestx[j]=x[j];
			bestf=f;
		}
		else
		{
			//指针j记录当前待定的作业任务，指针i记录确定的作业任务
			//遍历1.....n
			for(int j=i;j<=n;j++)
			{
				//对j号任务进行遍历
				f1+=m[x[j]][1];		//j号作业在m1机器上的完成时间
				//i-1号任务为确定的任务序列
				f2[i]=(f2[i-1]>f1?f2[i-1]:f1)+m[x[j]][2];	//判断前一个作业在m2机器上是否完成
				f+=f2[i];
				//如果满足条件
				if(f<bestf)
				{
					//将j号任务正式调到i号任务序列，之后再确定i+1号任务的候选者
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
		System.out.println("作业调度顺序是:");
		for(int i=1;i<4;i++)
			System.out.print(xx[i]+" ");
	}
}
}
