package com.ksundaysky.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @Transient
    private String password;
    @Column(name = "name")
    @Size(max = 20, message = " Zbyt długie imię")
    @NotEmpty(message = "*Please provide your name")
    private String name;
    @Column(name = "last_name")
    @Size(max=30,message = "Zbyt długie nazwisko - max 30 znaków")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;
    @Column(name = "experience")
    //@NotBlank(message = "Provide your experience")
    @Digits(integer=2, fraction=0, message = "provide valid experience")
    private String experience;
    @Column(name = "job_starting")
    //@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @NotNull(message = "provide valid date")
    private Date jobStartingDate;
    @Column(name = "active")
    private int active;
    @Column(name = "role_id")
    private int role_id;
    private String RoleName;

    @Column(name = "workdays_id")
    private int workdays_id;

    @javax.persistence.Transient
    String[] selectedWorkdays;

    public String[] getSelectedWorkdays() {
        return selectedWorkdays;
    }

    public void setSelectedWorkdays(String[] selectedWorkdays) {
        this.selectedWorkdays = selectedWorkdays;
    }



    public User() {
    }

    public User(int id, String email, String password, String name, String lastName, int active, int role_id, String experience,Date jobStartingDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.role_id = role_id;


        this.experience = experience;
        this.jobStartingDate = jobStartingDate;

    }
    public User(int id, String email, String password, String name, String lastName, int active, int role_id) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.role_id = role_id;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public String getRoleName() {
        return RoleName;
    }
    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Date getJobStartingDate() {
        return jobStartingDate;
    }

    public void setJobStartingDate(Date jobStartingDate) {
        this.jobStartingDate = jobStartingDate;
    }

    @javax.persistence.Transient
    public int getWorkdays_id() {
        return workdays_id;
    }
    @javax.persistence.Transient
    public void setWorkdays_id(int workdays_id) {
        this.workdays_id = workdays_id;
    }
}
