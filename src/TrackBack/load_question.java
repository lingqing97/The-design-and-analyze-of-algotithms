//装载问题:有一批共n个集装箱要装上两艘载重量分别为c1和c2的轮船，其中，集装箱i的重量为wi,且w1+w2+...wn<=c1+c2;
//		请确定是否有一个合理的装载方案可将这n个集装箱装上这两艘船，如果有，找出一种装载方案
//子集树的构造:
//            0
//集装箱1:   1  0
//集装箱2: 1  0 1 0
//集装箱3:...........
//
//思路:(遍历子集树)
//     定义cw记录当前节点相应的装载重量，bestw记录当前最大装载重量,w[i]为i号集装箱的重量,r是余集装箱的重量,x记录从根到当前节点的路径，bestx记录当前最优解路径,c是一号船的载重量
//     先令i=1,当i>n时，算法搜索至叶节点,此时相应的重量为cw,如果cw>bestw,则更新bestw;
//     在遍历子集树的过程中,左节点x[i]=1,右节点x[i]=0,仅当cw+w[i]<=c时进入左子树,对左子树进行递归搜索
//     同时，在当前节点下，其未来的载重量均不超过cw+r.因此，当cw+r<=bestw时，可以将z的右子树剪去
//综上可知，约束函数是cw+w[i]<c,限界函数是cw+r<=bestw(此时执行减支操作)
//即在遍历左子树前需要判断cw+w[i]<c才进入左子树,在遍历右子树前需要判断cw+r>=bestw才进入右子树
package TrackBack;

public class load_question {
public static class Loadind
{
    static int n;	//集装箱数目
    static int[] w;	//集装箱的重量
    static int c;    //1号船的载重量
    static int cw;	//当前载重量
    static int bestw;		//最优解
    static int r;		//剩余重量
    static int[] x;	//当前走过的路径
    static int[] bestx;	//记录最优解的路径
	public static int maxLoadind(int[] ww,int cc,int[] xx) {
		n=ww.length-1;
		w=ww;
		c=cc;
		cw=0;
		bestw=0;
		for(int i=1;i<=n;i++)
			r+=w[i];
		x=new int[n+1];
		bestx=xx;
		//从1号集装箱开始遍历
		backtrack(1);
		return bestw;
	}
	private static void backtrack(int i)
	{
		//到达叶子节点，判断是否是最优解
		if(i>n)
		{
			if(cw>bestw) 
			{
				for(int j=1;j<=n;j++) bestx[j]=x[j];
				bestw=cw;
			}
			return;
		}
		else
		{
			r-=w[i];
			//判断是否能够进入左子树
			if(cw+w[i]<=c)
			{
				x[i]=1;
				cw+=w[i];
				backtrack(i+1);
				cw-=w[i];
			}
			//否则，判断是否能进入右子树(判断是否需要减支操作)
			if(cw+r>=bestw)
			{
				x[i]=0;
				backtrack(i+1);
			}
			//在递归中需要恢复原状态
			r+=w[i];
		}
	}
	public static void main(String[] args) {
		int[] ww={0,2,3,4,1,7,5};
		int[] xx=new int[7];
		System.out.println("最优装载量为:"+maxLoadind(ww, 10, xx));
		for(int i=1;i<=6;i++)
		{
			if(xx[i]==1)
				System.out.println("装载了"+i+"号集装箱");
		}
	}
}
}
