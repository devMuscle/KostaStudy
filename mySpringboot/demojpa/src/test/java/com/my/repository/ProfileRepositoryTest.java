package com.my.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.board.entity.Member;
import com.my.board.entity.Profile;

@SpringBootTest
class ProfileRepositoryTest {
	
	@Autowired
	ProfileRepository repository;
	
	@Test
	void testInsert() {
		Member member = new Member();
		member.setM_uid("user1");
		for(int i=1; i<=3; i++) {
			Profile profile = new Profile();
			profile.setP_fname("face" + i + ".jpg");
			if(i==1) {
				profile.setP_current(true);
			}
			profile.setMember(member);
			repository.save(profile);
		}
	}

}
