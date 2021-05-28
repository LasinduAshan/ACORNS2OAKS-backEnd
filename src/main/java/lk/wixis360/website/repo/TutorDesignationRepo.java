package lk.wixis360.website.repo;

import lk.wixis360.website.entity.StudentInterest;
import lk.wixis360.website.entity.TutorDesignation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorDesignationRepo extends JpaRepository<TutorDesignation, String> {

}
