package lk.wixis360.website.service;


import lk.wixis360.website.dto.AccountDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public void saveFile(MultipartFile files,String cid);
    //public String searchLoginUserRole(String email, String password);
}
