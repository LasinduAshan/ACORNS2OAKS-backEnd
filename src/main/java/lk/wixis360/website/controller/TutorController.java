package lk.wixis360.website.controller;

import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.dto.TutorDTO;
import lk.wixis360.website.service.AccountService;
import lk.wixis360.website.service.CourseService;
import lk.wixis360.website.service.TutorService;
import lk.wixis360.website.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tutor")
@CrossOrigin
public class TutorController {

    @Autowired
    TutorService tutorService;
    @Autowired
    AccountService accountService;


    @Value("${tutor.image.upload.path}")
    String IMAGE_UPLOAD_PATH;

    @RequestMapping(value = "/image_uploads/{name:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {
        File img = new File(IMAGE_UPLOAD_PATH + name);
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addTutor(@RequestBody TutorDTO dto){
        tutorService.saveTutor(dto);
        accountService.saveAccount(new AccountDTO(dto.getEmail(),dto.getContact(),dto.getPassword(),"Tutor"));
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.CREATED);
    }

    //@PostMapping(path = "/about", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateTutorAbout(@RequestBody TutorDTO dto){
        tutorService.updateTutorAbout(dto);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteTutor(@RequestParam String id){
        tutorService.deleteTutor(id);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @PutMapping(path = "/{tutorID}")
    public ResponseEntity updateTutor(@PathVariable String tutorID) {
        // default avater eka danne.......
        tutorService.updateTutorPhoto(tutorID);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @PostMapping(path = "/update/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity updateTutorImage(@PathVariable String id,@RequestParam(value = "file[]") MultipartFile[] files, HttpServletRequest request) {
        for (MultipartFile file : files) {
            //System.out.println(file.getOriginalFilename());
            tutorService.updateTutorImage(file,id);
        }
        //System.out.println(id);
        //tutorService.updateTutorPhoto(tutorID);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity searchTutorByEmail(@PathVariable String id){
        TutorDTO tutorDTO = tutorService.searchTutor(id);
        StandardResponse success = new StandardResponse(200,"Success",tutorDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(path = "/search/{id}")
    public ResponseEntity searchTutorByID(@PathVariable String id){
        TutorDTO tutorDTO = tutorService.searchTutorByID(id);
        StandardResponse success = new StandardResponse(200,"Success",tutorDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllTutors(){
        List<TutorDTO> allTutors = tutorService.getAllTutors();
        StandardResponse success = new StandardResponse(200,"Success",allTutors);
        return new ResponseEntity(success, HttpStatus.OK);
    }

}
