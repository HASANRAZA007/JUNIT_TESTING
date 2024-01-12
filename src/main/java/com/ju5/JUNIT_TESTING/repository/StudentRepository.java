package com.ju5.JUNIT_TESTING.repository;

import com.ju5.JUNIT_TESTING.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
