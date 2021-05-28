package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Account;
import lk.wixis360.website.entity.Cource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepo extends JpaRepository<Account, String> {

   // Account findAccountByEmailOrContact(String value);
    //Account findLoginByEmailOrContactOrPassword(String email, String contact, String password);
    Account findLoginByEmailOrContactAndPassword(String email, String contact, String password);

   /* @Query(value = "select * from account where contact=?1 or email=?2 and password=?3", nativeQuery = true)
    Account getAccountByContactOrEmailAndPassword(String contact, String email, String password);*/

    @Query(value = "select * from account where contact=?1 and password=?3 or email=?2 and password=?3", nativeQuery = true)
    Account getAccountByContactOrEmailAndPassword(String contact, String email, String password);

    @Query(value = "select * from account where contact=?1 or email=?2", nativeQuery = true)
    Account findAccountByEmailOrContact(String value1, String value2);

    Account findAccountByEmail(String value);

}
