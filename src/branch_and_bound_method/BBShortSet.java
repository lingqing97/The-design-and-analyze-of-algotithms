//��Դ���·�����⣺
//����:
//�����ȨͼG=(V, E)
//ͼ�ж���s
//���:
//s��ͼ��������������·��
package branch_and_bound_method;

public class BBShortSet {
static class HeapNode implements Comparable
{
	int i;//������
	float length;//��ǰ·��
	HeapNode(int ii,float ll){
		i=ii;
		length=ll;
	}
	public int compareTo(Object x)
	{
		float xl=((HeapNode)x).length;
		if(length<xl) return -1;
		if(length==xl) return 0;
		return 1;
	}
}

static float[][] a={{0,0,0,0,0,0},
	{0,0,10,Float.MAX_VALUE,30,100},
	{0,Float.MAX_VALUE,0,50,Float.MAX_VALUE,Float.MAX_VALUE},
	{0,Float.MAX_VALUE,Float.MAX_VALUE,0,Float.MAX_VALUE,10},
	{0,Float.MAX_VALUE,Float.MAX_VALUE,20,0,60},
	{0,Float.MAX_VALUE,Float.MAX_VALUE,Float.MAX_VALUE,Float.MAX_VALUE,0}};;//ͼG���ڽӾ���

public static void shortset(int v,float[] dist,int[] p)
{
	int n=p.length-1;
	MinHeap H=new MinHeap(2*n);
	//��v��Ϊ��ʼ��Դ��
	HeapNode enode=new HeapNode(v,0);
	//��ʼ��dist
	for(int i=1;i<=n;i++)
		dist[i]=Float.MAX_VALUE;
	dist[v]=0;
	//������ռ�
	while(true)
	{
		for(int j=1;j<=n;j++)
		{
			//���j������enode�������ڣ���dist[j]�б�С
			if(a[enode.i][j]<Float.MAX_VALUE&&(enode.length+a[enode.i][j])<dist[j])
			{
				dist[j]=enode.length+a[enode.i][j];
				//����j��ǰ������Ϊj
				p[j]=enode.i;
				//��j�Ŷ���ѹ��С����
				HeapNode temp_node=new HeapNode(j,dist[j]);
				H.put(temp_node);
			}
			//���������еĿ��ܺ��˳�ѭ��
			if(H.isEmpty()) break;
			//����ǰ·��������С�Ķ����С����ȡ������Ϊ�µ�Դ��
			enode=(HeapNode)H.removeMin();
		}
	}
}
public static class MinHeap {
	private Comparable[] pq;
	private int N;
	public MinHeap(int max) {
		pq=new Comparable[max+1];
		N=0;
	}
	public void put(Comparable tmp)
	{
		if((N+1)==pq.length) resize(pq.length*2);
		pq[++N]=tmp;
		swin(N);
	}
	public Comparable removeMin()
	{
		Comparable key=pq[1];
		exch(1, N--);
//		if(N>0&&N==pq.length/4) resize(pq.length/2);
		pq[N+1]=' ';
		sink(1);
		return key;
	}
	public boolean isEmpty()
	{
		return N==0;
	}
	public int size()
	{
		return N;
	}
	private void resize(int max)
	{
		Comparable[] temp=new Comparable[max];
		for(int i=0;i<=N;i++)
			temp[i]=pq[i];
		pq=temp;
	}
	private boolean less(Comparable x,Comparable y)
	{
		return x.compareTo(y)<0;
	}
	private void exch(int x,int y)
	{
		Comparable temp=pq[x];
		pq[x]=pq[y];
		pq[y]=temp;
	}
	private void swin(int k)
	{
		while(k>1&&less(pq[k],pq[k/2]))
		{
			exch(k/2, k);
			k/=2;
		}
	}
	private void sink(int k)
	{
		while(2*k<=N)
		{
			int j=2*k;
			if(j<N&&less(pq[j+1], pq[j])) j++;
			if(!less(pq[j], pq[k])) break;
			exch(k, j);
			k=j;
		}
	}
}
public static void main(String[] args) {
	int v=0;
	float[] dist=new float[6];
	int[] p=new int[6];
	shortset(v, dist, p);
	for(int i=1;i<6;i++)
		System.out.println(i+"��ǰ��������:"+p[i]);
}
}
