//ͼ��m��ɫ����
//����:
//������ͨͼG
//m����ɫ
//���:
//������ɫ����
//�����������ڶ��㶼�в�ͬ��ɫ
//��ռ�:
//x[i]��ʾ����i����ɫ
//ÿ��x[i]��m�ֲ�ͬȡֵ
package TrackBack;

public class Graphic_color_question {
static int n;//ͼ�Ķ�����
static int m;//������ɫ��
static boolean[][] a={{},{},{},{},{},{}};
static int[] x;//��ǰ��
static long sum;//��ǰ���ҵ��Ŀ�m��ɫ������
public static long mColoring(int mm)
{
	m=mm;
	sum=0;
	backtrack(1);
	return sum;
}
private static void backtrack(int t)
{
	if(t>n)
	{
		sum++;
		for(int i=1;i<=n;i++)
		{
			System.out.println(x[i]+" ");
		}
		System.out.println();
	}
	else
	{
		for(int i=1;i<=m;i++)
		{
			x[t]=i;
			if(ok(t)) backtrack(t+1);
			x[t]=0;
		}
	}
}
private static boolean ok(int k)
{
	//�����ɫ�Ƿ����
	for(int j=1;j<=n;j++)
		//���j��ͼ�뵱ǰͼ(k��ͼ)���ڣ�������ͼ��ɫ��ͬ������false;
		if(a[k][j]&&(x[j]==x[k])) return false;
	return true;
}
public static void main(String[] args) {
	System.out.println(mColoring(3));
}
}
