//װ������:��һ����n����װ��Ҫװ�������������ֱ�Ϊc1��c2���ִ������У���װ��i������Ϊwi,��w1+w2+...wn<=c1+c2;
//		��ȷ���Ƿ���һ�������װ�ط����ɽ���n����װ��װ�������Ҵ�������У��ҳ�һ��װ�ط���
//�Ӽ����Ĺ���:
//            0
//��װ��1:   1  0
//��װ��2: 1  0 1 0
//��װ��3:...........
//
//˼·:(�����Ӽ���)
//     ����cw��¼��ǰ�ڵ���Ӧ��װ��������bestw��¼��ǰ���װ������,w[i]Ϊi�ż�װ�������,r���༯װ�������,x��¼�Ӹ�����ǰ�ڵ��·����bestx��¼��ǰ���Ž�·��,c��һ�Ŵ���������
//     ����i=1,��i>nʱ���㷨������Ҷ�ڵ�,��ʱ��Ӧ������Ϊcw,���cw>bestw,�����bestw;
//     �ڱ����Ӽ����Ĺ�����,��ڵ�x[i]=1,�ҽڵ�x[i]=0,����cw+w[i]<=cʱ����������,�����������еݹ�����
//     ͬʱ���ڵ�ǰ�ڵ��£���δ������������������cw+r.��ˣ���cw+r<=bestwʱ�����Խ�z����������ȥ
//���Ͽ�֪��Լ��������cw+w[i]<c,�޽纯����cw+r<=bestw(��ʱִ�м�֧����)
//���ڱ���������ǰ��Ҫ�ж�cw+w[i]<c�Ž���������,�ڱ���������ǰ��Ҫ�ж�cw+r>=bestw�Ž���������
package TrackBack;

public class load_question {
public static class Loadind
{
    static int n;	//��װ����Ŀ
    static int[] w;	//��װ�������
    static int c;    //1�Ŵ���������
    static int cw;	//��ǰ������
    static int bestw;		//���Ž�
    static int r;		//ʣ������
    static int[] x;	//��ǰ�߹���·��
    static int[] bestx;	//��¼���Ž��·��
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
		//��1�ż�װ�俪ʼ����
		backtrack(1);
		return bestw;
	}
	private static void backtrack(int i)
	{
		//����Ҷ�ӽڵ㣬�ж��Ƿ������Ž�
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
			//�ж��Ƿ��ܹ�����������
			if(cw+w[i]<=c)
			{
				x[i]=1;
				cw+=w[i];
				backtrack(i+1);
				cw-=w[i];
			}
			//�����ж��Ƿ��ܽ���������(�ж��Ƿ���Ҫ��֧����)
			if(cw+r>=bestw)
			{
				x[i]=0;
				backtrack(i+1);
			}
			//�ڵݹ�����Ҫ�ָ�ԭ״̬
			r+=w[i];
		}
	}
	public static void main(String[] args) {
		int[] ww={0,2,3,4,1,7,5};
		int[] xx=new int[7];
		System.out.println("����װ����Ϊ:"+maxLoadind(ww, 10, xx));
		for(int i=1;i<=6;i++)
		{
			if(xx[i]==1)
				System.out.println("װ����"+i+"�ż�װ��");
		}
	}
}
}
