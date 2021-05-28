package lk.wixis360.website.service;


import lk.wixis360.website.dto.SubjectDTO;
import lk.wixis360.website.dto.TutorDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TutorService {
    public void saveTutor(TutorDTO dto);
    public void updateTutorAbout(TutorDTO dto);
    public void updateTutorPhoto(String tid);
    public void updateTutorImage(MultipartFile file,String tid);
    public TutorDTO searchTutor(String id);
    public TutorDTO searchTutorByID(String id);
    public void deleteTutor(String id);
    public List<TutorDTO> getAllTutors();

}
