package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Cource;
import lk.wixis360.website.entity.Student;
import lk.wixis360.website.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<Cource, String> {

   // List<Cource> findAllCourceByCategory(String category);
    List<Cource> findAllCourceByTitleLike(String category);
    //List<Cource> findAllCourceByTutor(Tutor email);
   // List<Cource> findAllCourceByTutor_Tid(String id);
  @Query(value = "select * from cource where tutorid=?1", nativeQuery = true)
  List <Cource> findAllCourceByTutor(String id);

}
