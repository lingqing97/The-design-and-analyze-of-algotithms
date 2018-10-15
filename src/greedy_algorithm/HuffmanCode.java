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
		public static Comparable[] pq;
		public static int n;
	    private MinHeap() { }
	    public static void initialize(Comparable[] w,int nn)
	    {
	    	pq=w;
	    	n=nn;
	    }
	    public static void sort() {
	        for (int k = n/2; k >= 1; k--)
	            sink(k, n);
	        while (n > 1) {
	            exch(1, n--);
	            sink(1, n);
	        }
	    }
	    private static void sink(int k, int n) {
	        while (2*k <= n) {
	            int j = 2*k;
	            if (j < n && less(j, j+1)) j++;
	            if (!less( k, j)) break;
	            exch( k, j);
	            k = j;
	        }
	    }
	    private static void swim(int k)
	    {
	    	while(k>1&&less(k/2,k))
	    	{
	    		exch(k/2,k);
	    		k=k/2;
	    	}
	    }
	    private static boolean less(int i, int j) {
	        return pq[i-1].compareTo(pq[j-1]) < 0;
	    }
	    
	    private static void exch(int i, int j) {
	        Comparable swap = pq[i-1];
	        pq[i-1] = pq[j-1];
	        pq[j-1] = swap;
	    }
	    private static void show(Comparable[] a) {
	        for (int i = 0; i < a.length; i++) {
	            StdOut.println(a[i]);
	        }
	    }
	    public static void put(Comparable v)
	    {
	    	pq[++n]=v;
	    	swim(n);
	    }
	    public static Comparable removeMin()
	    {
	    	Comparable min=pq[1];
	    	exch(1,n--);
	    	pq[n+1]=null;
	    	sink(1,n);
	    	return min;
	    }
	}
	public static Bintree huffmanTree(float[] f,char[] ch)
	{
		//���ɵ��ڵ���
		int n=f.length;
		Huffman[] w=new Huffman[n+1];
		for(int i=0;i<n;i++)
		{
			Bintree x=new Bintree();
			x.makeTree(ch[i], null, null);
			w[i+1]=new Huffman(x,f[i]);
		}
		//�����ȶ���
		MinHeap H=new MinHeap();
		H.initialize(w, n);
		
		//�����ϲ���СƵ����
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