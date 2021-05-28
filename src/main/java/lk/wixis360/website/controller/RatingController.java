package lk.wixis360.website.controller;

import lk.wixis360.website.dto.RatingDTO;
import lk.wixis360.website.dto.StudentReviewDTO;
import lk.wixis360.website.service.RatingService;
import lk.wixis360.website.service.StudentReviewService;
import lk.wixis360.website.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@CrossOrigin
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveRating(@RequestBody RatingDTO dto){
        ratingService.saveRating(dto);
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
    */


    /*@GetMapping(path = "/{courseID}")
    public ResponseEntity getAllReviewsByCourse(@PathVariable String courseID){
        List<StudentReviewDTO> allReviewsByCourse = reviewService.getAllReviewsByCourse(courseID);
        StandardResponse success = new StandardResponse(200,"Success",allReviewsByCourse);
        return new ResponseEntity(success, HttpStatus.OK);
    }*/

}
