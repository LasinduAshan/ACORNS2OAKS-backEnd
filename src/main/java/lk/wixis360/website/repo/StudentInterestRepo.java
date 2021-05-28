package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Account;
import lk.wixis360.website.entity.StudentInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentInterestRepo extends JpaRepository<StudentInterest, String> {

}
