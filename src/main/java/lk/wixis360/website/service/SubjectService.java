package lk.wixis360.website.service;


import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    public void saveSubject(SubjectDTO dto);
    public void updateSubject(SubjectDTO dto);
    public SubjectDTO searchSubject(String id);
    public void deleteSubject(String id);
    public List<SubjectDTO> getAllSubjects();

}
