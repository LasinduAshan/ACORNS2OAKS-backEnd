package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepo extends JpaRepository<Tutor, String> {

    Tutor findTutorByEmailOrContact(String email, String email1);

    Tutor findTutorByTid(String id);

}
