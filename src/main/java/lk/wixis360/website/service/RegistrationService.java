package lk.wixis360.website.service;


import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.dto.RegistrationDTO;

import java.util.List;

public interface RegistrationService {
    public void registerStudent(RegistrationDTO dto);
   // public void updateCourse(CourceDTO dto);
    //public List<RegistrationDTO> searchRegistrationDetail(String id);
    public List<CourceDTO> searchRegistrationDetail(String id);
    public void deleteRegisterCourse(String sid,String cid);
    /*public List<CourceDTO> getAllCourses();*/

}
