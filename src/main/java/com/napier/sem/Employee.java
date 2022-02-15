package com.napier.sem;

/**
 * Represents an employee
 */
public class Employee
{
    /**
     * Employee number
     */
    private int empNo;

    /**
     * Employee's first name
     */
    private String firstName;

    /**
     * Employee's last name
     */
    private String lastName;

    /**
     * Employee's job title
     */
    private String title;

    /**
     * Employee's salary
     */
    private int salary;

    /**
     * Employee's current department
     */
    private String deptName;

    /**
     * Employee's manager
     */
    private String manager;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
