package greedy_algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Vector;

public class HuffmanCode {
	private static class Bintree
	{
		char w;
		Bintree leftchild;
		Bintree rightchild;
		public Bintree() {
			w='#';
			leftchild=null;
			rightchild=null;
		}
		public void makeTree(char ww,Bintree l,Bintree r)
		{
			w=ww;
			leftchild=l;
			rightchild=r;
		}
	}
	private static class Huffman implements Comparable
	{
		Bintree tree;
		float weight;
		public Huffman(Bintree tt,float ww) {
			tree=tt;
			weight=ww;
		}
		public int compareTo(Object x)
		{
			float xw=((Huffman)x).weight;
			if(weight<xw) return -1;
			if(weight==xw) return 0;
			return 1;
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
	public static Bintree huffmanTree(float[] f,char[] ch)
	{
		//生成单节点树
		int n=f.length;
		Huffman[] w=new Huffman[n+1];
		for(int i=0;i<n;i++)
		{
			Bintree x=new Bintree();
			x.makeTree(ch[i], null, null);
			w[i+1]=new Huffman(x,f[i]);
		}
		//建优先队列
		MinHeap H=new MinHeap(n);
		for(int i=1;i<=n;i++)
			H.put(w[i]);
		
		//反复合并最小频率树
		for(int i=1;i<n;i++)
		{
			Huffman x=(Huffman)H.removeMin();
			Huffman y=(Huffman)H.removeMin();
			Bintree z=new Bintree();
			z.makeTree('#', x.tree, y.tree);
			Huffman t=new Huffman(z,x.weight+y.weight);
			H.put(t);
		}
		return ((Huffman)H.removeMin()).tree;
	}
	public static void Huffman_Coding(Bintree root, String curcode,HashMap<Character,String> Huff_Dic)
	{
		if(root.leftchild==null&&root.rightchild==null)
		{
			Huff_Dic.put(root.w,curcode);
			System.out.println(root.w+" "+curcode);
			return;
		}
	    String lcode = curcode;
		String rcode = curcode;
		lcode+="0";
		rcode+="1";
	 
		Huffman_Coding(root.leftchild,lcode,Huff_Dic);
		Huffman_Coding(root.rightchild,rcode,Huff_Dic);
	}
	public static void main(String[] args) {
		float[] f={45,13,12,16,9,5};
		char[] ch={'a','b','c','d','e','f'};
		String curcode="";
		HashMap<Character,String> Huff_Dic=new HashMap() ;
		Bintree root=huffmanTree(f,ch);
		Huffman_Coding(root, curcode, Huff_Dic);
	}
}
