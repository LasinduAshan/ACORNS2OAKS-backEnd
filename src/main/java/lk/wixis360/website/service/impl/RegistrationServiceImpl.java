package lk.wixis360.website.service.impl;

import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.dto.RegistrationDTO;
import lk.wixis360.website.entity.*;
import lk.wixis360.website.repo.*;
import lk.wixis360.website.service.AccountService;
import lk.wixis360.website.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepo repo;

    @Autowired
    StudentRepo srepo;

    @Autowired
    RatingRepo ratingRepo;

    @Autowired
    PaymentRepo prepo;

    @Autowired
    CourseRepo crepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void registerStudent(RegistrationDTO dto) {

        //Student student = srepo.findStudentByEmail(dto.getEmail());
        Student student = srepo.findStudentByEmailOrContact(dto.getEmail(), dto.getEmail());
        Cource cource = crepo.getOne(dto.getCid());
        Registration registration = new Registration(UUID.randomUUID().toString(),new Date(),dto.getTutorID() ,student, cource);

        Registration r = repo.getAllRegistrationByCourseIDAAndStudent_Id(student.getId(), dto.getCid());

        if (r == null){
            repo.save(registration);
        }else {
            throw new RuntimeException("Student All ready registered by this course");
        }

    }

    @Override
    public List<CourceDTO> searchRegistrationDetail(String id) {
        //Student student = srepo.findStudentByEmail(id);
        Student student = srepo.findStudentByEmailOrContact(id,id);
        List<Registration> all = repo.getAllRegistrationByStudent_Id(student.getId());
        List<RegistrationDTO> reg = new ArrayList<>();
        List<CourceDTO> cou = new ArrayList<>();

        for (Registration r : all){
            //reg.add(new RegistrationDTO(r.getCource().getCid(),r.getStudent().getEmail()));

            ArrayList<Rating> rating = ratingRepo.findRatingByCourseID(r.getCource().getCid());

            int rate;

            if (rating.size() == 0){
                rate = 0;
            }else {
                int sum = ratingRepo.findRatingSumByCourseID(r.getCource().getCid());
                int count = ratingRepo.findRatingCountByCourseID(r.getCource().getCid());
                rate = sum/count;
            }

//            int sum = ratingRepo.findRatingSumByCourseID(r.getCource().getCid());
//            //System.out.println("Rating sum for courseID         ....."+sum);
//            int count = ratingRepo.findRatingCountByCourseID(r.getCource().getCid());
//            //System.out.println("Rating Count for courseID         ....."+count);


            cou.add(new CourceDTO(r.getCource().getCid(),r.getCource().getTitle(),r.getCource().getDeteails(),r.getCource().getTutor().getTid(),r.getCource().getImgPath(),rate));
            //cou.add(new CourceDTO(r.getCource().getCid(),r.getCource().getTitle(),r.getCource().getDeteails(),r.getCource().getTutor().getTid(),student.getImgPath(),rate));
        }

        return cou;

//       return null;

       /* return mapper.map(all, new TypeToken<List<RegistrationDTO>>(){
        }.getType());*/
    }

    @Override
    public void deleteRegisterCourse(String sid, String cid) {
        // course id eken registration eka arn eka delete krnna..................

        //System.out.println(id);

       // Registration r = repo.findRegistrationByCourseId(id);

//        if (repo.existsById(id)){
           // repo.deleteById(r.getRid());
//        }else {
//            throw new RuntimeException("No Student for the Delete ID "+ id);
//        }

       // repo.deleteRegistrationByCourseID(id);

        Student student = srepo.findStudentByEmailOrContact(sid, sid);

        Registration registration = repo.findRegistrationByCourseIdAndStudentID(cid, student.getId());
        System.out.println("select eka"+ registration.getRid());

        if (repo.existsById(registration.getRid())){
             repo.deleteById(registration.getRid());
        }else {
            throw new RuntimeException("No Student Register for the Delete ID "+ registration.getRid());
        }

        //repo.deleteRegistrationByCourseID(cid,student.getId());
        //repo.deleteRegistrationByRID(registration.getRid());
    }
}
