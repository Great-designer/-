package management;

import java.util.ArrayList;

public class PersonList
{
	private static PersonList pp;
	private static ArrayList<Student> StudentList= new ArrayList<>();
	private static ArrayList<Teacher> TeacherList= new ArrayList<>();
	public static ArrayList<Student> getStudentList()
	{
		return StudentList;
	}
	public static ArrayList<Teacher> getTeacherList()
	{
		return TeacherList;
	}
	private PersonList(){}
	static IDNum idInTeacher(String ID)
	{
		int i;
		for(i=0;i<PersonList.getTeacherList().size();i++)
		{
			if(PersonList.getTeacherList().get(i).getId().toString().toUpperCase().compareTo(ID.toUpperCase())==0)
			{
				return PersonList.getTeacherList().get(i).getId();
			}
		}
		return null;
	}
	static IDNum idInStudent(String ID)
	{
		int i;
		for(i=0;i<PersonList.getStudentList().size();i++)
		{
			if(PersonList.getStudentList().get(i).getId().toString().toUpperCase().compareTo(ID.toUpperCase())==0)
			{
				return PersonList.getStudentList().get(i).getId();
			}
		}
		return null;
	}
	static IDNum getPersonId(IDNum id)
	{
		int i;
		for(i=0;i<PersonList.getStudentList().size();i++)
		{
			if(PersonList.getStudentList().get(i).getId().toString().compareTo(id.toString())==0)
			{
				return PersonList.getStudentList().get(i).getId();
			}
		}
		for(i=0;i<PersonList.getTeacherList().size();i++)
		{
			if(PersonList.getTeacherList().get(i).getId().toString().compareTo(id.toString())==0)
			{
				return PersonList.getTeacherList().get(i).getId();
			}
		}
		return null;
	}
	static Student getStudentId(int StudentId)
	{
		int i;
		for(i=0;i<PersonList.getStudentList().size();i++)
		{
			if(PersonList.getStudentList().get(i).StudentId==StudentId)
			{
				return PersonList.getStudentList().get(i);
			}
		}
		return null;
	}
	static Teacher getTeacherId(int TeacherId)
	{
		int i;
		for(i=0;i<PersonList.getTeacherList().size();i++)
		{
			if(PersonList.getTeacherList().get(i).TeacherId==TeacherId)
			{
				return PersonList.getTeacherList().get(i);
			}
		}
		return null;
	}
	static void addStudent(Student a)
	{
		if(PersonList.getStudentId(a.StudentId)==null&&PersonList.getPersonId(a.getId())==null)
		{
			PersonList.getStudentList().add(a);
		}
	}
	static void addTeacher(Teacher a)
	{
		if(PersonList.getTeacherId(a.TeacherId)==null&&PersonList.getPersonId(a.getId())==null)
		{
			PersonList.getTeacherList().add(a);
		}
	}
	static void changePassword(int id,String password)
	{
		int i;
		for(i=0;i<PersonList.getStudentList().size();i++)
		{
			if(PersonList.getStudentList().get(i).StudentId==id)
			{
				Student b=PersonList.getStudentList().get(i);
				b.password=password;
				getStudentList().set(i,b);
				break;
			}
		}
		for(i=0;i<PersonList.getTeacherList().size();i++)
		{
			if(PersonList.getTeacherList().get(i).TeacherId==id)
			{
				Teacher b=PersonList.getTeacherList().get(i);
				b.password=password;
				getTeacherList().set(i,b);
				break;
			}
		}
	}
	static boolean isTID(String person)
	{
		if(person.length()!=5)
		{
			return true;
		}
		int i;
		for(i=0;i<5;i++)
		{
			char temp=person.charAt(i);
			if(temp<'0'||temp>'9')
			{
				return true;
			}
		}
		return false;
	}
	static boolean isSID(String person)
	{
		if(person.length()!=8)
		{
			return false;
		}
		int i;
		for(i=0;i<5;i++)
		{
			char temp=person.charAt(i);
			if(temp<'0'||temp>'9')
			{
				return false;
			}
		}
		return true;
	}
	static boolean isPassword(String password)
	{
		if(password.length()<6||password.length()>18)
		{
			return false;
		}
		int Upper=0;
		int Lower=0;
		int Number=0;
		int Others=0;
		int i;
		for(i=0;i<password.length();i++)
		{
			char opp=password.charAt(i);
			if(opp<33||opp>126)
			{
				return false;
			}
			if(opp>='A'&&opp<='Z')
			{
				Upper=1;
			}
			else if(opp>='a'&&opp<='z')
			{
				Lower=1;
			}
			else if(opp>='0'&&opp<='9')
			{
				Number=1;
			}
			else
			{
				Others=1;
			}
		}
		return Upper + Lower + Number + Others >= 2;
	}
	static boolean isName(String name)
	{
		int i;
		for(i=0;i<name.length();i++)
		{
			char temp=name.charAt(i);
			if(temp<'A'||(temp>'Z'&&temp<'a')||temp>'z')
			{
				return true;
			}
		}
		return false;
	}
	static void dropout(int sid)
   	{
		int i;
		for(i=0;i<StudentList.size();i++)
		{
			if(StudentList.get(i).StudentId==sid)
			{
				StudentList.remove(i);
				break;
			}
		}
   	}
}
