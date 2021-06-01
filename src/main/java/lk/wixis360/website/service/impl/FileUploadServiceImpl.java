package lk.wixis360.website.service.impl;

import lk.wixis360.website.controller.CourseController;
import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.entity.Account;
import lk.wixis360.website.entity.Cource;
import lk.wixis360.website.entity.FileUpload;
import lk.wixis360.website.repo.AccountRepo;
import lk.wixis360.website.repo.CourseRepo;
import lk.wixis360.website.repo.FileUploadRepo;
import lk.wixis360.website.service.AccountService;
import lk.wixis360.website.service.FileUploadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {



    private static final String UPLOAD_PATH = "E:/Quasar Projects/aluth project update eka clone krgnna eka/ACORNS2OAKS/src/assets/";
    @Autowired
    FileUploadRepo repo;

    @Autowired
    CourseRepo crepo;

    @Autowired
    ModelMapper mapper;

    @Value("${videos.upload.path}")
    String videos_upload_path;

    @Override
    public void saveFile(MultipartFile files, String cid) {

        System.out.println(videos_upload_path+"             .................................................................");



       // String interrPath = "E:/Quasar Projects/wixis-360-new/ACORNS2OAKS/src/assets/videos/" + files.getOriginalFilename();

        String interrPath = videos_upload_path + files.getOriginalFilename();
        //String interrPath = "E:/Quasar Projects/wixis-360-new/ACORNS2OAKS/public/videos/" + files.getOriginalFilename();
        //String videoPath =  "../assets/videos/"+files.getOriginalFilename();
        //String videoPath =  "videos/"+files.getOriginalFilename();
        //String videoPath =  videos_upload_path+files.getOriginalFilename();
        String videoPath =  files.getOriginalFilename();

        System.out.println("File name " + files.getOriginalFilename());
        System.out.println("video path " + videoPath);

        try {
            files.transferTo(new File(interrPath));

        } catch (IOException e) {

        }

        //Cource one = crepo.getOne(CourseServiceImpl.courseID);
        Cource one = crepo.getOne(cid);

        FileUpload f = new FileUpload(videoPath,one);
        repo.save(f);




















       // ModelAndView modelAndView = new ModelAndView("user/view_user_look");

        /*try {
            int rand = (int) (Math.random() * (1000000 - 1000));
            String folder = UPLOAD_PATH+"/img/activity/";
            byte[] bytes = files.getBytes();
            Path path = Paths.get(folder + rand + files.getOriginalFilename());
            Path write = Files.write(path, bytes);
            String aa = "img/activity/" + rand + files.getOriginalFilename();

            System.out.println(aa);

            FileUpload f = new FileUpload(UUID.randomUUID().toString(),aa);
            repo.save(f);


//            this.userService.updateBetMemberColumn("avatar", aa, Integer.parseInt(id2));
//            modelAndView.addObject("error", false);
        } catch (Exception ex) {
            ex.printStackTrace();
//            modelAndView.addObject("error", true);
//            modelAndView.addObject("message", ex.getMessage());
        }*/
       // modelAndView.addObject("id", id);
//        modelAndView.addObject("id2", id2);
//        return modelAndView;

    }
}

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
