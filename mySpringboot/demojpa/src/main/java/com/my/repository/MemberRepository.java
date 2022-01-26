package com.my.repository;

import org.springframework.data.repository.CrudRepository;

import com.my.board.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
