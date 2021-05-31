package lk.wixis360.website.service.impl;

import lk.wixis360.website.dto.StudentDTO;
import lk.wixis360.website.entity.Account;
import lk.wixis360.website.entity.Student;
import lk.wixis360.website.repo.AccountRepo;
import lk.wixis360.website.repo.StudentInterestRepo;
import lk.wixis360.website.repo.StudentRepo;
import lk.wixis360.website.service.StudentInterestService;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo repo;

    @Autowired
    StudentInterestService repo1;

    @Autowired
    AccountRepo arepo;

    @Autowired
    ModelMapper mapper;

    @Value("${student.image.upload.path}")
    String student_image_upload_path;


    @Override
    public void saveStudent(StudentDTO dto) {
        //if (!repo.existsById(dto.getId())){

        String studentID = UUID.randomUUID().toString();
        Student c = mapper.map(dto, Student.class);
        c.setId(studentID);
        c.setImgPath("photos/student/avater.png");

        repo.save(c);

        for (String name : dto.getModel1()) {
            repo1.saveStudentInterest(c,name);
        }


//        }else {
//            throw new RuntimeException("Student Allready exist");
//        }
    }

    @Override
    public void updateStudentFirstNameAndLastName(StudentDTO dto) {

        Student student = repo.findStudentByEmailOrContact(dto.getEmail(), dto.getEmail());


        if (repo.existsById(student.getId())) {
            //Student c = mapper.map(dto, Student.class);
            Student c = repo.getOne(student.getId());
            c.setFirstName(dto.getFirstName());
            c.setLastName(dto.getLastName());
            repo.save(c);
        } else {
            throw new RuntimeException("No such student for update..!");
        }
    }

    @Override
    public void updateStudentContact(StudentDTO dto) {

        Student s = repo.getOne(dto.getId());
        Account account = arepo.findAccountByEmail(s.getEmail());
        s.setContact(dto.getContact());
        account.setContact(dto.getContact());

        if (repo.existsById(s.getId())){
            arepo.save(account);
            repo.save(s);

        }else {
            throw new RuntimeException("No Student for the ID "+ dto.getId());
        }

    }

    @Override
    public void updateStudentEmail(StudentDTO dto) {

        Student s = repo.getOne(dto.getId());
        Account account = arepo.findAccountByEmail(s.getEmail());
        s.setEmail(dto.getEmail());
        account.setEmail(dto.getEmail());

        if (repo.existsById(s.getId())){
            arepo.save(account);
            repo.save(s);
        }else {
            throw new RuntimeException("No Student for the ID "+ dto.getId());
        }

    }

    @Override
    public void updateStudentDefaultImage(String email) {
        //Student student = repo.findStudentByEmailOrContact(email, email);

        if (repo.existsById(email)) {
            //Student c = mapper.map(dto, Student.class);
            Student c = repo.getOne(email);
            c.setImgPath("photos/student/avater.png");
            repo.save(c);
        } else {
            throw new RuntimeException("No such student for update..!");
        }
    }

    @Override
    public void updateStudentNewPhoto(MultipartFile file, String sid) {

        //String interrPath = "E:/Quasar Projects/wixis-360-new/ACORNS2OAKS/src/assets/photos/" + file.getOriginalFilename();

        String interrPath = student_image_upload_path+file.getOriginalFilename();
        //String interrPath = "E:/Quasar Projects/wixis-360-new/ACORNS2OAKS/public/photos/student/"+file.getOriginalFilename();
        //String imagePath =  "photos/student/"+file.getOriginalFilename();
        String imagePath =  student_image_upload_path+file.getOriginalFilename();

        //String imagePath =  "../assets/photos/"+file.getOriginalFilename();
        System.out.println("File name " + file.getOriginalFilename());
        System.out.println("image path " + imagePath);
        //System.out.println(dto.toString());

        try {
            file.transferTo(new File(interrPath));
            System.out.println("project path " + interrPath);

        } catch (IOException e) {

        }


        if (repo.existsById(sid)) {
            Student one = repo.getOne(sid);
            one.setImgPath(imagePath);
            repo.save(one);
        } else {
            throw new RuntimeException("No such tutor for Update image..!");
        }


    }

    @Override
    public StudentDTO searchStudent(String id) {
        Optional<Student> student =repo.findById(id);
        if (student.isPresent()){
            return mapper.map(student.get(), StudentDTO.class);
        }else {
            throw new RuntimeException("No Student for ID "+ id);
        }
    }

    @Override
    public void deleteStudent(String id) {

        Student student = repo.findStudentByEmailOrContact(id, id);

        if (repo.existsById(student.getId())){
            Account account = arepo.findAccountByEmail(student.getEmail());
            arepo.deleteById(account.getLoginID());
            repo.deleteById(student.getId());

        }else {
            throw new RuntimeException("No Student for the Delete ID "+ id);
        }
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<StudentDTO>>(){
        }.getType());
    }

    @Override
    public StudentDTO searchStudentByEmail(String email) {
        //Optional<Student> student = Optional.ofNullable(repo.findStudentByEmail(email));
        Optional<Student> student = Optional.ofNullable(repo.findStudentByEmailOrContact(email,email));
        if (student.isPresent()){
            return mapper.map(student.get(), StudentDTO.class);
        }else {
            throw new RuntimeException("No Student for ID "+ email);
        }
    }
}
