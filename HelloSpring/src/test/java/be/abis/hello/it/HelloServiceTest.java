package be.abis.hello.it;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.hello.service.AbisHelloService;
import be.abis.hello.service.HelloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
	
	@Autowired
	HelloService helloService;
	
	@Before
	public void setUp() {
		helloService = new AbisHelloService();
	}

	@Test
	public void  chechWetherPersonIsCecile() {
		assertEquals("Cecile", helloService.findPerson(1).getFirstName());
	}
}
