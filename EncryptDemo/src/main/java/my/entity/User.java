package my.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import my.vo.UserVO;

public class User {
    private Integer id;
    private String name;
    private Double salary;
    private Boolean married;
    private Date birthday;
    public User(Integer id, String name, Double salary, Boolean married, Date birthday) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.married = married;
        this.birthday = birthday;
    }
    public UserVO toVO() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return new UserVO(id, name, salary, married, df.format(birthday));
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    public Boolean getMarried() {
        return married;
    }
    public void setMarried(Boolean married) {
        this.married = married;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", salary=" + salary + ", married=" + married + ", birthday="
                + birthday + "]";
    }
}
