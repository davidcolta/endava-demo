package com.endava.endava.controller;

import com.endava.endava.model.Student;
import com.endava.endava.repository.StudentRepository;
import com.endava.endava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("signup")
    public String showSignUpForm(Student student) {
        return "add-student";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @PostMapping("add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }
        studentService.addStudent(student);
        return "redirect:list";

    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = getStudent(id);
        model.addAttribute("student", student);
        return "update-student";
    }


    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }
        studentService.updateStudent(student);
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student student = getStudent(id);
        studentService.removeStudent(student);
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    private Student getStudent(@PathVariable("id") long id) {
        Student student;
        if (studentService.findStudent(id).isPresent()) {
            student = studentService.findStudent(id).get();
        } else {
            throw new IllegalArgumentException("Invalid Student ID: " + id);
        }
        return student;
    }

    @GetMapping("/{faculty}")
    public String getAllStudentsByFaculty(@PathVariable Student.Faculty faculty, Model model) {
        List<Student> students = studentService.getAllStudentsByFaculty(faculty);
        if (students.isEmpty()) {
            model.addAttribute("message", "No students found for faculty " + faculty);
        } else {
            model.addAttribute("students", students);
        }
        return "index";
    }


}
