//�����������:������������x��y,����һ����z����x������������y��������ʱ�����z��x��y�Ĺ���������;
//����x��y���У��ҳ����ǵ�����������У�

//˼·:
//������x={x1,x2,x3,...xi}��y={y1,y2,y3,...yj},�����������������
//1.xi==yj,������ȼ�����x(i-1)��y(j-1)�������������
//2.xi!=yj����x��y������������е��� x(i-1)��yj������������� �� xi ��y(j-1)������������� �еĽϴ�ֵ

//�ݹ鶨�����Ž⣺
//��c(i,j)��x={x1,x2,x3,...xi}��y={y1,y2,y3,...yj}������������еĳ���
//��
//		 {	0      i=0||j=0	
//c(i,j)={	c(i-1,j-1)+1   xi==yj
//		 {	max( c(i-1,j), c(i,j-1)) xi!=yj  && i>0 && j>0

package Dynamic_planning;

import sun.applet.Main;

public class longestCommonSubstring {
//int[][] b���ڼ�¼c[i][j]��ֵ�����ĸ�������õ��ģ�
//��b[i][j]=1,������c(i,j)=c(i-1,j-1)+1�õ���
//��b[i][j]=2,������c(i,j)=max( c(i-1,j), c(i,j-1)) �� c(i-1,j) >= c(i,j-1)
//��b[i][j]=3,������c(i,j)=max( c(i-1,j), c(i,j-1)) �� c(i-1,j) < c(i,j-1)
	public static int lcslength(char[] x,char[] y,int[][] b)
	{
		int len_x=x.length;
		int len_y=y.length;
		int[][] c=new int[len_x+1][len_y+1];
		for(int i=0;i<=len_x;i++) c[i][0]=0;
		for(int i=0;i<=len_y;i++) c[0][i]=0;
		for(int i=1;i<=len_x;i++)	//i,j���Ǵ�1��ʼ,��x��y���Ǵӷǿ����п�ʼ
		{
			for(int j=1;j<=len_y;j++)
			{
				if(x[i-1]==y[j-1])		//�ж�x�ĵ�i��Ԫ���Ƿ����y�ĵ�j��Ԫ�أ�����������ʾ������Ҫ����һ
				{
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]=1;
				}
				else if(c[i-1][j]>=c[i][j-1])
				{
					c[i][j]=c[i-1][j];
					b[i][j]=2;
				}
				else
				{
					c[i][j]=c[i][j-1];
					b[i][j]=3;
				}
			}
		}
		return c[len_x][len_y];
	}
	public static void lcs(int i,int j,char[] x,int[][] b)
	{
		if(i==0||j==0) return;
		if(b[i][j]==1) 
		{
			lcs(i-1,j-1,x,b);
			System.out.print(x[i-1]);
		}
		else if(b[i][j]==2) lcs(i-1,j,x,b);
		else lcs(i,j-1,x,b);

	}
	public static void main(String[] args) {
		char[] x={'a','b','c','d','f','e'};
		char[] y={'a','b','c','t'};
		int[][] b=new int[x.length+1][y.length+1];
		System.out.println(lcslength(x, y, b));
		lcs(x.length, y.length, x, b);
	}
}
