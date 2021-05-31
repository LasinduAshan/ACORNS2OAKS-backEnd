package lk.wixis360.website.service.impl;

import lk.wixis360.website.controller.CourseController;
import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.dto.StudentDTO;
import lk.wixis360.website.entity.*;
import lk.wixis360.website.repo.*;
import lk.wixis360.website.service.CourseService;
import lk.wixis360.website.service.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    static String courseID;

    @Autowired
    CourseRepo repo;

    @Autowired
    StudentRepo srepo1;

    @Autowired
    TutorRepo tutorRepo;

    @Autowired
    FileUploadRepo fileRepo;

    @Autowired
    RegistrationRepo regRepo;

    @Autowired
    RatingRepo ratingRepo;

    @Autowired
    ModelMapper mapper;

    @Value("${image.upload.path}")
    String images_upload_path;

    @Override
    public void saveCourse(CourceDTO dto, MultipartFile file, String cid) {

        Tutor tutor = tutorRepo.findTutorByEmailOrContact(dto.getTutorEamilOrContact(), dto.getTutorEamilOrContact());

       // String interrPath = "E:/Quasar Projects/wixis-360-new/ACORNS2OAKS/src/assets/photos/" + file.getOriginalFilename();
        //String interrPath = "E:/Quasar Projects/wixis-360-new/ACORNS2OAKS/public/photos/course/" + file.getOriginalFilename();

        String interrPath = images_upload_path + file.getOriginalFilename();
        //String imagePath =  "../assets/photos/"+file.getOriginalFilename();
        //String imagePath =  "photos/course/"+file.getOriginalFilename();
        String imagePath =  images_upload_path+file.getOriginalFilename();

        System.out.println("File name " + file.getOriginalFilename());
        System.out.println("image path " + imagePath);
        System.out.println(dto.toString());

        try {
            file.transferTo(new File(interrPath));

        } catch (IOException e) {

        }

        //CourseServiceImpl.courseID = UUID.randomUUID().toString();


        Cource c = new Cource(cid,dto.getTitle(),dto.getCourseIncludes(),dto.getDeteails(),dto.getWhatWillLearn(),imagePath,tutor);
        repo.save(c);

        System.out.println("course Eke details save karana method eka.........");
        /*if (!repo.existsById(dto.getCid())){
            Cource c = mapper.map(dto, Cource.class);
            //Customer c = new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getSalary());
            repo.save(c);
        }else {
            throw new RuntimeException("Course All ready exist");
        }*/
    }

    @Override
    public void updateCourse(CourceDTO dto) {
        if (repo.existsById(dto.getCid())) {
            Cource c = mapper.map(dto, Cource.class);
            repo.save(c);
        } else {
            throw new RuntimeException("No such course for update..!");
        }
    }

    @Override
    public CourceDTO searchCourse(String id) {

        ArrayList<FileUpload> up = fileRepo.findAllFileUploadByCourseID(id);
        String [] paths = new String[up.size()];
        System.out.println("List eke size eka...............        "+ up.size());

        FileUpload a = new FileUpload();
        int i = 0;
        for (FileUpload f : up) {
            a = f;
            paths[i] = f.getVideoPath();
            i++;
            System.out.println(i);
        }
       /* String tutorFName= c.getTutor().getFirstName();
        String tutorLName= c.getTutor().getLastName();
        String tutorName = tutorFName+" "+tutorLName;*/
        System.out.println(paths.length);
       /* System.out.println(paths[0]);
        System.out.println(paths[1]);*/

        ArrayList<Rating> rating = ratingRepo.findRatingByCourseID(a.getCource().getCid());

        int rate;

        if (rating.size() == 0){
            rate = 0;
        }else {
            int sum = ratingRepo.findRatingSumByCourseID(a.getCource().getCid());
            int count = ratingRepo.findRatingCountByCourseID(a.getCource().getCid());
            rate = sum/count;
        }

       return new CourceDTO(a.getCource().getCid(),a.getCource().getTitle(),a.getCource().getDeteails(),a.getCource().getCourseIncludes(), paths,a.getCource().getImgPath(),rate);

       /* Optional<Cource> cource =repo.findById(id);
        Cource c = repo.getOne(id);
        if (cource.isPresent()){
            //return mapper.map(cource.get(), CourceDTO.class);
            return new CourceDTO(c.getCid(),c.getCname(),c.getDuration(),c.getPrice(),c.getDescription(),c.getRequirements(),c.getReview()
                    ,c.getImgPath(),c.getVideoPath(),c.getCategory(),c.getTutor().getTid()
            );
        }else {
            throw new RuntimeException("No Course for ID "+ id);
        }*/
//        return null;
    }

    @Override
    public void deleteCourse(String id) {
        if (repo.existsById(id)){
            //fileRepo.deleteFileUploadByCourseID(id);
            //regRepo.deleteRegistrationByCourseID(id);
            repo.deleteById(id);
        }else {
            throw new RuntimeException("No Course for the Delete ID "+ id);
        }
    }

    @Override
    public List<CourceDTO> getAllCourses() {
        List<Cource> all = repo.findAll();
//        return mapper.map(all, new TypeToken<List<CourceDTO>>(){
//        }.getType());

        List<CourceDTO> allCourses = new ArrayList<>();
        for (Cource c : all) {

            ArrayList<Rating> rating = ratingRepo.findRatingByCourseID(c.getCid());

            int rate;

            if (rating.size() == 0){
                rate = 0;
            }else {
                int sum = ratingRepo.findRatingSumByCourseID(c.getCid());
                int count = ratingRepo.findRatingCountByCourseID(c.getCid());
                rate = sum/count;
            }


            String tutorFName= c.getTutor().getFirstName();
            String tutorLName= c.getTutor().getLastName();
            String tutorName = tutorFName+" "+tutorLName;
            allCourses.add(new CourceDTO(c.getCid(),c.getTitle(),c.getDeteails()
                    , tutorName ,c.getImgPath(),c.getTutor().getTid(),rate
            ));
        }

        return allCourses;
    }

    @Override
    public List<CourceDTO> getAllCoursesByCategory(String category) {
       /* List<Cource> all = repo.findAllCourceByCategory(category);

        List<CourceDTO> allCourses = new ArrayList<>();
        for (Cource c : all) {
            String tutorFName= c.getTutor().getFirstName();
            String tutorLName= c.getTutor().getLastName();
            String tutorName = tutorFName+" "+tutorLName;
            allCourses.add(new CourceDTO(c.getCid(),c.getCname(),c.getDuration(),c.getPrice(),c.getDescription(),c.getRequirements(),c.getReview()
                   , tutorName ,c.getImgPath(),c.getVideoPath(),c.getCategory(),c.getTutor().getTid()
            ));
        }

        return allCourses;*/
       return null;
    }


    @Override
    public List<CourceDTO> getAllCoursesByStudent(String email) {
       // Student student = srepo1.findStudentByEmail(email);
        Student student = srepo1.findStudentByEmailOrContact(email, email);
        //Student allRegistration = regRepo.getAllRegistrationByCourseID(student.getId());
        Registration registrationByStudent_id = regRepo.getRegistrationByStudent_Id(student.getId());
       // System.out.println(allRegistration.toString());
       /* return mapper.map(allRegistration, new TypeToken<List<CourceDTO>>(){
        }.getType());*/
        return null;
    }

    @Override
    public List<CourceDTO> getAllCoursesBySearch(String value) {
        List<Cource> allCourceByCnameLike = repo.findAllCourceByTitleLike("%" + value + "%");
        List<CourceDTO> cou = new ArrayList<>();

        for (Cource c : allCourceByCnameLike){
            //reg.add(new RegistrationDTO(r.getCource().getCid(),r.getStudent().getEmail()));
            //cou.add(new CourceDTO(c.getCid(),c.getCname(),c.getDuration(),c.getImgPath()));

            ArrayList<Rating> rating = ratingRepo.findRatingByCourseID(c.getCid());

            int rate;

            if (rating.size() == 0){
                rate = 0;
            }else {
                int sum = ratingRepo.findRatingSumByCourseID(c.getCid());
                int count = ratingRepo.findRatingCountByCourseID(c.getCid());
                rate = sum/count;
            }

            String tutorFName= c.getTutor().getFirstName();
            String tutorLName= c.getTutor().getLastName();
            String tutorName = tutorFName+" "+tutorLName;
            cou.add(new CourceDTO(c.getCid(),c.getTitle(),c.getDeteails()
                    , tutorName ,c.getImgPath(),c.getTutor().getTid(),rate
            ));
        }
        return cou;
       //return null;
    }

    @Override
    public List<CourceDTO> getAllCoursesByTutor(String email) {

        Tutor tutor = tutorRepo.findTutorByEmailOrContact(email, email);
        List<Cource> allCource = repo.findAllCourceByTutor(tutor.getTid());
        //List<Cource> allCource = repo.findAll();
        List<CourceDTO> cou = new ArrayList<>();
        System.out.println();

        for (Cource c : allCource){
            //reg.add(new RegistrationDTO(r.getCource().getCid(),r.getStudent().getEmail()));
            //cou.add(new CourceDTO(c.getCid(),c.getCname(),c.getDuration(),c.getImgPath()));

            ArrayList<Rating> rating = ratingRepo.findRatingByCourseID(c.getCid());

            int rate;

            if (rating.size() == 0){
                rate = 0;
            }else {
                int sum = ratingRepo.findRatingSumByCourseID(c.getCid());
                int count = ratingRepo.findRatingCountByCourseID(c.getCid());
                rate = sum/count;
            }

            String tutorFName= c.getTutor().getFirstName();
            String tutorLName= c.getTutor().getLastName();
            String tutorName = tutorFName+" "+tutorLName;
            cou.add(new CourceDTO(c.getCid(),c.getTitle(),c.getDeteails()
                    , tutorName ,c.getTutor().getImgPath(),c.getTutor().getTid(),rate
            ));
        }

        System.out.println(cou.toString());

        return cou;

    }
}
