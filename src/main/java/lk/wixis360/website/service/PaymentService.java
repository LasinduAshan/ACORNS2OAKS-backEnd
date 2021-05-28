package lk.wixis360.website.service;


import lk.wixis360.website.dto.PaymentDTO;
import lk.wixis360.website.dto.TutorDTO;

import java.util.List;

public interface PaymentService {
    public void savePayment(PaymentDTO dto);
    public void updatePayment(PaymentDTO dto);
    public PaymentDTO searchPayment(String id);
    public void deletePayment(String id);
    public List<PaymentDTO> getAllPayments();

}
