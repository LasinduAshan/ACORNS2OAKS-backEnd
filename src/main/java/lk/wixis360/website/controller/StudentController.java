package lk.wixis360.website.controller;

import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.StudentDTO;
import lk.wixis360.website.service.AccountService;
import lk.wixis360.website.service.StudentService;
import lk.wixis360.website.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    AccountService accountService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addStudent(@RequestBody StudentDTO dto){
        studentService.saveStudent(dto);
        accountService.saveAccount(new AccountDTO(dto.getEmail(),dto.getContact(),dto.getPassword(),"Student"));
        //accountService.saveAccount(new AccountDTO("dto.getEmail()","dto.getPassword()","Student"));
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteStudent(@RequestParam String id){
        studentService.deleteStudent(id);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateCStudent(@RequestBody StudentDTO dto) {
        studentService.updateStudentFirstNameAndLastName(dto);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateStudentDefaultImage(@PathVariable String id) {
        // default avater eka danne.......
        studentService.updateStudentDefaultImage(id);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @PutMapping(path = "/contact")
    public ResponseEntity updateStudentContact(@RequestBody StudentDTO dto) {
        // default avater eka danne.......
        studentService.updateStudentContact(dto);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @PutMapping(path = "/email")
    public ResponseEntity updateStudentEmail(@RequestBody StudentDTO dto) {
        // default avater eka danne.......
         studentService.updateStudentEmail(dto);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @PostMapping(path = "/update/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity updateTutorImage(@PathVariable String id, @RequestParam(value = "file[]") MultipartFile[] files, HttpServletRequest request) {
        for (MultipartFile file : files) {
            //System.out.println(file.getOriginalFilename());
            studentService.updateStudentNewPhoto(file,id);
        }
        //System.out.println(id);
        //tutorService.updateTutorPhoto(tutorID);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchStudent(@PathVariable String id){
        StudentDTO studentDTO = studentService.searchStudent(id);
        StandardResponse success = new StandardResponse(200,"Success",studentDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllStudents(){
        List<StudentDTO> allStudents = studentService.getAllStudents();
        StandardResponse success = new StandardResponse(200,"Success",allStudents);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity searchTutor(@RequestParam("id") String emailOrPhoneNO){
        StudentDTO studentDTO = studentService.searchStudentByEmail(emailOrPhoneNO);
        StandardResponse success = new StandardResponse(200,"Success",studentDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }


}
