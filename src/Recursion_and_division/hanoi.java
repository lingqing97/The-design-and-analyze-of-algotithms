//汉诺塔问题:A柱子上的圆盘移到C柱子上去


package Recursion_and_division;
public class hanoi {
private static int sum=0;
private static void move(int n,char a,char b)
{
	sum=sum+1;
	System.out.print("第"+sum+"次:"+a+"--->"+b+"\n");
}
private static void hanoi(int n,char a,char b,char c)
{
	if(n>0)
	{
		hanoi(n-1,a,c,b);
		move(n,a,c);
		hanoi(n-1,b,a,c);
	}
}
public static void main(String[] args) {
	char a='a';
	char b='b';
	char c='c';
	hanoi(3,a,b,c);
}
}
