//��Դ���·��:������Ȩ����ͼG=(V,E),����ÿ���ߵ�Ȩ�ǷǸ�ʵ�������⣬������V�е�һ�����㣬��ΪԴ������Ҫ�����Դ��������������������·��
//			  �ȡ������·�ĳ�����ָ·�ϸ���Ȩ֮�͡�
//Dijkstra�㷨:���ö��㼯��s�����ϵ���̰��ѡ��������������ϡ�һ���������ڼ���s���ҽ�����Դ���ö�������·��������֪����ʼʱ��s�н���Դ��
//			 ��u��G��ĳ�����㣬�Ѵ�Դ��u���м�ֻ����s�ж����·��Ϊ��Դ��u������·����������ֵdist��¼��ǰÿ����������Ӧ���������·�����ȡ�
//			 
//Dijkstra�и������·�����ȵĹ���:dist[u]+a[u][i]<dist[i]ʱ,����prev[i]=u.(a[u][i]:��u��i�ı߳�;prev[i]��i�����ǰһ������)

package greedy_algorithm;

public class Dijkstra {
public static void dijkstra(int v,float[][] a,float[] dist,int[] prev)
{
	int n=a.length-1;
	if(v<1||v>n) return;
	boolean[] s=new boolean[n+1];
	//��ʼ����Դ��v���ڵĸ�������Ϣ(dist,prev,s)
	for(int i=1;i<=n;i++)
	{
		dist[i]=a[v][i];
		s[i]=false;
		if(dist[i]==Float.MAX_VALUE) prev[i]=0;
		else prev[i]=v;
	}
	//��ʼ��Դ��v;
	dist[v]=0;
	s[v]=true;
	//����n-1�Σ���ʣ���n-1���������s������
	for(int i=1;i<n;i++)
	{
		float temp=Float.MAX_VALUE;
		int u=v;
		//���ҵ�s��������Ķ��㣬�������s������
		for(int j=1;j<=n;j++)	
		{
			if(!s[j]&&dist[j]<temp)		//�������в���s�����еĶ��㣬�ҳ��������·��
			{
				u=j;
				temp=dist[j];
			}
		}
		s[u]=true;    //��u����s������
		//������u�����Ҳ���s�����еĶ��㣬�жϼ���u��dist[j]�Ƿ����仯
		for(int j=1;j<=n;j++)
		{
			if(!s[j]&&a[u][j]<Float.MAX_VALUE)
			{
				float dis=dist[u]+a[u][j];
				if(dis<dist[j])	
				{
					dist[j]=dis;	//����dist[j]
					prev[j]=u;		//���ȵ���������Ϊu
				}
			}
		}
	}
}
public static void main(String[] args) {
	float[][] a={{0,0,0,0,0,0},
			{0,0,10,Float.MAX_VALUE,30,100},
			{0,Float.MAX_VALUE,0,50,Float.MAX_VALUE,Float.MAX_VALUE},
			{0,Float.MAX_VALUE,Float.MAX_VALUE,0,Float.MAX_VALUE,10},
			{0,Float.MAX_VALUE,Float.MAX_VALUE,20,0,60},
			{0,Float.MAX_VALUE,Float.MAX_VALUE,Float.MAX_VALUE,Float.MAX_VALUE,0}};
	int v=1;
	float[] dist=new float[6];
	int[] prev=new int[6];
	dijkstra(v, a, dist, prev);
	for(int i=1;i<=5;i++)
		System.out.println(dist[i]);
}
}
