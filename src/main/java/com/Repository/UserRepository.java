package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Enitity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUserEmail(String userEmail);
}
