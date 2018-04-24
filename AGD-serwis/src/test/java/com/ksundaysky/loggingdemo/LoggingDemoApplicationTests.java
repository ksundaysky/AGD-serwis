package com.ksundaysky.loggingdemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggingDemoApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void dummyTest(){
		Assert.assertTrue(true);
	}

}
