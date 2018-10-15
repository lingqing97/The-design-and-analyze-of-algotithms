//���������:����n����ļ���E={1,2,3,4..n},���У�ÿ�����Ҫ��ʹ��ͬһ��Դ�����ݽ��᳡�ȣ�����ͬһʱ����ֻ��һ�����ʹ����һ��Դ��ÿ���i����
//			  һ��Ҫ��ʹ�ø���Դ����ʼʱ��si��һ������ʱ��fi,��si<fi.���ѡ���˻i�������ڰ뿪ʱ������[si,fi)��ռ����Դ��������[si,fi)������[sj,fj)
//			 ���ཻ����i��j�����ݵġ�������������Ҫ�������Ļ������ѡ���������ݻ�Ӽ��ϡ�
//˼·:
//   �i��j���ݵĳ�ֱ�Ҫ������s[i]>=f[j]
package greedy_algorithm;

public class activities_arrange {
public static int greedySelector(int[] s,int[] f,boolean[] a)
{
	int n=s.length-1;
	a[1]=true;
	int j=1;
	int count=1;
	for(int i=2;i<=n;i++)
	{
		if(s[i]>=f[j])
		{
			a[i]=true;
			j=i;
			count++;
		}
		else a[i]=false;
	}
	return count;
}
public static void main(String[] args) {
	int[] s={0,1,3,0,5,3,5,6,8,8,2,12};
	int[] f={4,5,6,7,8,9,10,11,12,13,14};
	boolean[] a=new boolean[s.length];
	System.out.println("�������ݻ�Ӽ�����:"+greedySelector(s, f, a));
	System.out.println("�ɰ��ŵĻ��:");
	for(int i=1;i<a.length;i++)
		if(a[i])
		System.out.println(i+" ");
}
}
