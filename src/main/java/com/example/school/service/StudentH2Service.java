package com.example.school.service;
import java.util.*;

import com.example.school.model.StudentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentH2Service implements StudentRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        List<Student> plist = db.query("Select * from Student", new StudentRowMapper());
        ArrayList<Student> list = new ArrayList<>(plist);
        return list;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            Student student = db.queryForObject("SElECT * from Student where studentId=?", new StudentRowMapper(), studentId);
            return student;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student addStudent(Student student) {

        db.update("insert into Student(studentName, gender,standard) values (?, ?, ?)",student.getStudentName(), student.getGender(),student.getStandard());

        Student savedStudent = db.queryForObject("select * from Student where StudentName = ? and gender = ? and standard = ?",
                new StudentRowMapper(), student.getStudentName(), student.getGender(),student.getStandard());


        return savedStudent;
    }


    @Override
    public Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("Update Student set studentName=? where studentId=?", student.getStudentName(), studentId);

        }
        if (student.getGender() != null) {
            db.update("Update Student set gender=? where studentId=?", student.getGender(), studentId);

        }
        if (student.getStandard() != 0) {
            db.update("Update Student set standard=? where studentId=?", student.getStandard(), studentId);

        }

        return getStudentById(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("Delete from Student where studentId=?", studentId);

    }
}

