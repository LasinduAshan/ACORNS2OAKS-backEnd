package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, String> {
    Student findStudentByEmail(String email);
    Student findStudentByEmailOrContact(String email, String email1);
}
