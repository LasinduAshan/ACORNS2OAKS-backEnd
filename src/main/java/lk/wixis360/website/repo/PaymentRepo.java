package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, String> {


}
