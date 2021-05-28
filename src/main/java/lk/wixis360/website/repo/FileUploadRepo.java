package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Account;
import lk.wixis360.website.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface FileUploadRepo extends JpaRepository<FileUpload, String> {

    //Account findLoginByEmailAndAndPassword(String email, String password);
    //Account findLoginByEmailOrContactOrPassword(String email, String contact, String password);
//    Account findLoginByEmailOrContactAndPassword(String email, String contact, String password);
//
//   /* @Query(value = "select * from account where contact=?1 or email=?2 and password=?3", nativeQuery = true)
//    Account getAccountByContactOrEmailAndPassword(String contact, String email, String password);*/
//
//    @Query(value = "select * from account where contact=?1 and password=?3 or email=?2 and password=?3", nativeQuery = true)
//    Account getAccountByContactOrEmailAndPassword(String contact, String email, String password);

    @Query(value = "select * from file_upload where courseid=?1", nativeQuery = true)
    ArrayList<FileUpload> findAllFileUploadByCourseID(String id);


    @Query(value = "delete from file_upload where courseid=?1", nativeQuery = true)
    void deleteFileUploadByCourseID(String id);


}
