//图的m着色问题
//输入:
//无向连通图G
//m种颜色
//输出:
//顶点着色方案
//任意两个相邻顶点都有不同颜色
//解空间:
//x[i]表示顶点i的颜色
//每个x[i]有m种不同取值
package TrackBack;

public class Graphic_color_question {
static int n;//图的顶点数
static int m;//可用颜色数
static boolean[][] a={{},{},{},{},{},{}};
static int[] x;//当前解
static long sum;//当前已找到的可m着色方案数
public static long mColoring(int mm)
{
	m=mm;
	sum=0;
	backtrack(1);
	return sum;
}
private static void backtrack(int t)
{
	if(t>n)
	{
		sum++;
		for(int i=1;i<=n;i++)
		{
			System.out.println(x[i]+" ");
		}
		System.out.println();
	}
	else
	{
		for(int i=1;i<=m;i++)
		{
			x[t]=i;
			if(ok(t)) backtrack(t+1);
			x[t]=0;
		}
	}
}
private static boolean ok(int k)
{
	//检查颜色是否可用
	for(int j=1;j<=n;j++)
		//如果j号图与当前图(k号图)相邻，且两个图颜色相同，返回false;
		if(a[k][j]&&(x[j]==x[k])) return false;
	return true;
}
public static void main(String[] args) {
	System.out.println(mColoring(3));
}
}
