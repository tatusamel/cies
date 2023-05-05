package com.yurtHomies.cies.repository;

import com.yurtHomies.cies.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
