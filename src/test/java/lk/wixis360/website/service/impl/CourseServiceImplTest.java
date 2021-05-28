package lk.wixis360.website.service.impl;

import lk.wixis360.website.config.WebAppConfig;
import lk.wixis360.website.dto.CourceDTO;
import lk.wixis360.website.service.CourseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

//@Transactional              // meka dammama data base ekata value watenna.........
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class})
@RunWith(SpringRunner.class)
public class CourseServiceImplTest {

  /*  @Autowired
    CourseService customerService;
*/

    @Test
    public void saveCustomer() {
      /*  CourceDTO dto = new CourceDTO("C001", "Ramal", "Galle");
        customerService.saveCourse(dto);*/
    }



}
