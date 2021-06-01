package lk.wixis360.website.service.impl;

import lk.wixis360.website.dto.SubjectDTO;
import lk.wixis360.website.dto.TutorDTO;
import lk.wixis360.website.entity.Tutor;
import lk.wixis360.website.repo.RegistrationRepo;
import lk.wixis360.website.repo.StudentReviewRepo;
import lk.wixis360.website.repo.SubjectRepo;
import lk.wixis360.website.repo.TutorRepo;
import lk.wixis360.website.service.SubjectService;
import lk.wixis360.website.service.TutorDesignationService;
import lk.wixis360.website.service.TutorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TutorServiceImpl implements TutorService {

    @Autowired
    TutorRepo repo;

    @Autowired
    TutorDesignationService repo1;

    @Autowired
    StudentReviewRepo studentReviewRepo;

    @Autowired
    RegistrationRepo registrationRepo;

    @Autowired
    ModelMapper mapper;

    @Value("${tutor.image.upload.path}")
    String tutor_image_upload_path;

    @Override
    public void saveTutor(TutorDTO dto) {
//        if (!repo.existsById(dto.getTid())){

        String tutorID = UUID.randomUUID().toString();
        Tutor c = mapper.map(dto, Tutor.class);
        c.setTid(tutorID);
        c.setDesignation(dto.getModel1());
        c.setImgPath("avater1.png");
        repo.save(c);

      /*  for (String designation : dto.getModel1()) {
            repo1.saveTutorDesignation(c,designation);
        }*/

//        }else {
//            throw new RuntimeException("Tutor All ready exist");
//        }
    }

    @Override
    public void updateTutorAbout(TutorDTO dto) {
        if (repo.existsById(dto.getTid())) {
            Tutor one = repo.getOne(dto.getTid());
            one.setAbout(dto.getAbout());
            repo.save(one);
        } else {
            throw new RuntimeException("No such tutor for update..!");
        }
    }

    @Override
    public void updateTutorPhoto(String tid) {
        if (repo.existsById(tid)) {
            Tutor one = repo.getOne(tid);
            one.setImgPath("avater1.png");
            repo.save(one);
        } else {
            throw new RuntimeException("No such tutor for delete image..!");
        }
    }

    @Override
    public void updateTutorImage(MultipartFile file, String tid) {

        //String interrPath = "E:/Quasar Projects/wixis-360-new/ACORNS2OAKS/src/assets/photos/" + file.getOriginalFilename();
        //String interrPath = "E:/Quasar Projects/wixis-360-new/ACORNS2OAKS/src/assets/"+file.getOriginalFilename();

        String interrPath = tutor_image_upload_path+file.getOriginalFilename();
        //String imagePath =  "photos/tutor/"+file.getOriginalFilename();
        //String imagePath =  tutor_image_upload_path+file.getOriginalFilename();
        String imagePath =  file.getOriginalFilename();

        //String imagePath =  "../assets/photos/"+file.getOriginalFilename();
        System.out.println("File name " + file.getOriginalFilename());
        System.out.println("image path " + imagePath);
        //System.out.println(dto.toString());

        try {
            file.transferTo(new File(interrPath));
            System.out.println("project path " + interrPath);

        } catch (IOException e) {

        }


        if (repo.existsById(tid)) {
            Tutor one = repo.getOne(tid);
            one.setImgPath(imagePath);
            repo.save(one);
        } else {
            throw new RuntimeException("No such tutor for Update image..!");
        }


    }

    @Override
    public TutorDTO searchTutor(String id) {

        Tutor t = repo.findTutorByEmailOrContact(id, id);

       // Optional<Tutor> student =repo.findById(id);
        if (t != null){
            //return new TutorDTO(t.getTid(),t.getFirstName(),t.getLastName(),t.getImgPath());
            return new TutorDTO(t.getTid(),t.getFirstName(),t.getLastName(),t.getImgPath(),t.getNameOfInstitution(),t.getExperience());
        }else {
            throw new RuntimeException("No tutor for ID "+ id);
        }
    }

    @Override
    public TutorDTO searchTutorByID(String id) {
        /*Optional<Tutor> t =repo.findById(id);
        if (t.isPresent()){
            return new TutorDTO(t.getTid(),t.getFirstName(),t.getLastName(),t.getImgPath(),t.getNameOfInstitution(),t.getExperience());
        }else {

        }*/

        int reviews = studentReviewRepo.findStudentReviewsByTutorID(id);

        int count = registrationRepo.findRegistrredCourseCountByTutorID(id);

        Optional<Tutor> tutor =repo.findById(id);
        Tutor t = repo.findTutorByTid(id);
        if (tutor.isPresent()){
            TutorDTO map = mapper.map(tutor.get(), TutorDTO.class);
            map.setImagePath(t.getImgPath());
            map.setReviewValue(reviews);
            map.setRagisteedCourseValue(count);
            return map;
        }else {
            throw new RuntimeException("No payment for ID "+ id);
        }
    }

    @Override
    public void deleteTutor(String id) {
        if (repo.existsById(id)){
            repo.deleteById(id);
        }else {
            throw new RuntimeException("No tutor for the Delete ID "+ id);
        }
    }

    @Override
    public List<TutorDTO> getAllTutors() {
        List<Tutor> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<TutorDTO>>(){
        }.getType());
    }
}
