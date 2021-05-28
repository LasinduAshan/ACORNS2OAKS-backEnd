package lk.wixis360.website.controller;

import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.dto.PaymentDTO;
import lk.wixis360.website.dto.RegistrationDTO;
import lk.wixis360.website.dto.TutorDTO;
import lk.wixis360.website.service.PaymentService;
import lk.wixis360.website.service.RegistrationService;
import lk.wixis360.website.service.TutorService;
import lk.wixis360.website.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
@CrossOrigin
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    PaymentService paymentService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity registerStudent(@RequestBody RegistrationDTO dto){
        registrationService.registerStudent(dto);
       // paymentService.savePayment(new PaymentDTO());
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{cid}",params = {"sid"})
    public ResponseEntity deleteRegisteredCourse(@PathVariable String cid,@RequestParam String sid){
        System.out.println(sid);
        System.out.println(cid);
        registrationService.deleteRegisterCourse(sid,cid);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.OK);
    }
/*
    @PutMapping
    public ResponseEntity updateTutor(@RequestBody TutorDTO dto) {
        tutorService.updateTutor(dto);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }*/

    @GetMapping(params = {"id"})
    public ResponseEntity searchTutor(@RequestParam("id") String email){
        //List<RegistrationDTO> registrationDTO = registrationService.searchRegistrationDetail(email);
        List<CourceDTO> registrationDTO = registrationService.searchRegistrationDetail(email);
        StandardResponse success = new StandardResponse(200,"Success",registrationDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }

   /* @GetMapping
    public ResponseEntity getAllTutors(){
        List<TutorDTO> allTutors = tutorService.getAllTutors();
        StandardResponse success = new StandardResponse(200,"Success",allTutors);
        return new ResponseEntity(success, HttpStatus.OK);
    }*/
}
