package management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

class ErrorException extends Exception
{
	final String message;
	ErrorException(String mess)
	{
		message="Error:"+mess;
	}
	String warnMess()
	{
		return message;
	}
}

class Ret
{
	void illegal(String order)
	{
		try
		{
			throw new ErrorException(order+" illegal.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void exist(String order)
	{
		try
		{
			throw new ErrorException(order+" does not exist.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void exists(String order)
	{
		try
		{
			throw new ErrorException(order+" exists.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void selected(String order)
	{
		try
		{
			throw new ErrorException(order+" selected.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void fail()
	{
		try
		{
			throw new ErrorException("Update fail.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void conflict()
	{
		try
		{
			throw new ErrorException("Course time conflict.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void full()
	{
		try
		{
			throw new ErrorException("The course is full.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void login()
	{
		try
		{
			throw new ErrorException("Login Error. Your ID or password maybe wrong.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void password()
	{
		try
		{
			throw new ErrorException("The password you entered must be the same as the former one.");
		}
		catch(ErrorException e)
		{
			System.out.println(e.warnMess());
		}
	}
	void success(String order)
	{
		System.out.println(order+" success.");
	}
	void successful()
	{
		System.out.println("Drop out successful.");
	}
	void successfully(String order)
	{
		System.out.println(order+" successfully.");
	}
}

class NLQ
{
	void printNLQ(ArrayList<?> example,int page,int num,String order,Scanner sc,Ret re)
	{
		if(example.size()==0)
		{
			re.exist(order);
			return;
		}
		int i;
		System.out.println("Page:"+page);
		for(i=0;i<num;i++)
		{
			int j=i+(page-1)*num+1;
			if(j>example.size())
			{
			   	break;
			}
			System.out.println((i+1)+"."+example.get(j-1).toString());
		}
		System.out.println("n-next page, l-last page, q-quit");
		String pmt;
		label:
		while((pmt=sc.nextLine())!=null)
		{
			switch (pmt)
			{
				case "q":
					break label;
				case "n":
					page++;
					if (((page - 1) * num) >= example.size())
					{
						re.exist(order);
						break label;
					}
					System.out.println("Page:" + page);
					for (i = 0; i < num; i++)
					{
						int j = i + (page - 1) * num + 1;
						if (j > example.size())
						{
							break;
						}
						System.out.println((i + 1) + "." + example.get(j - 1).toString());
					}
					System.out.println("n-next page, l-last page, q-quit");
					break;
				case "l":
					page--;
					if (page <= 0)
					{
						re.exist(order);
						break label;
					}
					System.out.println("Page:" + page);
					for (i = 0; i < num; i++)
					{
						int j = i + (page - 1) * num + 1;
						if (j > example.size())
						{
							break;
						}
						System.out.println(j + "." + example.get(j - 1).toString());
					}
					System.out.println("n-next page, l-last page, q-quit");
					break;
				default:
					re.illegal("Input");
					break label;
			}
		}
	}
}

class Stu implements Comparable<Stu>
{
	int StudentId;
	String name;
	@Override
	public int compareTo(Stu o)
	{
		return this.StudentId-o.StudentId;
	}
	public String toString()
   	{
		return String.format("%08d",StudentId)+","+ this.name;
   	}
}

class Legal
{
	boolean isInt(String str)
	{
		char begin=str.charAt(0);
		if((begin<'0'||begin>'9')&&begin!='-')
		{
			return false;
		}
		int i;
		for(i=1;i<str.length();i++)
		{
			if(str.charAt(i)<'0'||str.charAt(i)>'9')
			{
				return false;
			}
		}
		return true;
	}
}

public class Test
{
	public static void main(String[] args)
   	{
		int condition=0;
		int tempStudent=-1;
		int tempTeacher=-1;
		Scanner sc=new Scanner(System.in);
		String sss;
		Ret re=new Ret();
		while((sss = sc.nextLine()) != null)
		{
			String[] in = sss.split(" ");
			if(condition==0)
			{
				if(in.length==0)
				{
					re.illegal("Input");
					continue;
				}
				if(in[0].equals("SUDO"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					condition=1;
				}
				else if(in[0].equals("login"))
   				{
					if(in.length!=4)
					{
						re.illegal("Input");
						continue;
					}
   					if(in[1].equals("-t"))
   					{
   						String id=in[2];
   						String password=in[3];
   						tempTeacher=-1;
   						int i;
   						for(i=0; i< PersonList.getTeacherList().size(); i++)
   						{
   							if(PersonList.getTeacherList().get(i).getId().toString().toUpperCase().compareTo(id.toUpperCase())==0&& PersonList.getTeacherList().get(i).password.compareTo(password)==0)
   							{
   								tempTeacher=i;
   								break;
   							}
   						}
   						if(tempTeacher==-1)
   						{
   							re.login();
						}
   						else
   						{
   							re.success("Login");
   							condition=2;
						}
   					}
   					else if(in[1].equals("-s"))
   	   				{
   						String id=in[2];
   						String password=in[3];
   						tempStudent=-1;
   						int i;
   						for(i=0; i< PersonList.getStudentList().size(); i++)
   						{
   							if(PersonList.getStudentList().get(i).getId().toString().toUpperCase().compareTo(id.toUpperCase())==0&& PersonList.getStudentList().get(i).password.compareTo(password)==0)
   							{
   								tempStudent=i;
   								break;
   							}
   						}
   						if(tempStudent==-1)
   						{
   							re.login();
						}
   						else
   						{
   							re.success("Login");
   							condition=3;
						}
   	   				}
   				}
				else if(in[0].equals("QUIT"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					break;
   				}
				else if(sss.length()!=0)
				{
					re.illegal("Input");
				}
			}
			else if(condition==2)
			{
				if(in.length==0)
				{
					re.illegal("Input");
					continue;
				}
				Teacher temp= PersonList.getTeacherList().get(tempTeacher);
				if(in[0].equals("myc"))
				{
					if(in.length!=3)
					{
						re.illegal("Input");
						continue;
					}
					String n=in[1];
					String m=in[2];
					ArrayList<Course> b= CourseList.getCoursesByTID(temp.TeacherId);
					Collections.sort(b);
					Legal le=new Legal();
					if(!(le.isInt(n))&&(le.isInt(m)))
					{
						re.illegal("Input");
					}
					else
					{
						int page=Integer.parseInt(n);
						int num=Integer.parseInt(m);
						NLQ printer=new NLQ();
						printer.printNLQ(b,page,num,"Course",sc,re);
					}
				}
				else if(in[0].equals("clist"))
				{
					if(in.length!=4)
					{
						re.illegal("Input");
						continue;
					}
					String cid=in[1];
					String n=in[2];
					String m=in[3];
					Legal le=new Legal();
					if(CourseList.isCourseId(cid))
					{
						re.exist("Course");
					}
					else if(CourseList.getCourseById(cid)==null)
					{
						re.exist("Course");
					}
					else if(!(le.isInt(n))&&(le.isInt(m)))
					{
						re.illegal("Input");
					}
					else
					{
						int page=Integer.parseInt(n);
						int num=Integer.parseInt(m);
						ArrayList<Integer> si= Objects.requireNonNull(CourseList.getCourseById(cid)).StudentId;
						ArrayList<Stu> ss= new ArrayList<>();
						int i;
						for(i=0;i<si.size();i++)
						{
							Student ssr= PersonList.getStudentId(si.get(i));
							Stu rss=new Stu();
							rss.name= Objects.requireNonNull(ssr).getName();
							rss.StudentId=ssr.StudentId;
							ss.add(rss);
						}
						Collections.sort(ss);
						NLQ printer=new NLQ();
						printer.printNLQ(ss,page,num,"Record",sc,re);
					}
				}
				else if(in[0].equals("gc"))
	   			{
					if(in.length==1)
					{
						re.illegal("Input");
						continue;
					}
					switch (in[1])
					{
						case "-id":
						{
							if (in.length != 3)
							{
								re.illegal("Input");
								continue;
							}
							String second = in[2];
							if (CourseList.getCourseById(second) == null)
							{
								re.exist("Course");
							}
							else
							{
								System.out.println(Objects.requireNonNull(CourseList.getCourseById(second)).toString());
							}
							break;
						}
						case "-key":
						{
							if (in.length != 5)
							{
								re.illegal("Input");
								continue;
							}
							String second = in[2];
							String n = in[3];
							String m = in[4];
							Legal le = new Legal();
							if (CourseList.getCoursesByKeyword(second).size() == 0)
							{
								re.exist("Course");
							}
							else if (!(le.isInt(n)) && (le.isInt(m)))
							{
								re.illegal("Input");
							}
							else
							{
								ArrayList<Course> b = CourseList.getCoursesByKeyword(second);
								Collections.sort(b);
								int page = Integer.parseInt(n);
								int num = Integer.parseInt(m);
								NLQ printer = new NLQ();
								printer.printNLQ(b, page, num, "Course", sc, re);
							}
							break;
						}
						case "-all":
						{
							if (in.length != 4)
							{
								re.illegal("Input");
								continue;
							}
							String n = in[2];
							String m = in[3];
							Collections.sort(CourseList.getList());
							Legal le = new Legal();
							if (!(le.isInt(n)) && (le.isInt(m)))
							{
								re.illegal("Input");
							}
							else
							{
								int page = Integer.parseInt(n);
								int num = Integer.parseInt(m);
								NLQ printer = new NLQ();
								printer.printNLQ(CourseList.getList(), page, num, "Course", sc, re);
							}
							break;
						}
						default:
							re.illegal("Input");
							break;
					}
	   			}
				else if(in[0].equals("chgpw"))
   				{
					if(in.length!=3)
					{
						re.illegal("Input");
						continue;
					}
					String password=in[1];
					String password2=in[2];
					if(PersonList.isPassword(password))
					{
						re.illegal("Password");
						continue;
					}
					if(password.compareTo(password2)!=0)
					{
						re.password();
						continue;
					}
					PersonList.changePassword(temp.TeacherId,password);
					re.successfully("Password changed");
				}
				else if(in[0].equals("myinfo"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					System.out.println(temp.toString());
				}
				else if(in[0].equals("back"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					condition=0;
				}
				else if(in[0].equals("QUIT"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					break;
   				}
				else if(sss.length()!=0)
				{
					re.illegal("Input");
				}
			}
			else if(condition==3)
			{
				if(in.length==0)
				{
					re.illegal("Input");
					continue;
				}
				Student temp= PersonList.getStudentList().get(tempStudent);
				if(in[0].equals("DROPOUT"))
				{
					if(in.length!=3)
					{
						re.illegal("Input");
						continue;
					}
					String password1=in[1];
					String password2=in[2];
					if(password1.compareTo(password2)!=0)
					{
						re.password();
					}
					else if(password1.compareTo(temp.password)!=0)
					{
						re.illegal("Password");
					}
					else
					{
						CourseList.dropout(temp.StudentId);
						PersonList.dropout(temp.StudentId);
						re.successfully("Congratulations, drop out");
						condition=0;
					}
				}
				else if(in[0].equals("getc"))
				{
					if(in.length!=2)
					{
						re.illegal("Input");
						continue;
					}
					String cid=in[1];
					Course cgg= CourseList.getCourseById(cid);
					if(cgg==null)
					{
						re.exist("Course");
					}
					else if(cgg.inStudent(temp.StudentId)!=-1)
					{
						re.selected("The course has been"); 
					}
					else if(CourseList.timeExist(temp.StudentId,cgg.time))
					{
						re.conflict();
					}
					else if(cgg.capacity==cgg.StudentId.size())
					{
						re.full();
					}
					else
					{
						cgg.StudentId.add(temp.StudentId);
						CourseList.changeCourse(cgg);
						re.success("Course chosen");
					}
				}
				else if(in[0].equals("dropc"))
				{
					if(in.length!=2)
					{
						re.illegal("Input");
						continue;
					}
					String cid=in[1];
					Course cgg= CourseList.getCourseById(cid);
					if(cgg==null)
					{
						re.exist("Course");
					}
					else if(cgg.inStudent(temp.StudentId)==-1)
					{
						re.selected("The course has not been"); 
					}
					else
					{
						cgg.StudentId.remove(cgg.inStudent(temp.StudentId));
						CourseList.changeCourse(cgg);
						re.successful();
					}
				}
				else if(in[0].equals("myc"))
				{
					if(in.length!=3)
					{
						re.illegal("Input");
						continue;
					}
					String n=in[1];
					String m=in[2];
					ArrayList<Course> b= CourseList.getCoursesBySID(temp.StudentId);
					Collections.sort(b);
					Legal le=new Legal();
					if(!(le.isInt(n))&&(le.isInt(m)))
					{
						re.illegal("Input");
					}
					else
					{
						int page=Integer.parseInt(n);
						int num=Integer.parseInt(m);
						NLQ printer=new NLQ();
						printer.printNLQ(b,page,num,"Course",sc,re);
					}
				}
				else if(in[0].equals("gc"))
	   			{
					if(in.length==1)
					{
						re.illegal("Input");
						continue;
					}
					switch (in[1])
					{
						case "-id":
						{
							if (in.length != 3)
							{
								re.illegal("Input");
								continue;
							}
							String second = in[2];
							if (CourseList.getCourseById(second) == null)
							{
								re.exist("Course");
							}
							else
							{
								System.out.println(Objects.requireNonNull(CourseList.getCourseById(second)).toString());
							}
							break;
						}
						case "-key":
						{
							if (in.length != 5)
							{
								re.illegal("Input");
								continue;
							}
							String second = in[2];
							String n = in[3];
							String m = in[4];
							Legal le = new Legal();
							if (CourseList.getCoursesByKeyword(second).size() == 0)
							{
								re.exist("Course");
							}
							else if (!(le.isInt(n)) && (le.isInt(m)))
							{
								re.illegal("Input");
							}
							else
							{
								ArrayList<Course> b = CourseList.getCoursesByKeyword(second);
								Collections.sort(b);
								int page = Integer.parseInt(n);
								int num = Integer.parseInt(m);
								NLQ printer = new NLQ();
								printer.printNLQ(b, page, num, "Course", sc, re);
							}
							break;
						}
						case "-all":
						{
							if (in.length != 4)
							{
								re.illegal("Input");
								continue;
							}
							String n = in[2];
							String m = in[3];
							Collections.sort(CourseList.getList());
							Legal le = new Legal();
							if (!(le.isInt(n)) && (le.isInt(m)))
							{
								re.illegal("Input");
							}
							else
							{
								int page = Integer.parseInt(n);
								int num = Integer.parseInt(m);
								NLQ printer = new NLQ();
								printer.printNLQ(CourseList.getList(), page, num, "Course", sc, re);
							}
							break;
						}
						default:
							re.illegal("Input");
							break;
					}
	   			}
				else if(in[0].equals("chgpw"))
   				{
					if(in.length!=3)
					{
						re.illegal("Input");
						continue;
					}
					String password=in[1];
					String password2=in[2];
					if(PersonList.isPassword(password))
					{
						re.illegal("Password");
						continue;
					}
					if(password.compareTo(password2)!=0)
					{
						re.password();
						continue;
					}
					PersonList.changePassword(temp.StudentId,password);
					re.successfully("Password changed");
				}
				else if(in[0].equals("myinfo"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					System.out.println(temp.toString());
				}
				else if(in[0].equals("back"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					condition=0;
				}
				else if(in[0].equals("QUIT"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					break;
   				}
				else if(sss.length()!=0)
				{
					re.illegal("Input");
				}
			}
			else
			{
				if(in.length==0)
				{
					re.illegal("Input");
					continue;
				}
				if(in[0].equals("clist"))
				{
					if(in.length!=4)
					{
						re.illegal("Input");
						continue;
					}
					String cid=in[1];
					String n=in[2];
					String m=in[3];
					Legal le=new Legal();
					if(CourseList.isCourseId(cid))
					{
						re.exist("Course");
					}
					else if(CourseList.getCourseById(cid)==null)
					{
						re.exist("Course");
					}
					else if(!(le.isInt(n))&&(le.isInt(m)))
					{
						re.illegal("Input");
					}
					else
					{
						int page=Integer.parseInt(n);
						int num=Integer.parseInt(m);
						ArrayList<Integer> si= Objects.requireNonNull(CourseList.getCourseById(cid)).StudentId;
						ArrayList<Stu> ss= new ArrayList<>();
						int i;
						for(i=0;i<si.size();i++)
						{
							Student ssr= PersonList.getStudentId(si.get(i));
							Stu rss=new Stu();
							rss.name= Objects.requireNonNull(ssr).getName();
							rss.StudentId=ssr.StudentId;
							ss.add(rss);
						}
						Collections.sort(ss);
						NLQ printer=new NLQ();
						printer.printNLQ(ss,page,num,"Record",sc,re);
					}
				}
				else if(in[0].equals("udc"))
	   			{
					if(in.length!=4)
					{
						re.illegal("Input");
						continue;
					}
	   				String cid=in[1];
					switch (in[2])
					{
						case "-n":
							String name = in[3];
							if (CourseList.getCourseById(cid) == null)
							{
								re.exist("Course");
							}
							else if (CourseList.isCourseName(name))
							{
								re.fail();
							}
							else
							{
								int i;
								for (i = 0; i < CourseList.getList().size(); i++)
								{
									if (CourseList.getList().get(i).cid.toUpperCase().compareTo(cid.toUpperCase()) == 0)
									{
										Course b = CourseList.getList().get(i);
										b.name = name;
										CourseList.getList().set(i, b);
									}
								}
								re.success("Update");
							}
							break;
						case "-c":
							String second = in[3];
							Legal le = new Legal();
							if (CourseList.getCourseById(cid) == null)
							{
								re.exist("Course");
							}
							else if (!le.isInt(second))
							{
								re.illegal("Input");
							}
							else
							{
								int capacity = Integer.parseInt(second);
								if (capacity < 0)
								{
									re.fail();
								}
								else
								{
									int i;
									for (i = 0; i < CourseList.getList().size(); i++)
									{
										if (CourseList.getList().get(i).cid.toUpperCase().compareTo(cid.toUpperCase()) == 0)
										{
											Course b = CourseList.getList().get(i);
											b.capacity = capacity;
											CourseList.getList().set(i, b);
										}
									}
									re.success("Update");
								}
							}
							break;
						case "-t":
							String teachers = in[3];
							ArrayList<Integer> ti = CourseList.isTeacherList(teachers);
							if (CourseList.getCourseById(cid) == null)
							{
								re.exist("Course");
							}
							else if (ti == null)
							{
								re.fail();
							}
							else
							{
								int i;
								for (i = 0; i < CourseList.getList().size(); i++)
								{
									if (CourseList.getList().get(i).cid.toUpperCase().compareTo(cid.toUpperCase()) == 0)
									{
										Course b = CourseList.getList().get(i);
										b.TeacherId = ti;
										CourseList.getList().set(i, b);
									}
								}
								re.success("Update");
							}
							break;
						default:
							re.illegal("Input");
							break;
					}
	   			}
				else if(in[0].equals("nc"))
	   			{
					if(in.length!=6)
					{
						re.illegal("Input");
						continue;
					}
	   				String cid=in[1];
	   				String name=in[2];
	   				String teachers=in[3];
	   				String cap=in[4];
	   				String time=in[5];
	   				Legal le=new Legal();
					ArrayList<Integer> ti= CourseList.isTeacherList(teachers);
					Time tm= CourseList.isTime(time);
					if(CourseList.getCourseById(cid)!=null)
	   				{
	   					re.exists("Course");
	   				}
					else if(!le.isInt(cap))
   					{
	   					re.illegal("Input");
	   				}
					else
					{
						int capacity=Integer.parseInt(cap);
						if(CourseList.isCourseId(cid))
						{
							re.illegal("Course add");
						}
						else if(CourseList.isCourseName(name))
						{
							re.illegal("Course add");
						}
						else if(ti==null)
						{
							re.illegal("Course add");
						}
						else if(capacity<0)
						{
							re.illegal("Course add");
						}
						else if(tm==null)
	   					{
		   					re.illegal("Course add");
		   				}
						else
						{
							Course p=new Course();
							p.cid=cid.toUpperCase();
							p.name=name;
							p.capacity=capacity;
							p.TeacherId=ti;
							p.time=tm;
							CourseList.getList().add(p);
						}
					}
	   			}
				else if(in[0].equals("np"))
				{
					if(in.length!=5)
					{
						re.illegal("Input");
						continue;
					}
   					if(in[1].equals("-t"))
   					{
   						String name=in[2];
   						String ID=in[3];
   						String person=in[4];
   						if(IDNum.checkIDNum(ID))
   						{
   							re.illegal("ID");
   							continue;
   						}
   						if(PersonList.idInTeacher(ID)!=null)
   						{
   							re.exists("ID");
   							continue;
   						}
   						if(PersonList.isName(name))
   						{
   							re.illegal("Name");
   							continue;
   						}
   						if(PersonList.isTID(person))
   						{
   							re.illegal("TID");
   							continue;
   						}
   						if(PersonList.getTeacherId(Integer.parseInt(person))!=null)
   						{
   							re.exists("TID");
   							continue;
   						}
   						Teacher rua= Teacher.newTeacher(name,ID,person);
   						PersonList.addTeacher(rua);
   						re.success("Add teacher");
					}
   					else if(in[1].equals("-s"))
   					{
   	   					String name=in[2];
   	   					String ID=in[3];
   	   					String person=in[4];
   	   					if(IDNum.checkIDNum(ID))
   	   					{
   	   						re.illegal("ID");
   	   						continue;
   	   					}
   	   					if(PersonList.idInStudent(ID)!=null)
						{
							re.exists("ID");
							continue;
						}
   	   					if(PersonList.isName(name))
						{
							re.illegal("Name");
							continue;
						}
   	   					if(!PersonList.isSID(person))
   	   					{
   	   						re.illegal("SID");
   	   						continue;
   	   					}
   	   					if(PersonList.getStudentId(Integer.parseInt(person))!=null)
						{
							re.exists("SID");
							continue;
						}
   	   					Student rua= Student.newStudent(name,ID,person);
   	   					PersonList.addStudent(rua);
   	   					re.success("Add student");
					}
				}
				else if(in[0].equals("back"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					condition=0;
				}
				else if(in[0].equals("QUIT"))
   				{
					if(in.length!=1)
					{
						re.illegal("Input");
						continue;
					}
					break;
   				}
				else if(sss.length()!=0)
				{
					re.illegal("Input");
				}
			}
		}
		sc.close();
   	}
}
