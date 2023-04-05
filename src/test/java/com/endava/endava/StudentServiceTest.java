package com.endava.endava;

import com.endava.endava.model.Student;
import com.endava.endava.repository.StudentRepository;
import com.endava.endava.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }

    @Test
    void getStudentsByName_shouldReturnList() {
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("John", "john@example.com", "Address 1"));
        expected.add(new Student("John", "john2@example.com", "Address 2"));
        when(studentRepository.findByName("John")).thenReturn(expected);

        List<Student> actual = studentService.getStudentsByName("John");

        assertEquals(expected, actual);
    }

    @Test
    void getAllStudents_shouldReturnList() {
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("John", "john@example.com", "Address 1"));
        expected.add(new Student("Jane", "jane@example.com", "Address 2"));
        when(studentRepository.findAll()).thenReturn(expected);

        List<Student> actual = studentService.getAllStudents();

        assertEquals(expected, actual);
    }

    @Test
    void addStudent_shouldSaveStudent() {
        Student student = new Student("John", "john@example.com", "Address 1");
        studentService.addStudent(student);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void findStudent_shouldReturnOptional() {
        Student student = new Student("John", "john@example.com", "Address 1");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> actual = studentService.findStudent(1L);

        assertEquals(student, actual.get());
    }

    @Test
    void updateStudent_shouldSaveStudent() {
        Student student = new Student("John", "john@example.com", "Address 1");
        studentService.updateStudent(student);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void removeStudent_shouldDeleteStudent() {
        Student student = new Student("John", "john@example.com", "Address 1");
        studentService.removeStudent(student);

        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    void getAllStudentsByFaculty_shouldReturnList() {
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("John", "john@example.com", "Address 1"));
        expected.add(new Student("Jane", "jane@example.com", "Address 2"));
        when(studentRepository.findByFaculty(Student.Faculty.Engineering)).thenReturn(expected);

        List<Student> actual = studentService.getAllStudentsByFaculty(Student.Faculty.Engineering);

        assertEquals(expected, actual);
    }
}
