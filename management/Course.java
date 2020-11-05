package management;

import java.util.ArrayList;
import java.util.Objects;

class Time
{
	final int weekStart;
   	final int weekend;
   	final int weekday;
   	final int daytime;
   	Time(int weekStart,int weekend,int weekday,int daytime)
   	{
   		this.weekStart =weekStart;
   		this.weekend=weekend;
   		this.weekday=weekday;
   		this.daytime=daytime;
   	}
}

public class Course implements Comparable<Course> 
{
   	String cid;
   	String name;
   	ArrayList<Integer> TeacherId= new ArrayList<>();
   	final ArrayList<Integer> StudentId= new ArrayList<>();
   	int capacity;
   	Time time;
   	public String toString()
   	{
   		StringBuilder tea;
   		int i;
   		if(TeacherId.size()==0)
   		{
   			tea = new StringBuilder();
   		}
   		else
   		{
   			Teacher temp= PersonList.getTeacherId(TeacherId.get(0));
	   		if(temp==null)
	   		{
	   			tea = new StringBuilder(String.format("%05d", TeacherId.get(0)));
	   		}
	   		else
	   		{
	   			tea = new StringBuilder(Objects.requireNonNull(PersonList.getTeacherId(TeacherId.get(0))).getName());
	   		}
	   		for(i=1;i<TeacherId.size();i++)
	   		{
	   			tea.append(',');
	   			temp= PersonList.getTeacherId(TeacherId.get(i));
	   	   		if(temp==null)
	   	   		{
	   	   			tea.append(TeacherId.get(i).toString());
	   	   		}
	   	   		else
	   	   		{
	   	   			tea.append(Objects.requireNonNull(PersonList.getTeacherId(TeacherId.get(i))).getName());
	   	   		}
	   		}
   		}
   		String cap=String.valueOf(this.capacity);
   		String qua=String.valueOf(this.StudentId.size());
   		String time="["+ this.time.weekStart +"-"+ this.time.weekend +"]"+ this.time.weekday +","+ this.time.daytime;
		return "CID:"+ this.cid.toUpperCase()+",Name:"+ this.name+",Teachers:["+tea+"],Capacity:"+qua+"/"+cap+",Time:"+time;
   	}
   	@Override
    public int compareTo(Course o)
   	{
        return this.cid.compareTo(o.cid);
    }
   	int inStudent(int sid)
   	{
   		int i;
		for(i=0;i<this.StudentId.size();i++)
		{
			if(this.StudentId.get(i)==sid)
			{
				return i;
			}
		}
		return -1;
   	}
}
