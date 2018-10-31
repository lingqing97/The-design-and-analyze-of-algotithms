//0_1 ��������:
//�޽纯��bound(V) = p + q(pΪ��ǰ��ֵ��qΪʣ����Ʒһ�㱳����������Ž�)
//����q���������{i+1, ..., n}��C����һ�㱳����������Ž�
//���԰����ر�˳�������򱳰���װ����Ʒ�õ�q

//��ʽ��֧�޽編:
//ÿ�δӴ����ѡ������bound(i)�Ľڵ���Ϊ���Žڵ�
package branch_and_bound_method;

import greedy_algorithm.bag_qustion.Element;

public class bag01_question {
	public static class MaxHeap {
		private Comparable[] pq;
		private int N;
		public MaxHeap(int max) {
			pq=new Comparable[max+1];
			N=0;
		}
		public void put(Comparable tmp)
		{
			if((N+1)==pq.length) resize(pq.length*2);
			pq[++N]=tmp;
			swin(N);
		}
		public Comparable removeMax()
		{
			Comparable key=pq[1];
			exch(1, N--);
//			if(N>0&&N==pq.length/4) resize(pq.length/2);
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
			while(k>1&&less(pq[k/2],pq[k]))
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
				if(j<N&&less(pq[j], pq[j+1])) j++;
				if(!less(pq[k], pq[j])) break;
				exch(k, j);
				k=j;
			}
		}
	}
	public static class MergeSort
	{
		private static Element[] aux;
		public static void sort(Element[] a)
		{
			aux=new Element[a.length];
			mergeSort(a,0,a.length-1);
		}
		public static void mergeSort(Element[] a,int low,int high)
		{
			if(low<high)//����������Ԫ�زŽ��й鲢����
			{
				int mid=(low+high)/2;
				mergeSort(a,low,mid);
				mergeSort(a,mid+1,high);
				merge(a,low,mid,high);
			}
		}
		public static void merge(Element[] a,int low,int mid,int high)
		{
			int j=low;
			int k=mid+1;
			for(int i=low;i<=high;i++)
				aux[i]=a[i];
			for(int i=low;i<=high;i++)
			{
				if(j>mid) a[i]=aux[k++];
				else if(k>high) a[i]=aux[j++];
				else if(aux[j].compareTo(aux[k])<0) a[i]=aux[j++];
				else a[i]=aux[k++];
			}
		}
	}

	private static class BBnode
	{
		BBnode parent;	//���ڵ�
		boolean leftChild;	//����ӽڵ��־
		
		BBnode(BBnode par,boolean ch)
		{
			parent=par;
			leftChild=ch;
		}
		
	}
	private static class HeapNode implements Comparable
	{
		BBnode liveNode;	//��ڵ�
		double upperProfit;//�ڵ�ļ�ֵ�Ͻ�
		double profit;//�ڵ�����Ӧ�ļ�ֵ
		double weight;//�ڵ�����Ӧ������
		int level;//��ڵ����Ӽ����������Ĳ����
		
		//���췽��
		HeapNode(BBnode node,double up,double pp,double ww,int lev)
		{
			liveNode=node;
			upperProfit=up;
			profit=pp;
			weight=ww;
			level=lev;
		}
		public int compareTo(Object x)
		{
			double xup=((HeapNode)x).upperProfit;
			if(upperProfit<xup) return -1;
			if(upperProfit==xup) return 0;
			return 1;
		}
	}
	private static class Element implements Comparable
	{
		int id;	//���
		double d;//��λ������ֵ
		//���췽��
		public Element(int idd,double dd) {
			id=idd;
			d=dd;
		}
		public int compareTo(Object x)
		{
			double xd=((Element)x).d;
			if(d<xd) return -1;
			if(d==xd) return 0;
			return 1;
		}
	}
	public static class BBKnapsack
	{
		static double c;	//��������
		static int n;	//��Ʒ����
		static double[] w;//��Ʒ��������
		static double[] p;//��Ʒ��ֵ����
		static double cw;//��ǰ����
		static double cp;//��ǰ��ֵ
		static int[] bestx;//���Ž�
		static MaxHeap heap;//��ڵ����ȶ���
		//��ʼ��
	    public static double knapsack(double[] pp,double[] ww,double cc,int[] xx)
	    {
	    	c=cc;
	    	n=pp.length-1;
	    	
	    	//��������λ������ֵ�������Ʒ����
	    	Element[] q=new Element[n];
	    	double ws=0.0;//װ����Ʒ����
	    	double ps=0.0;//װ����Ʒ��ֵ
	    	for(int i=1;i<=n;i++)
	    	{
	    		q[i-1]=new Element(i,pp[i]/ww[i]);
	    		ps+=pp[i];
	    		ws+=ww[i];
	    	}
	    	//������Ʒװ��
	    	if(ws<=c)
	    	{
	    		for(int i=1;i<=n;i++)
	    			xx[i]=1;
	    		return ps;
	    	}
	    	
	    	//����λ������ֵ����
	    	MergeSort.sort(q);
	    	
	    	//��ʼ�������ݳ�Ա
	    	p=new double[n+1];
	    	w=new double[n+1];
	    	for(int i=1;i<=n;i++)
	    	{
	    		p[i]=pp[q[n-i].id];
	    		w[i]=ww[q[n-i].id];
	    	}
	    	cw=0.0;
	    	cp=0.0;
	    	bestx=new int[n+1];
	    	heap=new MaxHeap(n);
	    	
	    	//����BBknapsack����������Ž�
	    	double maxp=bbKnapsack();
	    	for(int i=1;i<=n;i++)
	    		xx[q[n-i].id]=bestx[i];
	    	return maxp;
	    }
		private static double bound(int i)
		{
			//����ڵ�����Ӧ��ֵ���Ͻ�
			double cleft=c-cw;//ʣ������
			double b=cp;//��ǰ��ֵ�Ͻ�
			while(i<=n&&w[i]<=cleft)
			{
				cleft-=w[i];
				b+=p[i];
				i++;
			}
			//������δװ����������ʣ��һ��ռ��ַŲ������һ����Ʒ
			//��װ��һ���ֵ���Ʒ
			if(i<=n) b+=(p[i]/w[i])*cleft;
			return b;
		}
		private static void addLiveNode(double up,double pp,double ww,int lev,BBnode par,boolean ch)
		{
			//��һ���µĻ�ڵ���뵽�Ӽ���������H��
			BBnode d=new BBnode(par, ch);
			HeapNode node=new HeapNode(d,up,pp,ww,lev);
			heap.put(node);
		}
		private static double bbKnapsack()
		{
			//��ʼ��
			BBnode enode=null;
			int i=1;
			double bestp=0.0;
			double up=bound(1);
			//�����Ӽ��ռ���
			while(i!=(n+1))
			{
				//��Ҷ�ڵ�
			    //��鵱ǰ��չ�ڵ�����ӽڵ�
				double wt=cw+w[i];
				if(wt<c)
				{
					//����ӽڵ�Ϊ���нڵ�
					//�ж��Ƿ���Ҫ���м�֧����
					if(cp+p[i]>bestp)
					{
						bestp=cp+p[i];
						addLiveNode(up, cp+p[i], cw+w[i], i+1, enode, true);
					}
				}
				up=bound(i+1);
				//��鵱ǰ��չ�ڵ���Ҷ��ӽڵ�
				if(up>=bestp)
				{
					//���������ܺ������Ž�
					addLiveNode(up,cp,cw,i+1,enode,false);
				}
				//ȡ��һ��չ�ڵ�
				HeapNode node=(HeapNode)heap.removeMax();
				enode=node.liveNode;
				cw=node.weight;
				cp=node.profit;
				up=node.upperProfit;
				i=node.level;
			}
			//���쵱ǰ ���Ž�
			for(int j=n;j>0;j--)
			{
				bestx[j]=(enode.leftChild)?1:0;
				enode=enode.parent;
			}
			return cp;
		}
	}
	public static void main(String[] args) {
		double[] v={0,6,3,5,4,6};	//�����0����Ʒ�����ͽ���ֵ��Ϊ0�������㷨�����
	    double[] w={0,2,2,6,5,4};
		double cc=10;
		int n=5;
		int[] x=new int[n+1];
		System.out.println("����ֵΪ:"+BBKnapsack.knapsack(v, w, cc, x));
		for(int i=1;i<x.length;i++)
			if(x[i]==1)
				System.out.println("ѡ����"+i+"����Ʒ");
	}
}
