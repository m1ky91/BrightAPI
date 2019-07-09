package it.micheledichio.brightapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BrightApiApplication.class)
@ActiveProfiles("test")
public class BrightApiApplicationTests {

    @Test
	public void contextLoads() {
	}

}
