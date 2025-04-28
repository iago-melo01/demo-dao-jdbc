package model.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {
    public static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        DateFormat dtf =  new SimpleDateFormat("dd/MM/yyyy");
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", BirthDate=" + dtf.format(BirthDate) +
                ", BaseSalary=" + BaseSalary +
                ", department=" + department.getName() +
                '}';
    }

    private int id;
    private String name;
    private String email;
    private Date BirthDate;
    private Double BaseSalary;
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Seller(int id, String name, String email, Date birthDate, Double baseSalary, Department department) {
        this();
        this.id = id;
    }
    public Seller( String name, String email, Date birthDate, Double baseSalary, Department department) {
        this.name = name;
        this.email = email;
        this.BirthDate = birthDate;
        this.BaseSalary = baseSalary;
        this.department = department;
    }

    public Seller(){

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return id == seller.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public Double getBaseSalary() {
        return BaseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        BaseSalary = baseSalary;
    }
}
