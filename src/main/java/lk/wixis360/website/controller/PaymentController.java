package lk.wixis360.website.controller;

import lk.wixis360.website.dto.PaymentDTO;
import lk.wixis360.website.dto.RegistrationDTO;
import lk.wixis360.website.service.PaymentService;
import lk.wixis360.website.service.RegistrationService;
import lk.wixis360.website.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity registerStudent(@RequestBody PaymentDTO dto){
        paymentService.savePayment(dto);
       // paymentService.savePayment(new PaymentDTO());
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.CREATED);
    }

   /* @DeleteMapping(params = {"id"})
    public ResponseEntity deleteTutor(@RequestParam String id){
        tutorService.deleteTutor(id);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateTutor(@RequestBody TutorDTO dto) {
        tutorService.updateTutor(dto);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchTutor(@PathVariable String id){
        TutorDTO tutorDTO = tutorService.searchTutor(id);
        StandardResponse success = new StandardResponse(200,"Success",tutorDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllTutors(){
        List<TutorDTO> allTutors = tutorService.getAllTutors();
        StandardResponse success = new StandardResponse(200,"Success",allTutors);
        return new ResponseEntity(success, HttpStatus.OK);
    }
*/
}
