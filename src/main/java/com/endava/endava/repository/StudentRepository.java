package com.endava.endava.repository;

import com.endava.endava.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    List<Student> findByFaculty(Student.Faculty faculty);
}
