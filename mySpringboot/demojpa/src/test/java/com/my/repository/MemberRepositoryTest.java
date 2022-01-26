package com.my.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.board.entity.Member;

@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	private MemberRepository repository;
	
	@Test
	void testInsert() {
		assertNotNull(repository);
		for(int i=1; i<5; i++) {
			Member m = new Member();
			m.setM_uid("user"+i);
			m.setM_uname("사용자"+i);
			m.setM_upw("pw"+i);
			repository.save(m);
		}
	}

}
