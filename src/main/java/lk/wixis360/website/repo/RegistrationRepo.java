package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Cource;
import lk.wixis360.website.entity.Registration;
import lk.wixis360.website.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepo extends JpaRepository<Registration, String> {

    @Query(value = "select * from registration where studentid=?1 and courseid=?2", nativeQuery = true)
    Registration getAllRegistrationByCourseIDAAndStudent_Id(String sid, String cid);

    @Query(value = "select * from registration where studentid=:myID", nativeQuery = true)
    Registration getRegistrationByStudent_Id(@Param("myID") String id);


    @Query(value = "select * from registration where studentid=?1 group by rid", nativeQuery = true)
    List <Registration> getAllRegistrationByStudent_Id(String sid);

    Registration findRegistrationByCource_CidAndStudent_Id(String cid, String sid);

    @Query(value = "select * from registration where courseid=?1", nativeQuery = true)
    Registration findRegistrationByCourseId(String id);

    @Query(value = "delete from registration where courseid=?1 and studentid=?2", nativeQuery = true)
    void deleteRegistrationByCourseID(String cid,String sid);

    @Query(value = "select count(reg_date) from registration where tutorid=?1", nativeQuery = true)
    int findRegistrredCourseCountByTutorID(String tid);

    @Query(value = "select * from registration where courseid=?1 and studentid=?2", nativeQuery = true)
    Registration findRegistrationByCourseIdAndStudentID(String cid,String sid);

    @Query(value = "delete from registration where rid=?1", nativeQuery = true)
    void deleteRegistrationByRID(int rid);

    //Registration deleteByCource(Cource cid);

}
