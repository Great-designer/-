package management;

import java.util.ArrayList;
import java.util.Collections;

public class CourseList
{
	private static CourseList cc;
	public static CourseList getCc()
	{
		return cc;
	}
	public static void setCc(CourseList cc)
	{
		CourseList.cc = cc;
	}
	private static ArrayList<Course> list= new ArrayList<>();
	public static ArrayList<Course> getList()
	{
		return list;
	}
	public static void setList(ArrayList<Course> list)
	{
		CourseList.list = list;
	}
	private CourseList() {}
	static Course getCourseById(String cid)
	{
		int i;
		for(i=0;i<CourseList.getList().size();i++)
		{
			if(CourseList.getList().get(i).cid.toUpperCase().compareTo(cid.toUpperCase())==0)
			{
				return CourseList.getList().get(i);
			}
		}
		return null;
	}
	static ArrayList<Course> getCoursesByKeyword(String keyword)
	{
		ArrayList<Course> answer= new ArrayList<>();
		int i;
		for(i=0;i<CourseList.getList().size();i++)
		{
			if(CourseList.getList().get(i).name.toUpperCase().contains(keyword.toUpperCase()))
			{
				answer.add(CourseList.getList().get(i));
			}
		}
		return answer;
	}
	static ArrayList<Course> getCoursesByTID(int TeacherId)
	{
		ArrayList<Course> answer= new ArrayList<>();
		int i;
		for(i=0;i<CourseList.getList().size();i++)
		{
			ArrayList<Integer> finding=CourseList.getList().get(i).TeacherId;
			int j;
			for(j=0;j<finding.size();j++)
			{
				if(finding.get(j)==TeacherId)
				{
					answer.add(CourseList.getList().get(i));
					break;
				}
			}
		}
		return answer;
	}
	static ArrayList<Course> getCoursesBySID(int StudentId)
	{
		ArrayList<Course> answer= new ArrayList<>();
		int i;
		for(i=0;i<CourseList.getList().size();i++)
		{
			ArrayList<Integer> finding=CourseList.getList().get(i).StudentId;
			int j;
			for(j=0;j<finding.size();j++)
			{
				if(finding.get(j)==StudentId)
				{
					answer.add(CourseList.getList().get(i));
					break;
				}
			}
		}
		return answer;
	}
	static void addCourse(Course a)
	{
		if(CourseList.getCourseById(a.cid)==null&&a.capacity>=0)
		{
			CourseList.getList().add(a);
		}
	}
	static void changeCourse(Course a)
	{
		if(a.capacity>=0)
		{
			int i;
			for(i=0;i<getList().size();i++)
			{
				if(getList().get(i).cid.compareTo(a.cid)==0)
				{
					getList().set(i,a);
					break;
				}
			}
		}
	}
   	static void changeCid(String old,String novel)
   	{
   		if(CourseList.getCourseById(novel)==null)
		{
			int i;
			for(i=0;i<getList().size();i++)
			{
				if(getList().get(i).cid.compareTo(old)==0)
				{
					Course b=new Course();
					b.cid=novel;
					b.name=getList().get(i).name;
					b.capacity=getList().get(i).capacity;
					b.TeacherId=getList().get(i).TeacherId;
					getList().set(i,b);
					break;
				}
			}
		}
   	}
   	static ArrayList<Integer> isTeacherList(String teachers)
   	{
   		ArrayList<Integer> ti= new ArrayList<>();
   		if(teachers.length()==2&&teachers.charAt(0)=='['&&teachers.charAt(1)==']')
   		{
   			return ti;
   		}
		int i;
		String tea="";
		for(i=1;i<teachers.length();i++)
		{
			if(teachers.charAt(i)==']')
			{
				if(PersonList.isTID(tea))
				{
					return null;
				}
				int te=Integer.parseInt(tea);
				int j;
				for(j=0;j<ti.size();j++)
				{
					if(ti.get(j)==te)
					{
						return null;
					}
				}
				ti.add(te);
				Collections.sort(ti);
				return ti;
			}
			else if(teachers.charAt(i)==',')
			{
				if(PersonList.isTID(tea))
				{
					return null;
				}
				int te=Integer.parseInt(tea);
				int j;
				for(j=0;j<ti.size();j++)
				{
					if(ti.get(j)==te)
					{
						return null;
					}
				}
				ti.add(te);
				tea="";
			}
			else
			{
				tea=tea+teachers.charAt(i);
			}
		}
		return null;
   	}
   	static boolean isCourseName(String name)
   	{
   		int i;
   		for(i=0;i<name.length();i++)
   		{
   			if(!((name.charAt(i)>='a'&&name.charAt(i)<='z')||(name.charAt(i)>='A'&&name.charAt(i)<='Z')||(name.charAt(i)>='0'&&name.charAt(i)<='9')))
   			{
   				return true;
   			}
   		}
   		return false;
   	}
   	static boolean isCourseId(String cid)
   	{
   		if(cid.charAt(0)!='B'&&cid.charAt(0)!='b')
   		{
   			return true;
   		}
   		if(cid.charAt(1)!='H'&&cid.charAt(1)!='h')
   		{
   			return true;
   		}
   		if(cid.length()!=10)
   		{
   			return true;
   		}
   		int i;
   		for(i=2;i<10;i++)
   		{
   			if(cid.charAt(i)<'0'||cid.charAt(i)>'9')
   			{
   				return true;
   			}
   		}
   		return false;
   	}
   	static Time isTime(String Time)
   	{
   		if(Time.charAt(0)!='['||Time.charAt(1)<'0'||Time.charAt(1)>'9')
   		{
   			return null;
   		}
   		int x=Time.charAt(1)-'0';
   		int i;
   		for(i=2;i<Time.length()&&Time.charAt(i)!='-';i++)
   		{
   			if(Time.charAt(i)<'0'||Time.charAt(i)>'9')
   	   		{
   	   			return null;
   	   		}
   			x*=10;
   			x+=(Time.charAt(i)-'0');
   		}
   		if(x<1||x>18)
   		{
   			return null;
   		}
   		if(i+1>=Time.length()||Time.charAt(i)!='-'||Time.charAt(i+1)<'0'||Time.charAt(i+1)>'9')
   		{
   			return null;
   		}
   		i++;
   		int y=Time.charAt(i)-'0';
   		i++;
   		for(;i<Time.length()&&Time.charAt(i)!=']';i++)
   		{
   			if(Time.charAt(i)<'0'||Time.charAt(i)>'9')
   	   		{
   	   			return null;
   	   		}
   			y*=10;
   			y+=(Time.charAt(i)-'0');
   		}
   		if(y<1||y>18||x>y)
   		{
   			return null;
   		}
   		if(i+1>=Time.length()||Time.charAt(i)!=']'||Time.charAt(i+1)<'0'||Time.charAt(i+1)>'9')
   		{
   			return null;
   		}
   		i++;
   		int n=Time.charAt(i)-'0';
   		i++;
   		if(n<1||n>7)
   		{
   			return null;
   		}
   		if(i+1>=Time.length()||Time.charAt(i)!=','||Time.charAt(i+1)<'0'||Time.charAt(i+1)>'9')
   		{
   			return null;
   		}
   		i++;
   		int m=Time.charAt(i)-'0';
   		i++;
   		for(;i<Time.length();i++)
   		{
   			if(Time.charAt(i)<'0'||Time.charAt(i)>'9')
   	   		{
   	   			return null;
   	   		}
   			m*=10;
   			m+=(Time.charAt(i)-'0');
   		}
   		if(m<1||m>10||i!=Time.length())
   		{
   			return null;
   		}
   		return new Time(x,y,n,m);
   	}
   	static boolean timeConflict(Time time1,Time time2)
   	{
		return time1.weekday == time2.weekday && time1.daytime == time2.daytime && time2.weekend >= time1.weekStart && time1.weekend >= time2.weekStart;
	}
   	static boolean timeExist(int sid,Time time)
   	{
		ArrayList<Course> choice=CourseList.getCoursesBySID(sid);
		int i;
		for(i=0;i<choice.size();i++)
		{
			if(timeConflict(choice.get(i).time,time))
			{
				return true;
			}
		}
   		return false;
   	}
   	static void dropout(int sid)
   	{
		int i;
		for(i=0;i<CourseList.getList().size();i++)
		{
			ArrayList<Integer> finding=CourseList.getList().get(i).StudentId;
			int j;
			for(j=0;j<finding.size();j++)
			{
				if(finding.get(j)==sid)
				{
					CourseList.getList().get(i).StudentId.remove(j);
					break;
				}
			}
		}
   	}
}
