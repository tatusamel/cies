package com.yurtHomies.cies.repository;

import com.yurtHomies.cies.model.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
