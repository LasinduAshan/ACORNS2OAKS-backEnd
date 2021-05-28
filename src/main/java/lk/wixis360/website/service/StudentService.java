package lk.wixis360.website.service;


import lk.wixis360.website.dto.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    public void saveStudent(StudentDTO dto);
    public void updateStudentFirstNameAndLastName(StudentDTO dto);
    public void updateStudentContact(StudentDTO dto);
    public void updateStudentEmail(StudentDTO dto);
    public void updateStudentDefaultImage(String email);
    public void updateStudentNewPhoto(MultipartFile file, String sid);
    public StudentDTO searchStudent(String id);
    public void deleteStudent(String id);
    public List<StudentDTO> getAllStudents();
    public StudentDTO searchStudentByEmail(String email);
}
