package employeetable;

public class Employee {
	
	private String empId;
	private String name;
	private String designation;
	private String gender;
	private String company;
	private double salary;
	private String email;
	private String city;
	private Boolean validEmail;
		
	public Employee(String empId, String name, String designation, String gender , String company ,double salary, String email, String city,Boolean valid)
	{
		this.empId = empId;
		this.name = name;
		this.designation = designation;
		this.gender = gender;
		this.company = company;
		this.salary= salary;
		this.email = email;
		this.city = city;
		this.validEmail=valid;
	}
	
	public Employee(String empId, String name, String designation, String gender , String company ,double salary, String email, String city)
		{
		this.empId = empId;
		this.name = name;
		this.designation = designation;
		this.gender = gender;
		this.company = company;
		this.salary= salary;
		this.email = email;
		this.city = city;
		
	}
	//getter setter for providing and accessing the pojo class fields
	
	public Boolean getValidEmail() {
		return validEmail;
	}

	public void setValidEmail(Boolean validEmail) {
		this.validEmail = validEmail;
	}
	
	public String getEmpId()
	{
		return empId;
	}
	
	public void setEmpId(String empId)
	{
		this.empId = empId;
	}
	
	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	
	
	public String getDesignation()
	{
		return designation;
	}
	
	public void setDesignation(String designation)
	{
		this.designation= designation;
	}
	
	
	
	public String getGender()
	{
		return gender;
	}
	
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	public String getCompany()
	{
		return company;
	}
	
	public void setCompany(String company)
	{
		this.company= company;
	}
	
	
	
	public double getSalary()
	{
		return salary;
	}
	

	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	

	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email= email;
	}
	
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city= city;
	}
	
	//to print the object as string 
	 @Override
	    public String toString() {
	        return "Employee [empId=" + empId + ", name=" + name + ", designation=" + designation
	                + ", gender=" + gender + ", company=" + company + ", salary=" + salary
	                + ", email=" + email + ", city=" + city + "]";
	    }
	

}