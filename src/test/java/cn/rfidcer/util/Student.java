package cn.rfidcer.util;

import java.util.Date;

public class Student  
{  
    private int id;  
    private String name;  
    private int age;  
    private Date birth;  
    
    private String address;
  
    public Student(int id, String name, String address)  
    {  
    	this.id = id;
    	this.name = name;
    	this.address = address;
    }  
  
    public Student(int id, String name, int age, Date birth)  
    {  
        this.id = id;  
        this.name = name;  
        this.age = age;  
        this.birth = birth;  
    }  
  
    public int getId()  
    {  
        return id;  
    }  
  
    public void setId(int id)  
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
  
    public int getAge()  
    {  
        return age;  
    }  
  
    public void setAge(int age)  
    {  
        this.age = age;  
    }  
  
    public Date getBirth()  
    {  
        return birth;  
    }  
  
    public void setBirth(Date birth)  
    {  
        this.birth = birth;  
    }

	/**
	 * 获取 #{bare_field_comment}
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}  
    
  
}  