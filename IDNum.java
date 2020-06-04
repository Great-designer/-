package management;

class IDNum
{
	int locate;
	int birthDate;
	int order;
	final int check;
	IDNum(String IDN)
	{
		char t = IDN.charAt(17);
		int[] a = new int[18];
		if(t=='x'||t=='X')
		{
			a[17]=10;
		}
		else
		{
			a[17]=t-'0';
		}
		int i;
		for(i=0;i<=16;i++)
		{
			a[i]=IDN.charAt(i)-'0';
		}
		this.locate=a[0];
		for(i=1;i<=5;i++)
		{
			this.locate*=10;
			this.locate+=a[i];
		}
		this.birthDate =a[6];
		for(i=7;i<=13;i++)
		{
			this.birthDate *=10;
			this.birthDate +=a[i];
		}
		this.order=a[14];
		for(i=15;i<=16;i++)
		{
			this.order*=10;
			this.order+=a[i];
		}
		this.check=(1+4*a[0]+2*a[1]+ a[2] +6*a[3]+3*a[4]+7*a[5]+9*a[6]+10*a[7]+5*a[8]+8*a[9]+4*a[10]+2*a[11]+ a[12] +6*a[13]+3*a[14]+7*a[15]+9*a[16])%11;
	}
	static boolean checkIDNum(String IDNum)
	{
		if(IDNum.length()!=18)
		{
			return true;
		}
		char t = IDNum.charAt(17);
		int[] a = new int[18];
		if(t=='x'||t=='X')
		{
			a[17]=10;
		}
		else
		{
			a[17]=t-'0';
		}
		int i;
		for(i=0;i<=16;i++)
		{
			a[i]=IDNum.charAt(i)-'0';
		}
		int get=(1+4*a[0]+2*a[1]+ a[2] +6*a[3]+3*a[4]+7*a[5]+9*a[6]+10*a[7]+5*a[8]+8*a[9]+4*a[10]+2*a[11]+ a[12] +6*a[13]+3*a[14]+7*a[15]+9*a[16]+10*a[17])%11;
		if(get!=0)
		{
			return true;
		}
		int correct=0;
		int low=10*a[8]+a[9];
		if(low%4==0)
		{
			correct=1;
		}
		int up=10*a[6]+a[7];
		if(up%4!=0&&a[8]==0&&a[9]==0)
		{
			correct=0;
		}
		int month=10*a[10]+a[11];
		if(month>12)
		{
			return true;
		}
		int date=10*a[12]+a[13];
		if(date>31)
		{
			return true;
		}
		if(date==31)
		{
			if(month==4||month==6||month==9||month==11)
			{
				return true;
			}
		}
		if(date==30&&month==2)
		{
			return true;
		}
		return date == 29 && correct == 0 && month == 2;
	}
	public String toString()
	{
		String locate1=Integer.toString(this.locate);
		String birthDate1=Integer.toString(this.birthDate);
		String order1=Integer.toString(this.order);
		String check1;
		if(this.check!=10)
		{
			check1=Integer.toString(this.check);
		}
		else
		{
			check1="X";
		}
		return locate1+birthDate1+order1+check1;
	}
}


