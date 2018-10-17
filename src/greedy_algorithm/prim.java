//��С����������:��G=(V,E)��������ͨ��Ȩͼ������һ�����硣E��ÿ����(v,w)��ȨΪc[v][w].���G����ͼG'��һ�ð���G�����ж�����������G'��
//				G�����������������ϸ���Ȩֵ֮�ͳ�Ϊ���������ĺķѡ���G���������������ķ���С����������ΪG����С������.
//Prim�㷨:
//		������S={1},Ȼ��ֻҪs��v�����Ӽ����ͽ������µ�̰��ѡ��:ѡȡ��������i����s,j����(v-s)����c[i][j]��С�ı�,������j��ӵ�s�С�
//		�������һֱ���е�s=vʱΪֹ��
//ʱ�临�Ӷ���O(N^2) 
package greedy_algorithm;

public class prim {
	public static void prim(int n,float[][] c)
	{
		float[] lowcost=new float[n+1];	//��¼��ǰ����£������㵽�����������(���������ǵ�Դ�㣬������dijkstra������)
		int[] closeset=new int[n+1];		//��¼��ǰ����£�����������������Ķ���
		boolean[] s=new boolean[n+1];		//��¼�����Ƿ��ڼ���s��
		//��ʼ������s={1}
		s[1]=true;
		for(int i=2;i<=n;i++)
		{
			lowcost[i]=c[1][i];
			closeset[i]=1;
			s[i]=false;
		}
		for(int i=1;i<n;i++)		//��ʣ��n-1��������뵽����s��
		{
			float min=Float.MAX_VALUE;
			int j=1;		//j�������õ�ǰ���ڱ����Ķ��㣬��ʼΪ1
			for(int k=2;k<=n;k++)	//�ҳ���������������Ķ���
			{
				if(!s[k]&&lowcost[k]<min)	//�ӵ����ľ�����ѡ����Сֵ
				{
					min=lowcost[k];
					j=k;
				}
			}
			//�ҵ���������������ĵ㣬�����ӡ����
			System.out.println(j+", "+closeset[j]);
			s[j]=true;
			//����ʣ�ඥ�㵽���ĵ�ǰ������룬��colseset[k]
			for(int k=2;k<=n;k++)
			{
				if(!s[k]&&c[j][k]<lowcost[k])
				{
					lowcost[k]=c[j][k];
					closeset[k]=j;
				}
			}
		}
	}
	public static void main(String[] args) {
		float[][] c={{0,0,0,0,0,0,0},
				{0,0,6,1,5,Float.MAX_VALUE,Float.MAX_VALUE},
				{0,6,0,5,Float.MAX_VALUE,3,Float.MAX_VALUE},
				{0,1,5,0,5,6,4},
				{0,5,Float.MAX_VALUE,5,0,Float.MAX_VALUE,2},
				{0,Float.MAX_VALUE,3,6,Float.MAX_VALUE,0,6},
				{0,Float.MAX_VALUE,Float.MAX_VALUE,4,2,6,0}};
		prim(c.length-1, c);
	}
}
