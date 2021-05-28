package lk.wixis360.website.service.impl;

import lk.wixis360.website.dto.PaymentDTO;
import lk.wixis360.website.dto.SubjectDTO;
import lk.wixis360.website.entity.Cource;
import lk.wixis360.website.entity.Payment;
import lk.wixis360.website.entity.Registration;
import lk.wixis360.website.entity.Student;
import lk.wixis360.website.repo.CourseRepo;
import lk.wixis360.website.repo.PaymentRepo;
import lk.wixis360.website.repo.StudentRepo;
import lk.wixis360.website.repo.SubjectRepo;
import lk.wixis360.website.service.PaymentService;
import lk.wixis360.website.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepo repo;

    @Autowired
    StudentRepo srepo;

    @Autowired
    CourseRepo crepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void savePayment(PaymentDTO dto) {
        //if (!repo.existsById(dto.getPid())){
        Cource cource = crepo.getOne(dto.getCid());
        Student student = srepo.findStudentByEmail(dto.getEmail());
        //Registration registration = new Registration(new Date(), student, cource);

       // Payment c = new Payment(new Date(), dto.getAmount(), registration);

       // repo.save(c);
//        }else {
//            throw new RuntimeException("Payment All ready exist");
//        }
    }

    @Override
    public void updatePayment(PaymentDTO dto) {
       /* if (repo.existsById(dto.getPid())) {
            Payment c = mapper.map(dto, Payment.class);
            repo.save(c);
        } else {
            throw new RuntimeException("No such payment for update..!");
        }*/
    }

    @Override
    public PaymentDTO searchPayment(String id) {
        Optional<Payment> student =repo.findById(id);
        if (student.isPresent()){
            return mapper.map(student.get(), PaymentDTO.class);
        }else {
            throw new RuntimeException("No payment for ID "+ id);
        }
    }

    @Override
    public void deletePayment(String id) {
        if (repo.existsById(id)){
            repo.deleteById(id);
        }else {
            throw new RuntimeException("No Payment for the Delete ID "+ id);
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<PaymentDTO>>(){
        }.getType());
    }
}
