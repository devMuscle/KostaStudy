package com.my;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class LogTest {
	private Logger log = 
			LoggerFactory.getLogger(getClass());
	@Test
	void test() {
		log.debug("DEBUG LEVEL LOG");
		log.info("INFO LEVEL LOG");
		log.warn("WARN LEVEL LOG");
		log.error("ERROR LEVEL LOG");
	}
	
	@Test
	void testLoop() {
		for(int i=0; i<10000; i++) {
			log.debug("DEBUG LEVEL LOG" + i);
			log.info("INFO LEVEL LOG"+ i);
			log.warn("WARN LEVEL LOG"+ i);
			log.error("ERROR LEVEL LOG"+ i);
		}
	}
}
