//������������:����������ʾΪһϵ��������֮�ͣ�n=n1+n2+....nk;����n1>=n2>=n3>=...>=nk>=1,k>=1;


package Recursion_and_division;
public class Int_division {
public static int q(int n,int m)
{
	if(n<1||m<1) return 0;
	if(n==1&&m==1) return 1;
	if(n<m) return q(n,n);
	if(n==m) return 1+q(n,n-1);
	return q(n,m-1)+q(n-m,m);
}
public static void main(String[] args) {
	System.out.print(q(6,6));
}
}
