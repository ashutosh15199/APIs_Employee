package com.org.EmployeemanagementSystem;

import com.org.EmployeemanagementSystem.entities.Role;
import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class EmployeemanagementSystemApplication {
@Autowired
UserRepo userRepo;
	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagementSystemApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		User adminAccount = userRepo.findByRole(Role.ADMIN);
//		if(null==adminAccount){
//			User user = new User();
//			user.setUsername("Anjali");
//			user.setEmailId("anjali@123gmail.com");
//			user.setRole(Role.ADMIN);
//			user.setPassword(new BCryptPasswordEncoder().encode("anjali@123"));
//			userRepo.save(user);
//		}

}
