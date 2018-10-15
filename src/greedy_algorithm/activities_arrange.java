//活动安排问题:设有n个活动的集合E={1,2,3,4..n},其中，每个活动都要求使用同一资源，如演讲会场等，而在同一时间内只有一个活动能使用这一资源。每个活动i都有
//			  一个要求使用该资源的起始时间si和一个结束时间fi,且si<fi.如果选择了活动i，则它在半开时间区间[si,fi)内占用资源。若区间[si,fi)与区间[sj,fj)
//			 不相交，则活动i与活动j是相容的。活动安排问题就是要在所给的活动集合中选出最大的相容活动子集合。
//思路:
//   活动i与活动j相容的充分必要条件是s[i]>=f[j]
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
	System.out.println("最大的相容活动子集数是:"+greedySelector(s, f, a));
	System.out.println("可安排的活动有:");
	for(int i=1;i<a.length;i++)
		if(a[i])
		System.out.println(i+" ");
}
}
