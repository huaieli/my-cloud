package com;

import com.xsn.MysqlRWApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MysqlRWApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("junit")
public class BaseTest {
}
