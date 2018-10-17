//Kruskal�㷨:���Ƚ�G��n�����㿴��n����������ͨ��֧�������еı߰�Ȩֵ��С��������Ȼ��ӵ�һ���߿�ʼ������Ȩ������˳��鿴ÿһ���ߣ�����������������������ͬ��
//			  ��֧�����鿴����K����(v,w)ʱ������˵�v��w�ֱ��ǵ�ǰ������ͬ����ͨ��֧T1��T2�еĶ���ʱ�����ñ�(v,w)��T1��T2����Ϊһ����ͨ��֧��Ȼ������鿴k+1��
//           ��;����˵�v��w�ڵ�ǰ��ͬһ����ͨ��֧�У���ֱ���ٲ鿴��k+1���ߡ��������һֱ���е�ֻʣ��һ����ͨ��֧ʱΪֹ��
//ʱ�临�Ӷ�:O(eloge) 
//
package greedy_algorithm;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class Kruskal {
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
			if(N>0&&N==pq.length/4) resize(pq.length/2);
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
	public static class EdgeNode implements Comparable
	{
		float weight;
		int u,v;
		public EdgeNode(int uu,int vv,float ww) {
			u=uu;
			v=vv;
			weight=ww;
		}
		public int compareTo(Object x)
		{
			double xw=((EdgeNode)x).weight;
			if(weight<xw) return -1;
			if(weight==xw) return 0;
			return 1;
		}
		@Override
		public String toString() {
			return "EdgeNode [weight=" + weight + ", u=" + u + ", v=" + v + "]";
		}
	}
	public static class UF {

	    private int[] parent;  // parent[i] = parent of i
	    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
	    private int count;     // number of components

	    public UF(int n) {
	        if (n < 0) throw new IllegalArgumentException();
	        count = n;
	        parent = new int[n];
	        rank = new byte[n];
	        for (int i = 0; i < n; i++) {
	            parent[i] = i;
	            rank[i] = 0;
	        }
	    }

	    public int find(int p) {
	        validate(p);
	        while (p != parent[p]) {
	            parent[p] = parent[parent[p]];    // path compression by halving
	            p = parent[p];
	        }
	        return p;
	    }

	    public int count() {
	        return count;
	    }

	    public boolean connected(int p, int q) {
	        return find(p) == find(q);
	    }

	    public void union(int p, int q) {
	        int rootP = find(p);
	        int rootQ = find(q);
	        if (rootP == rootQ) return;

	        if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
	        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
	        else {
	            parent[rootQ] = rootP;
	            rank[rootP]++;
	        }
	        count--;
	    }
	    private void validate(int p) {
	        int n = parent.length;
	        if (p < 0 || p >= n) {
	            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
	        }
	    }
	}
	public static boolean kruskal(int n,int e,EdgeNode[] E,EdgeNode[] t)
	{
		MinHeap H=new MinHeap(1);
		for(int i=0;i<e;i++)
			H.put(E[i]);
		UF U=new UF(n);
		int k=0;
		while(e>0&&k<n-1)
		{
			EdgeNode x=(EdgeNode)H.removeMin();
			e--;
			int a=U.find(x.u);
			int b=U.find(x.v);
			if(a!=b)
			{
				t[k++]=x;
				U.union(a, b);
			}
		}
		return (k==n-1);
	}
	public static void main(String[] args) {
		int n=7;
		int e=16;
		EdgeNode[] E={new EdgeNode(1,2,6),
					new EdgeNode(1,3,1),
					new EdgeNode(1,4,5),
					new EdgeNode(2,3,5),
					new EdgeNode(3,4,5),
					new EdgeNode(2,5,3),
					new EdgeNode(3,5,6),
					new EdgeNode(3,6,4),
					new EdgeNode(4,6,2),
					new EdgeNode(5,6,6),
					new EdgeNode(1,0,0),
					new EdgeNode(2,0,0),
					new EdgeNode(3,0,0),
					new EdgeNode(4,0,0),
					new EdgeNode(5,0,0),
					new EdgeNode(6,0,0),};
		EdgeNode[] t=new EdgeNode[16];
		for(int i=0;i<16;i++)
			t[i]=new EdgeNode(0,0,0);
		kruskal(n, e, E, t);
		for(int i=0;i<16;i++)
		{
			if(t[i].weight!=0)
				System.out.println(t[i]);
		}
	}
		
}
