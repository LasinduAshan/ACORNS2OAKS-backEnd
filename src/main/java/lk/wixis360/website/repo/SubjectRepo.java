package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Student;
import lk.wixis360.website.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject, String> {


}
