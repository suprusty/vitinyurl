package com.virtualidentity.vitinyurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualidentity.vitinyurl.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String username);
	User findByEmail(String email);
}
