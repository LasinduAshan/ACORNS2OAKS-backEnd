package lk.wixis360.website.service;


import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.dto.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    public void saveCourse(CourceDTO dto, MultipartFile file,String cid);
    public void updateCourse(CourceDTO dto);
    public CourceDTO searchCourse(String id);
    public void deleteCourse(String id);
    public List<CourceDTO> getAllCourses();
    public List<CourceDTO> getAllCoursesByCategory(String category);
    public List<CourceDTO> getAllCoursesByStudent(String email);
    public List<CourceDTO> getAllCoursesBySearch(String value);
    public List<CourceDTO> getAllCoursesByTutor(String email);
}
