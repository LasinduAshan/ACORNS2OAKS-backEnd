package lk.wixis360.website.controller;

import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.dto.StudentDTO;
import lk.wixis360.website.service.CourseService;
import lk.wixis360.website.service.FileUploadService;
import lk.wixis360.website.service.StudentService;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin
public class CourseController {


    @Autowired
    CourseService courseService;

    @Autowired
    FileUploadService fileUploadService;

    @Value("${image.upload.path}")
    String IMAGE_UPLOAD_PATH;



    @Value("${videos.upload.path}")
    String VIDEO_UPLOAD_PATH;



    @RequestMapping(value = "/video_uploads/{name:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getVideo(@PathVariable String name) throws IOException {
        File img = new File(VIDEO_UPLOAD_PATH + name);
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }



    @RequestMapping(value = "/image_uploads/{name:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {
        File img = new File(IMAGE_UPLOAD_PATH + name);
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }



//
    /*@PostMapping(path = "/save1",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addCourse(@RequestBody CourceDTO dto){
        System.out.println("Json Object Request.........");
        *//*if (cid=="null"){
            cid = UUID.randomUUID().toString();
        }*//*
      // courseService.saveCourse(dto,image);

        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.CREATED);
    }*/

    @PostMapping(path = "/save",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadImage(@RequestParam(value = "file1[]") MultipartFile[] files,@RequestParam String title,
                                      @RequestParam String courseIncludes,@RequestParam String deteails,@RequestParam String whatWillLearn,
                                      @RequestParam String tutorEamilOrContact, @RequestParam(value = "video[]") MultipartFile[] videos){
        System.out.println("image request");
        System.out.println(title);

        CourceDTO courceDTO = new CourceDTO(title, courseIncludes, deteails, whatWillLearn, tutorEamilOrContact);
        MultipartFile image = null;
        String cid = UUID.randomUUID().toString();

         //courseService.saveCourse(dto);

        for (MultipartFile file : files) {
            //System.out.println(file.getOriginalFilename());
            image = file;
        }

        courseService.saveCourse(courceDTO,image,cid);

        for (MultipartFile video : videos) {
           // System.out.println(video.getOriginalFilename());
            fileUploadService.saveFile(video,cid);

        }
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.CREATED);
    }

    /*@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadVideos(@RequestParam(value = "file[]") MultipartFile[] files, HttpServletRequest request) {
        //String rootPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("Videos Request.........");

       *//* if (cid=="null"){
            cid = UUID.randomUUID().toString();
        }*//*

//        fileUploadService.saveFile(files[0]);
//        fileUploadService.saveFile(files[1]);
        for (MultipartFile file : files) {
            fileUploadService.saveFile(file);
        }

        //cid="null";
        //System.out.println(files[0].getOriginalFilename());

//          System.out.println(files[0].getOriginalFilename());
//          System.out.println(files[1].getOriginalFilename());
//        return new ResponseEntity(new StrandedResponse(true, "Success.!"), HttpStatus.OK);
       return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
        //return null;
    }*/

    /*@PostMapping(value = "/upload_avatar", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView uploadAvatar(HttpServletRequest request, @RequestParam String id, @RequestParam String id2, @RequestParam("picture") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("user/view_user_look");
        try {
            int rand = (int) (Math.random() * (1000000 - 1000));
            String folder = UPLOAD_PATH+"/img/activity/";
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + rand + file.getOriginalFilename());
            Path write = Files.write(path, bytes);
            String aa = "img/activity/" + rand + file.getOriginalFilename();
            this.userService.updateBetMemberColumn("avatar", aa, Integer.parseInt(id2));
            modelAndView.addObject("error", false);
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("error", true);
            modelAndView.addObject("message", ex.getMessage());
        }
        modelAndView.addObject("id", id);
        modelAndView.addObject("id2", id2);
        return modelAndView;
    }*/

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteCourse(@RequestParam String id){
        courseService.deleteCourse(id);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateCourse(@RequestBody CourceDTO dto) {
        courseService.updateCourse(dto);
        return new ResponseEntity(new StandardResponse(200, "Success", "aaa"), HttpStatus.OK);
    }

   /* @GetMapping(path = "/{id}")
    public ResponseEntity searchCourse(@PathVariable String id){
        CourceDTO courceDTO = courseService.searchCourse(id);
        StandardResponse success = new StandardResponse(200,"Success",courceDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }*/

    @GetMapping(path = "/{courseID}/{courseID1}")
    public ResponseEntity searchCourse(@PathVariable String courseID, @PathVariable String courseID1){
        CourceDTO courceDTO = courseService.searchCourse(courseID);
        StandardResponse success = new StandardResponse(200,"Success",courceDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCourses(){
        List<CourceDTO> allCourses = courseService.getAllCourses();
        StandardResponse success = new StandardResponse(200,"Success",allCourses);
        return new ResponseEntity(success, HttpStatus.OK);
    }


    @GetMapping(path = "/{category}")
    public ResponseEntity getAllCourses(@PathVariable String category){
        List<CourceDTO> allCourses = courseService.getAllCoursesByCategory(category);
        //List<CourceDTO> allCourses = courseService.getAllCoursesBySearch(category);           // meka search karana eka name eken like karala
        StandardResponse success = new StandardResponse(200,"Success",allCourses);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(path = "/{value1}/{value2}/{value3}")
    public ResponseEntity getAllCoursesBySearchValue(@PathVariable String value1, @PathVariable String value2, @PathVariable String value3){
        List<CourceDTO> allCourses = courseService.getAllCoursesBySearch(value1);           // meka search karana eka name eken like karala
        StandardResponse success = new StandardResponse(200,"Success",allCourses);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(path = "/tutor", params = {"id"})
    public ResponseEntity searchCoursesByEmail(@RequestParam("id") String email) {
        List<CourceDTO> allCoursesByTutor = courseService.getAllCoursesByTutor(email);
        StandardResponse success = new StandardResponse(200,"Success",allCoursesByTutor);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity searchCoursesByTutorEmail(@RequestParam("id") String email) {
        CourceDTO courceDTO = (CourceDTO) courseService.getAllCoursesByStudent(email);
        StandardResponse success = new StandardResponse(200,"Success",courceDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }

}
