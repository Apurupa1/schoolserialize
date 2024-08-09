package com.example.school.model;
import java.io.Serial;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Serializable;

import org.springframework.jdbc.core.RowMapper;







    public class StudentRowMapper implements RowMapper<Student>,Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(
                    rs.getInt("studentId"),
                    rs.getString("studentName"),
                    rs.getString("gender"),
                    rs.getInt("standard"));
        }
    }

