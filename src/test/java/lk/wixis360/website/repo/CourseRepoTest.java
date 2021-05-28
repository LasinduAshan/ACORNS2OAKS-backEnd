package lk.wixis360.website.repo;

import lk.wixis360.website.config.WebAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class})
@RunWith(SpringRunner.class)
public class CourseRepoTest {

    @Autowired
    CourseRepo repo;

    @Test
    public void test() {


    }
}
