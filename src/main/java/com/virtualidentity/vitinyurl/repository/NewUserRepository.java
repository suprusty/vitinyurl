package com.virtualidentity.vitinyurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualidentity.vitinyurl.entity.NewUser;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser, Long>{
	 NewUser findByUsername(String username);
	 NewUser findByEmail(String email);
}
