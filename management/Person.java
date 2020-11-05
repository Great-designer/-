package management;

import java.util.ArrayList;

class Person
{
	private IDNum id;
	private String name;
	private int sex;
	private int birthDate;
	String password;
	public IDNum getId()
	{
		return id;
	}
	public void setId(IDNum id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setSex(int sex)
	{
		this.sex = sex;
	}
	public void setBirthDate(int birthDate)
	{
		this.birthDate = birthDate;
	}
	public String toString()
	{
		String name1="Name:"+this.name;
		String id1="IDNum:"+this.id.toString();
		String sex2;
		if(this.id.order%2==1)
		{
			sex2="M";
		}
		else
		{
			sex2="F";
		}
		String sex1="Sex:"+sex2;
		int birth2=this.id.birthDate;
		String birth3=String.valueOf(birth2);
		String year=birth3.substring(0,4);
		String month=birth3.substring(4,6);
		String date=birth3.substring(6,8);
		String birth1="Birthday:"+year+"/"+month+"/"+date;
		return name1+"\n"+id1+"\n"+sex1+"\n"+birth1;
	}
}

class Student extends Person
{
	int StudentId;
	public String toString()
	{
		String name1="Name:"+this.getName();
		String id1="IDNum:"+this.getId().toString();
		String SID="SID:"+String.format("%08d",this.StudentId);
		String sex2;
		if(this.getId().order%2==1)
		{
			sex2="M";
		}
		else
		{
			sex2="F";
		}
		String sex1="Sex:"+sex2;
		int birth2=this.getId().birthDate;
		String birth3=String.valueOf(birth2);
		String year=birth3.substring(0,4);
		String month=birth3.substring(4,6);
		String date=birth3.substring(6,8);
		String birth1="Birthday:"+year+"/"+month+"/"+date;
		return name1+"\n"+id1+"\n"+SID+"\n"+sex1+"\n"+birth1;
	}
	static Student newStudent(String name,String id,String StudentId)
	{
		Student rua=new Student();
		rua.setName(name);
		IDNum pia=new IDNum(id);
		rua.setId(pia);
		if(pia.order%2==1)
		{
			rua.setSex(1);
		}
		else
		{
			rua.setSex(0);
		}
		rua.setBirthDate(pia.birthDate);
		rua.password="a12345";
		rua.StudentId=Integer.parseInt(StudentId);
		return rua;
	}
}

class Teacher extends Person
{
	int TeacherId;
	public String toString()
	{
		String name1="Name:"+this.getName();
		String id1="IDNum:"+this.getId().toString();
		String TID="TID:"+String.format("%05d",this.TeacherId);
		String sex2;
		if(this.getId().order%2==1)
		{
			sex2="M";
		}
		else
		{
			sex2="F";
		}
		String sex1="Sex:"+sex2;
		int birth2=this.getId().birthDate;
		String birth3=String.valueOf(birth2);
		String year=birth3.substring(0,4);
		String month=birth3.substring(4,6);
		String date=birth3.substring(6,8);
		String birth1="Birthday:"+year+"/"+month+"/"+date;
		return name1+"\n"+id1+"\n"+TID+"\n"+sex1+"\n"+birth1;
	}
	static Teacher newTeacher(String name,String id,String TeacherId)
	{
		Teacher rua=new Teacher();
		rua.setName(name);
		IDNum pia=new IDNum(id);
		rua.setId(pia);
		if(pia.order%2==1)
		{
			rua.setSex(1);
		}
		else
		{
			rua.setSex(0);
		}
		rua.setBirthDate(pia.birthDate);
		rua.password="a12345";
		rua.TeacherId=Integer.parseInt(TeacherId);
		return rua;
	}
}
