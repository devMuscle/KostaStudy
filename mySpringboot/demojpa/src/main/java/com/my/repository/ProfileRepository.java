package com.my.repository;

import org.springframework.data.repository.CrudRepository;

import com.my.board.entity.Profile;

public interface ProfileRepository extends CrudRepository<Profile, String> {

}
