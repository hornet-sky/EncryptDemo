package my.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import my.entity.User;

public class UserVO {
    private Integer id;
    private String name;
    private Double salary;
    private Boolean married;
    private String birthday;
    public UserVO(Integer id, String name, Double salary, Boolean married, String birthday) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.married = married;
        this.birthday = birthday;
    }
    public User toEntity() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return new User(id, name, salary, married, df.parse(birthday));
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
    public String getBirthday() {
        return birthday;
    }
    @Override
    public String toString() {
        return "UserVO [id=" + id + ", name=" + name + ", salary=" + salary + ", married=" + married + ", birthday="
                + birthday + "]";
    }
}
