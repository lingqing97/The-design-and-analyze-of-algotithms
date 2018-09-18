//�������ĳ˷�����x��y����nλ��ʮ��������������Ҫ�������ǵĳ˻�xy��
//�㷨˼�룺
//x=a*pow(10,(n/2))+b   y=c*pow(10,(n/2))+d
//xy=(ac)*pow(10,n)+(ad+cb)*pow(10,(n/2))+(bd)
//xy=(ac)*pow(10,n)+((a-b)*(d-c)+ac+bd)*pow(10,(n/2))+bd  ��ԭʽ���б��Σ����˷�������ٵ�������������ʱ�临�Ӷ�

package Recursion_and_division;
import java.lang.Double;
public class BigInteger_multiply {
private static long big_multiply(long num1,long num2)
{
	if(num1<10||num2<10) return num1*num2;
	
	int size_num1=String.valueOf(num1).length();
	int size_num2=String.valueOf(num2).length();
	int half_size=Math.max(size_num1, size_num2)/2;
	
	
	long a=Long.valueOf(String.valueOf(num1).substring(0,size_num1-half_size));
	long b=Long.valueOf(String.valueOf(num1).substring(size_num1-half_size));
	long c=Long.valueOf(String.valueOf(num2).substring(0,size_num2-half_size));
	long d=Long.valueOf(String.valueOf(num2).substring(size_num2-half_size));
	long z0=big_multiply(a,c);
	long z2=big_multiply(b, d);
	long z1=big_multiply((a-b),(d-c))+z0+z2;
	return (long)(z0*Math.pow(10.0, 2*half_size)+z1*Math.pow(10.0, half_size)+z2);
}
public static void main(String[] args) {
	String a="1234567";
	String b="891234";
	System.out.print(big_multiply(Long.valueOf(a),Long.valueOf(b)));
}
}
