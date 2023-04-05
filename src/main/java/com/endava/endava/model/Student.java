package com.endava.endava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@ToString
@Table(name = "students")
public class Student {
    public enum Faculty {
        Engineering,
        Law,
        Medicine,
        Business
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;


    @Min(value = 1, message = "Phone number is required")
    @Column(name = "phone_no")
    private long phoneNo;

    @Column(name = "age")
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private long age;

    @Column(name = "faculty")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Faculty is required")
    private Faculty faculty;

    public Student() {
    }

    public Student(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}