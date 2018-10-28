//n������
//����:
//n��n������
//n���ʺ�
//���:
//n���ʺ�ķ��÷���
//���������ʺ󶼲���ͬһ�С�ͬһ�л�ͬһб����
//��ռ�:(������)
//ÿ�����ҽ���һ���ʺ�
//��x[i]��ʾ��i�лʺ�λ�ڵڼ���
//�˻ʺ������Ϊ(i,x[i])
//����Ľ���x[1,��, n]������
//���������ʺ���ͬһ���ϣ�x[i]!=x[j]
//���������ʺ���ͬһб����:| i-j | != | x[i] - x[j] |
//
//sum��¼��ǰ���ҵ��Ŀ��з�����
package TrackBack;

public class n_queue_question {
static int n;	//�ʺ����
static int[] x;		//��ǰ��
static long sum;	//��ǰ���ҵ��Ŀ��з�����

public static long nQueen(int nn)
{
	n=nn;
	sum=0;
	x=new int [n+1];
	for(int i=0;i<=n;i++) x[i]=0;
	backtrack(1);
	return sum;
}
private static boolean constraint(int k)
{
	//����ǰ��,����K�еĻʺ��λ����ǰk-1�еĻʺ��λ�ý��бȶԣ��жϷ��õ�λ���Ƿ�Ϸ�
	for(int j=1;j<k;j++)
		if(((Math.abs(k-j)==Math.abs(x[j]-x[k]))||x[j]==x[k])) return false;
		return true;
}
private static void backtrack(int t)
{
	if(t>n) 
	{
		sum++;
		System.out.println("���з���"+sum+"Ϊ:");
		for(int i=1;i<=n;i++)
			System.out.println("("+i+","+x[i]+")");
	}
	else
	{
		for(int i=1;i<=n;i++)
		{
			x[t]=i;
			if(constraint(t)) backtrack(t+1);
		}
	}
}
public static void main(String[] args) {
	System.out.println("���з�������Ϊ:"+nQueen(4));
}
}
