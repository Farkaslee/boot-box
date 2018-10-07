package com.boot.box;

import com.boot.box.config.MyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootBoxApplicationTests {

	@Autowired
	private MyConfig myConfig;
	@Test
	public void contextLoads() {
		System.out.println("myconfig : " + myConfig.getName());
	}

}
